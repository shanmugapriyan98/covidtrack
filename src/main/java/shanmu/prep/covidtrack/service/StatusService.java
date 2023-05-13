package shanmu.prep.covidtrack.service;

import org.springframework.stereotype.Service;
import shanmu.prep.covidtrack.model.Status;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
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
}
