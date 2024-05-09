import org.junit.Test;
import static org.junit.Assert.*;

public class CourseTest {

    @Test
    public void testRoomAssignment() {
        Rooms rooms = new Rooms();
        Room room1 = new Room(1, 350, "101", "Chem", "Dep");
        Room room2 = new Room(2, 150, "301", "Gym", "Dep");
        Room room3 = new Room(3, 20, "200", "Hockey", "Dep");
        rooms.addRoom(room3);
        rooms.addRoom(room2);
        rooms.addRoom(room1);

        Course crs1 = new Course("1", "Dep", 300, "Handanovich", rooms);
        Course crs2 = new Course("2", "Dep", 5, "Handanovich", rooms);
        Course crs3 = new Course("3", "Dep", 25, "Handanovich", rooms);

        assertEquals(1, crs1.getRoom());
        assertEquals(3, crs2.getRoom());
        assertEquals(2, crs3.getRoom());
    }

    @Test
    public void testGetterSetter() {
        Rooms rooms = new Rooms();
        Course crs = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        crs.setRoom(1);
        crs.setCourseID("1");
        crs.setDepartment("Dep");
        crs.setCapactiy(200);
        crs.setInstructor("Instructor");

        assertEquals(1, crs.getRoom());
        assertEquals("1", crs.getCourseID());
        assertEquals("Dep", crs.getDepartment());
        assertEquals(200, crs.getCapacity());
        assertEquals("Instructor", crs.getInstructor());
    }

    @Test
    public void testCourseEqual() {
        Rooms rooms = new Rooms();
        Course crs1 = new Course("1", "Dep", 300, "Handanovich", rooms);
        Course crs2 = new Course("1", "Dep", 300, "Handanovich", rooms);;
        assertTrue(crs1.equals(crs2));
    }

    @Test
    public void testCourseToString() {
        Rooms rooms = new Rooms();
        Course crs1 = new Course("CIT 5940", "CIS", 100, "Smith", rooms);
        String expect = "CourseID: " + "CIT 5940" + "\n"
                + "Department: " + "CIS" + "\n"
                + "Instructor: " + "Smith" + "\n";
        assertEquals(expect, crs1.toString());
    }

    @Test
    public void testCalendarTileString() {
        Rooms rooms = new Rooms();
        Room room1 = new Room(1, 350, "101", "Chem", "Dep");
        Course crs = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        CalendarTile tile = new CalendarTile(room1, crs, TimeSlotFactory.getTimeSlot(10));
        String expect = "CourseID: " + "316" + "\n"
                + "Department: " + "Chemistry" + "\n"
                + "Instructor: " + "Handanovich" + "\n"
                + "Location: " + "101" + ", " + "Chem\n";
        assertEquals(expect, tile.toString());
    }

    @Test
    public void testTimeSlot() {
        assertEquals("4PM", TimeSlotFactory.getTimeSlot(10).getStartTime());
        assertEquals("TTh", TimeSlotFactory.getTimeSlot(10).getDaysInWeek());
    }

}
