package files;

import java.sql.Timestamp;

public class HistoryElement {
    private String action;
    private Timestamp timestamp;
    private String threadName;

    public HistoryElement(String action, Timestamp timestamp, String threadName) {
        this.action = action;
        this.timestamp = timestamp;
        this.threadName = threadName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getThreadName() {
        return threadName;
    }

    public void setThreadName(String threadName) {
        this.threadName = threadName;
    }
}
