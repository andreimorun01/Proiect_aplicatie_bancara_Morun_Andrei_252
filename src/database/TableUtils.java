package database;

public class TableUtils {
    /**
     * Initialize all tables from the database
     */
    public static void createTables() {
        BankTable bankTable = new BankTable();
        ClientTable clientTable = new ClientTable();
        HistoryTable historyTable = new HistoryTable();
        LoanTable loanTable = new LoanTable();

        bankTable.create();
        clientTable.create();
        historyTable.create();
        loanTable.create();
    }
}
