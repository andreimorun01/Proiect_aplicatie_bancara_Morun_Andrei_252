package bank;

import currency.Currency;
import currency.CurrencyConverter;
import files.CSVUtils;
import person.Person;

import java.util.Date;
import java.util.TreeSet;

public class Client extends Person {
    private TreeSet<Loan> loans;

    public Client(String firstName, String lastName, String address, Date birthday) {
        super(firstName, lastName, address, birthday);
        CSVUtils.getInstance().appendToCSV("new_client", Thread.currentThread().getName());
        this.loans = new TreeSet<>();
    }

    public TreeSet<Loan> getLoans() {
        return loans;
    }

    public void setLoans(TreeSet<Loan> loans) {
        this.loans = loans;
    }

    public Loan createLoan(Float value, Currency currency, Duration duration) {
        Loan currentLoan = new Loan(this, value, currency, duration);
        this.loans.add(currentLoan);
        return currentLoan;
    }

    /**
     * Function that pays for a month from the credit.
     * If the payments have been completed, the loan will be deleted.
     * @param loan represents the loan that will pe paid
     */
    public void payLoan(Loan loan) {
        CSVUtils.getInstance().appendToCSV("paid_loan", Thread.currentThread().getName());
        loan.getDuration().setPaidMonths(loan.getDuration().getPaidMonths() + 1);
        if (loan.getDuration().getMonths().equals(loan.getDuration().getPaidMonths())) {
            this.loans.remove(loan);
            CSVUtils.getInstance().appendToCSV("finished_loan", Thread.currentThread().getName());
        }
    }

    public void getInformation() {
        for (Loan loan : loans) {
            int remainingMonths = loan.getDuration().getMonths() - loan.getDuration().getPaidMonths();
            System.out.println("Loan Value: " + loan.getValue() +
                    "; Currency: " + loan.getCurrency() +
                    "; Months: " + loan.getDuration().getMonths() +
                    "; Remaining Months: " + remainingMonths +
                    "; Monthly Rate: " + loan.getRate().getValue());
        }
    }

    /**
     * Function that calculates the total value of the loans.
     * @param newCurrency represents the common currency of the total value
     * @return the total value of the remaining rates from the loans
     */
    public Float calculateAllLoans(Currency newCurrency) {
        float allLoans = 0f;
        for (Loan loan : loans) {
            Integer remainingMonths = loan.getDuration().getMonths() - loan.getDuration().getPaidMonths();
            Float exchangeRate = CurrencyConverter.calculateExchangeRate(loan.getCurrency(), newCurrency);
            float currentLoan = remainingMonths * loan.getRate().getValue() * exchangeRate;
            allLoans += currentLoan;
        }
        return allLoans;
    }
}
