package files;

import database.HistoryTable;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {
    private static CSVUtils instance = null;
    private List<HistoryElement> history;

    private CSVUtils() {
        this.history = new ArrayList<>();
    }

    public static CSVUtils getInstance() {
        if (instance == null) {
            instance = new CSVUtils();
        }
        return instance;
    }

    public List<HistoryElement> getHistory() {
        return history;
    }

    public void readCSV() {
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader("src/history.csv"));
            String currentRow;
            while ((currentRow = bufferedReader.readLine()) != null) {
                String[] data = currentRow.split(",");
                Timestamp timestamp = new Timestamp(Long.parseLong(data[1]));
                history.add(new HistoryElement(data[0], timestamp, data[2]));
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Function that adds a new line to the history.csv
     * @param string represents the action done
     * @param threadName represents the thread that has called the function
     */
    public void appendToCSV(String string, String threadName) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("src/history.csv", true);
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            HistoryElement historyElement = new HistoryElement(string, timestamp, threadName);
            this.history.add(historyElement);
            fileWriter.append(string);
            fileWriter.append(",");
            fileWriter.append(String.valueOf(timestamp.getTime()));
            fileWriter.append(",");
            fileWriter.append(threadName);
            fileWriter.append("\n");
            fileWriter.flush();
            fileWriter.close();
            HistoryTable.addHistoryElement(string, String.valueOf(timestamp.getTime()), threadName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
