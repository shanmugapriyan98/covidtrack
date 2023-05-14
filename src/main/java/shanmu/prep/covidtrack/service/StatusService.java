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
import java.util.concurrent.TimeUnit;

@Service
public class StatusService {
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
        List<Status> resultList = new ArrayList<>();
        LocalDate localDate = LocalDate.of( 2020 , 9 , 10 );
        Date date = Date.from(localDate.atStartOfDay(ZoneId.of("Africa/Tunis")).toInstant());
        for(Status status:statusList){
            long timeDiff = date.getTime() - status.getDateNew().getTime();
            long days = TimeUnit.DAYS.convert(timeDiff, TimeUnit.MILLISECONDS);
            if(days < 7 && days >=0){
                resultList.add(status);
            }
        }
        return resultList;
    }

    public Date convertStringToDate(String pattern, String dateString){
        Date dateNew= null;
        try {
            dateNew = new SimpleDateFormat(pattern).parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return dateNew;
    }

    public List<Status> getStatusBetweenDates(List<Status> statusList, String state, String startDateStr, String endDateStr) {
        String datePattern = ("yyyy-MM-dd");
        Date startDate = convertStringToDate(datePattern, startDateStr);
        Date endDate = convertStringToDate(datePattern, endDateStr);
        return getStatus(statusList, state, startDate, endDate);
    }

    public List<Status> getStatus(List<Status> statusList, String state, Date startDate, Date endDate){
        List<Status> resultList = new ArrayList<>();
        for(Status status:statusList){
            long startTime = startDate.getTime();
            long endTime = endDate.getTime();
            long statusTime = status.getDateNew().getTime();
            if((statusTime >= startTime) && (statusTime <= endTime)){
                resultList.add(status);
            }
        }
        return resultList;
    }
}
