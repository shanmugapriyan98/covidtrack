package shanmu.prep.covidtrack.service;

import org.springframework.stereotype.Service;
import shanmu.prep.covidtrack.model.Status;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class StatusService {
    public String DATE_PATTERN_FROM_FORM = ("yyyy-MM-dd");
    public StatusService(){}
    public List<Status> setDate(Status[] statuses){
        List<Status> statusList = Arrays.asList(statuses);
        for(Status status:statusList){
            try {
                status.setDateNew();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        return statusList;
    }

    //TODO: Make this function also in the same way as get status between dates
    public List<Status> getLastWeekStatus(List<Status> statusList){
        LocalDate localDate = LocalDate.of( 2020 , 9 , 10 );
        Date endDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date startDate = new Date(endDate.getTime() - (7*24*60*60*1000));
        return getStatusWithFilters(statusList,"AZ", startDate, endDate);
    }

    public Date convertStringToDate(String pattern, String dateString){
        Date dateNew;
        try {
            dateNew = new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return dateNew;
    }

    public List<Status> getStatusBetweenDates(List<Status> statusList, String state,
                                              String startDateStr, String endDateStr){
        Date startDate = convertStringToDate(DATE_PATTERN_FROM_FORM, startDateStr);
        Date endDate = convertStringToDate(DATE_PATTERN_FROM_FORM, endDateStr);
        return getStatusWithFilters(statusList, state, startDate, endDate);
    }

    public List<Status> getStatusWithFilters(List<Status> statusList, String state, Date startDate, Date endDate) {
        List<Status> resultList = new ArrayList<>();
        for(Status status: statusList){
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            long statusTime = status.getDateNew().getTime();
            if((statusTime >= startTime) && (statusTime <= endTime) &&
                    (status.getState().equals(state))){
                resultList.add(status);
            }
        }
        return resultList;
    }
}
