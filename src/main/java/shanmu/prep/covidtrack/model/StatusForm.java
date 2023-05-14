package shanmu.prep.covidtrack.model;

public class StatusForm {
    private String state;
    private String startDate;
    private String endDate;

    public StatusForm(String state, String startDate, String endDate) {
        this.state = state;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StatusForm{" +
                "state='" + state + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
