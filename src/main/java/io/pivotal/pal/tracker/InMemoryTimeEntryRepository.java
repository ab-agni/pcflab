package io.pivotal.pal.tracker;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private final Map<Long, TimeEntry> timeEntries = new HashMap<>();
    private Long counter = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(counter);
        timeEntries.put(timeEntry.getId(), timeEntry);
        counter++;

        return timeEntry;
    }

    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if(timeEntries.get(id) != null) {
            timeEntry.setId(id);
            timeEntries.put(id, timeEntry);

            return timeEntry;
        } else {
            return null;
        }

    }

    public void delete(long id) {
        timeEntries.remove(id);
    }
}
