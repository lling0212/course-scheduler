import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * implements ICalendar
 */
public class Calendar implements ICalendar {

    private Courses courses;
    private List<CalendarTile> schedule;
    private List<CalendarTile> scheduleWithPref;
    private Map<String, CalendarTile> scheduleByDepartment;
    private Map<String, CalendarTile> scheduleByInstructor;

    /**
     * Constructor takes in a Courses object and initialize all schedules to be empty initially
     *
     */
    Calendar(Courses courses) {
        this.courses = courses;
    }

    @Override
    public void generateSchedule() {

    }

    @Override
    public List<Set<Course>> generateConflictSets() {
        return null;
    }

    @Override
    public List<Set<Course>> generateIndepSets(List<Set<Course>> conflictSets) {
        return null;
    }

    @Override
    public List<CalendarTile> getCourseByDepartment(String department) {
        return null;
    }

    @Override
    public List<CalendarTile> getCourseByInstructor(String instructor) {
        return null;
    }

    @Override
    public void generateScheduleWithPref() {

    }

    @Override
    public void printSchedule(List<CalendarTile> schedule) {

    }
}
