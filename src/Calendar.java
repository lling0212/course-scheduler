import java.util.List;
import java.util.Map;

/**
 * implements ICalendar
 */
public class Calendar {

    private Courses courses;
    private List<CalendarTile> schedule;
    private List<CalendarTile> scheduleWithPref;
    private Map<String, CalendarTile> scheduleByDepartment;
    private Map<String, CalendarTile> scheduleByInstructor;

}
