package bank;

import files.CSVUtils;

public class Duration {
    private Integer months;
    private Integer paidMonths;

    public Duration(Integer months) {
        this.months = months;
        this.paidMonths = 0;
    }

    public Integer getMonths() {
        return months;
    }

    public void setMonths(Integer months) {
        this.months = months;
    }

    public Integer getPaidMonths() {
        return paidMonths;
    }

    public void setPaidMonths(Integer paidMonths) {
        CSVUtils.getInstance().appendToCSV("update_paid_months", Thread.currentThread().getName());
        this.paidMonths = paidMonths;
    }
}
