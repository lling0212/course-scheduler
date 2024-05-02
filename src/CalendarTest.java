import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class CalendarTest {

    private Courses g;

    public void setup() {
        Rooms rooms = new Rooms();

        Course A = new Course("C1",
                    "D1", 50, "name1", rooms);
        A.setRoom(1);
        Course B = new Course("C2",
                "D1", 100, "name2", rooms);
        B.setRoom(2);
        Course C = new Course("C3",
                "D1", 20, "name1", rooms);
        C.setRoom(1);
        Course D = new Course("C4",
                "D2", 50, "name3", rooms);
        D.setRoom(3);
        Course E = new Course("C5",
                "D2", 100, "name3", rooms);
        E.setRoom(4);
        Course F = new Course("C6",
                "D2", 50, "name4", rooms);
        F.setRoom(4);

        g = new Courses();
        g.addNode(A);
        g.addNode(B);
        g.addNode(C);
        g.addNode(D);
        g.addNode(E);
        g.addNode(F);

        g.addAllEdges();
    }

    @Test
    public void testGenerateConflictSets() {
        setup();
        Calendar calendar = new Calendar(g);
        List<Set<Course>> result = calendar.generateConflictSets();
        assertEquals(2, result.size());

        Set<Course> setOne = result.get(0);
        Set<Course> setTwo = result.get(1);
        assertEquals(3, setOne.size());
        assertEquals(3, setTwo.size());

        Set<String> setOneCrs = new HashSet<>();
        for (Course crs : setOne) {
            setOneCrs.add(crs.getCourseID());
        }

        Set<String> expectedOne = new HashSet<>();
        expectedOne.add("C1");
        expectedOne.add("C2");
        expectedOne.add("C3");

        Set<String> expectedTwo = new HashSet<>();
        expectedOne.add("C4");
        expectedOne.add("C5");
        expectedOne.add("C6");

        boolean testResult = (setOneCrs.containsAll(expectedOne) ||
                setOne.contains(expectedTwo));
        assertTrue(testResult);
    }

    @Test
    public void testGenerateIndepSets() {
        setup();
        Calendar calendar = new Calendar(g);
        List<Set<Course>> conflictSets = calendar.generateConflictSets();
        List<Set<Course>> result = calendar.generateIndepSets(conflictSets);

        assertEquals(3, result.size());

        Set<Course> setOne = result.get(0);
        Set<Course> setTwo = result.get(1);
        Set<Course> setThree = result.get(2);
        assertEquals(2, setOne.size());
        assertEquals(2, setTwo.size());
        assertEquals(2, setThree.size());

        List<Course> one = new ArrayList<>();
        for (Course c : setOne) {
            one.add(c);
        }
        assertFalse(g.hasConflict(one.get(0), one.get(1)));

        List<Course> two = new ArrayList<>();
        for (Course c : setTwo) {
            two.add(c);
        }
        assertFalse(g.hasConflict(two.get(0), two.get(1)));

        List<Course> three = new ArrayList<>();
        for (Course c : setThree) {
            three.add(c);
        }
        assertFalse(g.hasConflict(three.get(0), three.get(1)));

    }

    @Test
    public void testGenerateSchedule() {
        setup();
        Calendar calendar = new Calendar(g);
        calendar.generateSchedule();

        List<CalendarTile> schedule = calendar.getSchedule();
        assertEquals(6, schedule.size());
        for (int i = 0; i < schedule.size() - 1; i++) {
            for (int j = i + 1; j < schedule.size(); j++) {
                CalendarTile tile1 = schedule.get(i);
                CalendarTile tile2 = schedule.get(j);

                if (tile1.getTimeSlot() == tile2.getTimeSlot()) {
                    assertFalse(g.hasConflict(tile1.getCourse(), tile2.getCourse()));
                }
            }
        }

    }

    @Test
    public void testGetCourseByDepartment() {
        setup();
        Calendar calendar = new Calendar(g);
        calendar.generateSchedule();
        List<CalendarTile> coursesD1 = calendar.getCourseByDepartment("D1");

        assertEquals(3, coursesD1.size());
        for (CalendarTile tile : coursesD1) {
            assertEquals("D1", tile.getCourse().getDepartment());
        }
    }

    @Test
    public void testGetCourseByInstructor() {
        setup();
        Calendar calendar = new Calendar(g);
        calendar.generateSchedule();

        List<CalendarTile> coursesInstr1 = calendar.getCourseByInstructor("name1");
        assertEquals(2, coursesInstr1.size());
        for (CalendarTile tile : coursesInstr1) {
            assertEquals("name1", tile.getCourse().getInstructor());
        }

        List<CalendarTile> coursesInstr3 = calendar.getCourseByInstructor("name3");
        assertEquals(2, coursesInstr3.size());
        for (CalendarTile tile : coursesInstr3) {
            assertEquals("name3", tile.getCourse().getInstructor());
        }
    }


}
