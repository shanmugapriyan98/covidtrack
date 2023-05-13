package shanmu.prep.covidtrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import shanmu.prep.covidtrack.model.Status;
import shanmu.prep.covidtrack.service.StatusService;

import java.util.Arrays;
import java.util.List;

@RestController
public class StatusController {
    @Autowired
    private StatusService statusService;

    @GetMapping("/weekly")
    public ResponseEntity<List<Status>> getLastWeekStatus(){
        String url = "https://api.covidtracking.com/v1/states/az/daily.json";
        RestTemplate restTemplate = new RestTemplate();
        Status[] statuses = restTemplate.getForObject(url,Status[].class);
        List<Status> dateStatuses = statusService.setDate(statuses);
        List<Status> result = statusService.getLastWeekStatus(dateStatuses);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
