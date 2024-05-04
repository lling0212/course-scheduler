import org.junit.Test;

import static org.junit.Assert.*;

public class CalendarTest {

    @Test
    public void generateSchedule() {
    }

    @Test
    public void generateConflictSets() {
    }

    @Test
    public void generateIndepSets() {
    }

    @Test
    public void getCourseByDepartment() {
        Course newC = new Course("316", "Hockey", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");
        Courses co = new Courses();
        co.addNode(newC);
        co.addNode(class2);
        co.addNode(class3);
        Calendar cal = new Calendar(co);
        assertEquals(cal.getCourseByDepartment("Hockey").size(), 2);
    }
    @Test
    public void getCourseByDepartment2() {
        Course newC = new Course("316", "Hockey", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");
        Courses co = new Courses();
        co.addNode(newC);
        co.addNode(class2);
        co.addNode(class3);
        Calendar cal = new Calendar(co);
        assertEquals(cal.getCourseByDepartment("Gym").size(), 1);
    }
    @Test
    public void getCourseByDepartment3() {
        Course newC = new Course("316", "Hockey", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");
        Courses co = new Courses();
        co.addNode(newC);
        co.addNode(class2);
        co.addNode(class3);
        Calendar cal = new Calendar(co);
        assertEquals(cal.getCourseByDepartment("Pittsburgh").size(), 0);
    }

    @Test
    public void getCourseByInstructor() {
        Course newC = new Course("316", "Hockey", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");
        Courses co = new Courses();
        co.addNode(newC);
        co.addNode(class2);
        co.addNode(class3);
        Calendar cal = new Calendar(co);
        assertEquals(cal.getCourseByInstructor("Martinez").size(), 1);
    }
    @Test
    public void getCourseByInstructor2() {
        Course newC = new Course("316", "Hockey", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Martinez");
        Courses co = new Courses();
        co.addNode(newC);
        co.addNode(class2);
        co.addNode(class3);
        Calendar cal = new Calendar(co);
        assertEquals(cal.getCourseByInstructor("Martinez").size(), 2);
    }
    @Test
    public void getCourseByInstructor3() {
        Course newC = new Course("316", "Hockey", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Martinez");
        Courses co = new Courses();
        co.addNode(newC);
        co.addNode(class2);
        co.addNode(class3);
        Calendar cal = new Calendar(co);
        assertEquals(cal.getCourseByInstructor("Elk").size(), 0);
    }

    @Test
    public void generateScheduleWithPref() {
    }

    @Test
    public void printSchedule() {
    }
}