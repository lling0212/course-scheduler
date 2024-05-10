import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Interface for the Calendar class that generates valid schedules based on
 * the graph of Course from Courses
 */

public interface ICalendar {

    /**
     * Generate one non-conflicting schedule based on the input graph (Courses)
     *
     * First use helper function generateConflictSets to get the connected
     * components of the graph (nodes within each component have some conflicts
     * among themselves)
     *
     * Next use helper function generateIndepSets to get the independent sets
     * containing courses among which no conflicts exist, based on result from
     * generateConflictSets
     *
     * Lastly, map each independent set to a unique timeslot (one of the 10
     * created in TimeSlotFactory; randomly assign) and store the result in
     * schedule. The result is represented by a list of CalendarTiles (mapping
     * between a Course and its assigned timeslot and room).
     * For example if we have [A, D], [B, E], [C] as the independent sets and map
     * them each to timeslot 1, 2 and 3, the returned list will contain CalendarTile1
     * (A, 1, the room A was mapped to when A was first constructed)
     * Note that we do not need to map the course to a room in this method - this is
     * already done when the Course objects are first constructed
     *
     * At the same time, fill in scheduleByDepartment and scheduleByInstructor.
     *
     */
    void generateSchedule();

    /**
     * Use Union-Find to generate sets; each set is a connected component within the graph
     * Implement union() and find() helper methods
     *
     * @return a list of sets, each set representing a connected component of courses
     */
    List<Set<Course>> generateConflictSets();

    /**
     * Based on the result of union find, generate a list of independent sets (a set of
     * nodes with no edges aka conflicts among them) by picking 1 node out of each connected
     * component and creating an independent set with them, until no nodes are left in any
     * of the sets
     *
     * Note that ideally we want the maximal independent sets (as many nodes as possible in each
     * set and as few independent sets in total as possible). This method does not generate
     * the most optimal solution but is of reasonable complexity and will take less time and space
     * Example: if [A, B, C] and [D, E] are the connected components, the result can be [A, D],
     * [B, E], [C]
     *
     * @return a list of sets, each set containing courses where no 2 course nodes have a
     * conflict between them
     */
    List<Set<Course>> generateIndepSets(List<Set<Course>> conflictSets);

    /**
     *
     * @param department
     * @return a list of CalendarTiles containing courses in the specified department;
     * only called after schedule is generated
     */
    List<CalendarTile> getCourseByDepartment(String department);

    /**
     *
     * @param instructor
     * @return a list of CalendarTiles containing courses taught by the specified instructor;
     * only called after schedule is generated
     */
    List<CalendarTile> getCourseByInstructor(String instructor);

    /**
     *
     * @return a list of CalendarTile containing the entire schedule
     */
    List<CalendarTile> getSchedule();

    /**
     * Takes in a schedule and prints it out to terminal in a formatted manner
     */
    void printSchedule(List<CalendarTile> schedule);

}
