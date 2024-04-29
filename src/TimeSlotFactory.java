import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// A class that creates all the needed timeslots in the calendar
// to support implementation of flyweight pattern

public class TimeSlotFactory {

    public static final Map<Integer, TimeSlot> created = new HashMap<>();
    public static final List<TimeSlot> allTimes = new ArrayList<>();

    static {
        TimeSlot one = new TimeSlot(1, "8AM", "10AM", "MWF");
        created.put(1, one);
        allTimes.add(one);

        /** ... create the rest of the 9 timeslots
         * MWF:
         * TODO
         */

    }

    public static TimeSlot getTimeSlot(int id) {
        return created.get(id);
    }

    public static List<TimeSlot> getAllTimeSlots() {
        return allTimes;
    }

}
