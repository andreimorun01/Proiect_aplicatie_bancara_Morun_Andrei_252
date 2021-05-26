package bank;

import currency.Currency;
import currency.CurrencyConverter;
import database.LoanTable;
import files.CSVUtils;

import java.sql.Timestamp;
import java.util.Date;


public class Loan implements Comparable<Loan> {
    private final Timestamp timestamp;
    private Float value;
    private Currency currency;
    private Duration duration;
    private Rate rate;

    public Loan(Client client, Float value, Currency currency, Duration duration) {
        CSVUtils.getInstance().appendToCSV("new_loan", Thread.currentThread().getName());
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.value = value;
        this.currency = currency;
        this.duration = duration;
        this.rate = new Rate((float) (value) / duration.getMonths());

        LoanTable.addLoan(value.toString(), currency.toString(), duration.getMonths().toString(),
                this.rate.toString(), client.getLoans().size());
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    /**
     * @return the remaining payments for the loan
     */
    public Float remainingLoan() {
        Integer remainingMonths = this.getDuration().getMonths() - this.getDuration().getPaidMonths();
        return remainingMonths * this.getRate().getValue();
    }

    /**
     * Function that changes the currency of a credit.
     * @param newCurrency represents the currency that the credit needs to be converted into
     */
    public void changeLoanCurrency(Currency newCurrency) {
        Currency oldCurrency = this.getCurrency();
        this.setCurrency(newCurrency);
        Float exchangeRate = CurrencyConverter.calculateExchangeRate(oldCurrency, newCurrency);
        this.rate = new Rate(exchangeRate * this.getRate().getValue());
        this.value = exchangeRate * this.getValue();
    }

    /**
     * Function that postpones a loan by one month.
     */
    public void postponeLoan() {
        this.getDuration().setPaidMonths(this.getDuration().getPaidMonths() + 1);
        this.getDuration().setMonths(this.getDuration().getMonths() + 1);
    }

    @Override
    public int compareTo(Loan o) {
        return this.timestamp.compareTo(o.timestamp);
    }
}
