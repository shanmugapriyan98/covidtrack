package shanmu.prep.covidtrack.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Status {
    private String date;
    private Date dateNew;
    private String state;
    private long positive;
    private long negative;
    private long positiveIncrease;
    private long negativeIncrease;

    public Status(){}
    public Status(String date, String state, long positive, long negative, long positiveIncrease, long negativeIncrease) {
        this.date = date;
        this.state = state;
        this.positive = positive;
        this.negative = negative;
        this.positiveIncrease = positiveIncrease;
        this.negativeIncrease = negativeIncrease;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public long getPositive() {
        return positive;
    }

    public void setPositive(long positive) {
        this.positive = positive;
    }

    public long getNegative() {
        return negative;
    }

    public void setNegative(long negative) {
        this.negative = negative;
    }

    public long getPositiveIncrease() {
        return positiveIncrease;
    }

    public void setPositiveIncrease(long positiveIncrease) {
        this.positiveIncrease = positiveIncrease;
    }

    public long getNegativeIncrease() {
        return negativeIncrease;
    }

    public void setNegativeIncrease(long negativeIncrease) {
        this.negativeIncrease = negativeIncrease;
    }

    public Date getDateNew() {
        return dateNew;
    }

    public void setDateNew() throws ParseException {
        dateNew= new SimpleDateFormat("yyyyMMdd").parse(this.date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/YYYY");
        this.date = simpleDateFormat.format(dateNew);
    }


    @Override
    public String toString() {
        return "Status{" +
                "date=" + date +
                ", state='" + state + '\'' +
                ", positiveCases=" + positive +
                ", negativeCases=" + negative +
                ", positiveIncrease=" + positiveIncrease +
                ", negativeIncrease=" + negativeIncrease +
                '}';
    }
}
