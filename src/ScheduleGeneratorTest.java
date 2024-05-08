import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class ScheduleGeneratorTest {

    public static ScheduleGenerator sg = new ScheduleGenerator();





    @Test
    public void courseFiller() {
        ScheduleGenerator sgs = new ScheduleGenerator();
        sgs.courseFiller();
        assertEquals(sgs.getCourses().getGraph().size(), 8);
    }

    @Test
    public void roomFiller() {
        ScheduleGenerator sgs = new ScheduleGenerator();
        sgs.roomFiller();
        assertEquals(sgs.getRooms().getRoomById(1).getCapacity(), 50);
    }


    @Test
    public void printRowDept() throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("output.txt");

        // Create a PrintStream from the FileOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(fileOutputStream);
        String out = "";
        // Redirect s  tdout to the PrintStream
        System.setOut(printStream);
        printStream.flush();

        // Convert the data in the ByteArrayOutputStream to a string
        String output = byteArrayOutputStream.toString();

        ScheduleGenerator sgs = new ScheduleGenerator();
        Room r = new Room(1, 29, "2", "Mar1", "UFC");
        Rooms rs = new Rooms();
        rs.addRoom(r);
        Course c = new Course("1", "UFC", 12, "Roger", rs);
        TimeSlot ts = new TimeSlot(1, "8AM", "10AM", "MWF");
        CalendarTile ct = new CalendarTile(r, c, ts);
        CalendarTile cw = null;
        HashMap<String, List<CalendarTile>> mwfClasses = new HashMap<String, List<CalendarTile>>();
        ArrayList<CalendarTile> cwl = new ArrayList<CalendarTile>();
        mwfClasses.put("MWF", cwl);
        HashMap<String, List<CalendarTile>> ttClasses = new HashMap<String, List<CalendarTile>>();
        ttClasses.put("MWF", cwl);
        cwl.add(ct);
        ArrayList<CalendarTile> ctl = null;
        sgs.printRowDept(1, mwfClasses, ttClasses);
        assertEquals(output, "");
    }

    @Test
    public void deptRowSetter() {
        ScheduleGenerator sgs = new ScheduleGenerator();
        Room r = new Room(1, 29, "2", "Mar1", "UFC");
        Rooms rs = new Rooms();
        rs.addRoom(r);
        Course c = new Course("1", "UFC", 12, "Roger", rs);
        TimeSlot ts = new TimeSlot(1, "8AM", "10AM", "MWF");
        CalendarTile ct = new CalendarTile(r, c, ts);
        CalendarTile cw = null;
        ArrayList<CalendarTile> cwl = new ArrayList<CalendarTile>();
        cwl.add(ct);
        ArrayList<CalendarTile> ctl = null;
        String expected = "Course ID: 1                            Course ID: 1                            Course ID: 1 ";
        assertEquals(sgs.deptRowSetter(cwl, ctl, "Course ID: "), expected);
    }


    @Test
    public void rowSetter() {
        ScheduleGenerator sgs = new ScheduleGenerator();
        Room r = new Room(1, 29, "2", "Mar1", "UFC");
        Rooms rs = new Rooms();
        rs.addRoom(r);
        Course c = new Course("1", "UFC", 12, "Roger", rs);
        TimeSlot ts = new TimeSlot(1, "8AM", "10AM", "MWF");
        CalendarTile ct = new CalendarTile(r, c, ts);
        CalendarTile cw = null;
        String expected = "Course ID: 1                            Course ID: 1                            Course ID: 1";
        assertEquals(sgs.rowSetter(ct, cw, "Course ID: "), expected);
    }


    @Test
    public void timeMapFiller() {
        ScheduleGenerator sgs = new ScheduleGenerator();
        sgs.timeMapFiller();
        assertEquals(sgs.getTimeMap().keySet().size(), 26);

    }
}