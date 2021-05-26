package frames;

import bank.*;
import com.sun.tools.javac.Main;
import currency.CurrencyConverter;
import currency.EUR;
import currency.RON;
import currency.USD;
import database.CreateDatabase;
import database.DeleteDatabase;
import database.TableUtils;
import files.CSVUtils;
import files.HistoryElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;


public class MainFrame {
    public static void open(List<HistoryElement> history) {
        JButton historyButton = new JButton("history");
        historyButton.setBounds(475, 500, 100, 50);

        JButton ExitButton = new JButton("EXIT");
        ExitButton.setBounds(475, 450, 100, 50);

        JButton AddButton = new JButton("Add Client");
        AddButton.setBounds(15, 500, 100, 50);

        JButton DeleteButton = new JButton("Del Client");
        DeleteButton.setBounds(15, 450, 100, 50);


        JButton ConversieButton = new JButton("Convert");
        ConversieButton.setBounds(15, 400, 100, 50);

        JButton LoansButton = new JButton("Loans");
        LoansButton.setBounds(15, 350, 100, 50);

        JButton PayLoanButton = new JButton("Pay Loan");
        PayLoanButton.setBounds(15, 300, 100, 50);

        JButton InfoButton = new JButton("Info");
        InfoButton.setBounds(15, 250, 100, 50);

        JButton DeleteBDButton = new JButton("DeleteBD");
        DeleteBDButton.setBounds(15, 200, 100, 50);

        JButton CreateBDButton = new JButton("CreateBD");
        CreateBDButton.setBounds(15, 150, 100, 50);

        JFrame mainFrame = new JFrame();
        mainFrame.add(historyButton);
        mainFrame.add(ExitButton);
        mainFrame.add(AddButton);
        mainFrame.add(DeleteButton);
        mainFrame.add(ConversieButton);
        mainFrame.add(InfoButton);
        mainFrame.add(LoansButton);
        mainFrame.add(PayLoanButton);
        mainFrame.add(DeleteBDButton);
        mainFrame.add(CreateBDButton);
        mainFrame.setSize(600, 600);
        mainFrame.setLayout(null);
        mainFrame.setVisible(true);

        CreateDatabase.main(null);
        TableUtils.createTables();
        Date date = new Date(946754592000L);
        //System.out.println(date.toString());
        Bank bank = new Bank("Banca");
        Client client = new Client("Daniel", "Marcu", "Str. Amara, nr.10", date);


        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.dispose();
                HistoryFrame.open(history);
            }
        });
        ExitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteDatabase.main(null);
            }
        });

        InfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                client.getInformation();
            }
        });

        DeleteBDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mainFrame.dispose();
                //HistoryFrame.open(history);
                DeleteDatabase.main(null);
            }
        });
        CreateBDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mainFrame.dispose();
                //HistoryFrame.open(history);
                CreateDatabase.main(null);
                TableUtils.createTables();
            }
        });
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mainFrame.dispose();
                //HistoryFrame.open(history);
                CSVUtils.getInstance().readCSV();

                bank.addClient(client);
                Client client1 = new Client("Cristian", "Andone", "Str. Amara, nr.15", date);
                bank.addClient(client1);
                Client client2 = new Client("Stefan", "Dragut", "Str. Brasov, nr.85", date);
                bank.addClient(client2);
                //Client client3 = new Client("Alex", "Popescu", "Str. Aviatiei, nr.47", date);
                // bank.addClient(client3);

                RON ron = new RON();
                EUR eur = new EUR();
                USD usd = new USD();

                Duration duration = new Duration(60);
                Rate rate = bank.calculateLoan(1234.5678f, eur, duration);
                System.out.println("\n The value of the rate for 60 nonths " + rate.getValue() + "\n");

                System.out.println("\n Conversion 1 USD to RONI " + CurrencyConverter.calculateExchangeRate(usd, ron) + "\n");

                System.out.println("\n All loans in euro (the exact average) " + client.calculateAllLoans(eur) + "\n");
                Loan currentLoan = client.createLoan(1234.5678f, eur, duration);
                System.out.println("\n All loans in EURO " + client.calculateAllLoans(eur) + "\n");
                System.out.println("\n All loans in RONI " + client.calculateAllLoans(ron) + "\n");

                client.payLoan(currentLoan);
                System.out.println("\n Loan pay  ");
                System.out.println("\n Remaining loan for the first: " + currentLoan.remainingLoan() + "\n");

                System.out.println(client.calculateAllLoans(eur));

                Duration nextDuration = new Duration(12);
                Loan nextLoan = client.createLoan(12.36f, eur, nextDuration);
                nextLoan.postponeLoan();
                System.out.println("\n After pay, all loans  ");
                client.payLoan(nextLoan);
                 //client.payLoan(nextLoan);
                // client.payLoan(nextLoan);
                // client.payLoan(nextLoan);
               //  client.payLoan(nextLoan);
                // client.payLoan(nextLoan);
                // client.payLoan(nextLoan);
               //  client.payLoan(nextLoan);
               //  client.payLoan(nextLoan);
               //  client.payLoan(nextLoan);
               //  client.payLoan(nextLoan);
              //   client.payLoan(nextLoan);
//

                nextLoan.changeLoanCurrency(ron);



            }
        });

        LoansButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //mainFrame.dispose();
                //HistoryFrame.open(history);
                RON ron = new RON();
                EUR eur = new EUR();
                USD usd = new USD();
                System.out.println(client.calculateAllLoans(usd));

            }
        });
        DeleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //mainFrame.dispose();
                //HistoryFrame.open(history);
                CSVUtils.getInstance().readCSV();
                bank.deleteClient(client);
            }
        });
    }
}
