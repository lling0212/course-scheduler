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

        TimeSlot two = new TimeSlot(2, "10AM", "12PM", "MWF");
        created.put(2, two);
        allTimes.add(two);

        TimeSlot three = new TimeSlot(3, "12PM", "2PM", "MWF");
        created.put(3, three);
        allTimes.add(three);

        TimeSlot four = new TimeSlot(4, "2PM", "4PM", "MWF");
        created.put(4, four);
        allTimes.add(four);

        TimeSlot five = new TimeSlot(5, "4PM", "6PM", "MWF");
        created.put(5, five);
        allTimes.add(five);

        TimeSlot six = new TimeSlot(6, "8AM", "10AM", "TTh");
        created.put(6, six);
        allTimes.add(six);

        TimeSlot seven = new TimeSlot(7, "10AM", "12PM", "TTh");
        created.put(7, seven);
        allTimes.add(seven);

        TimeSlot eight = new TimeSlot(8, "12PM", "2PM", "TTh");
        created.put(8, eight);
        allTimes.add(eight);

        TimeSlot nine = new TimeSlot(9, "2PM", "4PM", "TTh");
        created.put(9, nine);
        allTimes.add(nine);

        TimeSlot ten = new TimeSlot(10, "4PM", "6PM", "TTh");
        created.put(10, ten);
        allTimes.add(ten);
    }

    public static TimeSlot getTimeSlot(int id) {
        return created.get(id);
    }


}
