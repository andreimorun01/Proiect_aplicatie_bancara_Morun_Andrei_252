import database.CreateDatabase;
import database.DeleteDatabase;
import database.TableUtils;
import files.CSVUtils;
import files.HistoryElement;
import frames.MainFrame;

import java.util.List;


public class UpdatedMain {
    public static void main(String[] args) {
        //DeleteDatabase.main(null);
        //CreateDatabase.main(null);
        //TableUtils.createTables();

        CSVUtils.getInstance().readCSV();
        List<HistoryElement> history = CSVUtils.getInstance().getHistory();

        MainFrame.open(history);
    }
}