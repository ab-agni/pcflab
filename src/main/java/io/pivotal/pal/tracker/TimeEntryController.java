package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }
@PostMapping("/time-entries")
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry timeEntry = timeEntryRepository.create(timeEntryToCreate);

        return new ResponseEntity(timeEntry, HttpStatus.CREATED);
    }
@GetMapping("/time-entries/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable(name="id",required = true) long timeEntryId) {
        //timeEntryRepository.find(1L);

        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);

        if(timeEntry == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }
        //HttpStatus.OK

    }
@GetMapping("/time-entries")
    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries =  timeEntryRepository.list();
       return new ResponseEntity(timeEntries, HttpStatus.OK);
    }
@PutMapping("/time-entries/{UpdateID}")
    public ResponseEntity update(@PathVariable(name="UpdateID", required=true) long timeEntryId,@RequestBody TimeEntry expected) {

        TimeEntry timeEntry = timeEntryRepository.update(timeEntryId,expected);

        if(timeEntry == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }


    }
@DeleteMapping("/time-entries/{IDjhh}")
    public ResponseEntity<TimeEntry> delete(@PathVariable(name="IDjhh",required=true) long timeEntryId) {

         TimeEntry timeEntry = timeEntryRepository.delete(timeEntryId);
        return new ResponseEntity(timeEntry, HttpStatus.NO_CONTENT);

    }
}
