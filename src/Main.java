import bank.*;
import currency.CurrencyConverter;
import currency.EUR;
import currency.RON;
import currency.USD;
import files.CSVUtils;


import java.util.Date;

public class Main {

    /**
     * Main functions tested from the implementation
     * @param args not used
     */
    public static void main(String[] args) {

        CSVUtils.getInstance().readCSV();

        Bank bank = new Bank("Banca");

        Date date = new Date(946754592000L);
        System.out.println(date.toString());

        Client client = new Client("Daniel", "Marcu", "Str. Amara, nr.10", date);
        bank.addClient(client);
        //Client client1 = new Client("Cristian", "Andone", "Str. Amara, nr.15", date);
        //bank.addClient(client1);
       // Client client2 = new Client("Stefan", "Dragut", "Str. Brasov, nr.85", date);
       // bank.addClient(client2);
        //Client client3 = new Client("Alex", "Popescu", "Str. Aviatiei, nr.47", date);
       // bank.addClient(client3);

        RON ron = new RON();
        EUR eur = new EUR();
        USD usd = new USD();

        Duration duration = new Duration(60);
        Rate rate = bank.calculateLoan(1234.5678f, eur, duration);
        System.out.println(rate.getValue());

        System.out.println(CurrencyConverter.calculateExchangeRate(usd, ron));

        System.out.println(client.calculateAllLoans(eur));
        Loan currentLoan = client.createLoan(1234.5678f, eur, duration);
        System.out.println(client.calculateAllLoans(eur));
        System.out.println(client.calculateAllLoans(ron));

        client.payLoan(currentLoan);
        System.out.println("Remaining loan for the first: " + currentLoan.remainingLoan());
        System.out.println(client.calculateAllLoans(eur));

        Duration nextDuration = new Duration(12);
        Loan nextLoan = client.createLoan(12.36f, eur, nextDuration);
        nextLoan.postponeLoan();
        client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // client.payLoan(nextLoan);
       // System.out.println(client.calculateAllLoans(usd));

        nextLoan.changeLoanCurrency(ron);

        client.getInformation();
        bank.deleteClient(client);
    }
}
