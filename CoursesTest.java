import static org.junit.Assert.*;

public class CoursesTest {

    @org.junit.Test
    public void addNode() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        c.addNode(newC);
        assertTrue(c.getGraph().containsKey(newC));
    }
    @org.junit.Test
    public void addNode2() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");

        c.addNode(newC);
        c.addNode(class2);
        assertTrue(c.getGraph().containsKey(class2));
    }

    @org.junit.Test
    public void addEdge() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");

        c.addNode(newC);
        c.addNode(class2);
        c.addEdge(newC, class2);
        assertEquals(((c.getGraph()).get(class2)).size(), 1);
    }
    @org.junit.Test
    public void addEdge2() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");

        c.addNode(newC);
        c.addNode(class2);
        assertEquals(((c.getGraph()).get(class2)).size(), 0);
    }
    @org.junit.Test
    public void addEdge3() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");

        c.addNode(newC);
        c.addNode(class2);
        c.addEdge(newC, class2);
        assertEquals(((c.getGraph()).get(newC)).size(), 1);
    }
    @org.junit.Test
    public void addEdge4() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");

        c.addNode(newC);
        c.addNode(class2);
        c.addEdge(newC, class2);
        c.addEdge(newC, class3);
        assertEquals(((c.getGraph()).get(newC)).size(), 2);
    }

    @org.junit.Test
    public void hasConflict() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");

        c.addNode(newC);
        c.addNode(class2);
        c.addEdge(newC, class2);
        c.addEdge(newC, class3);
        assertTrue(c.hasConflict(newC, class2));
    }
    @org.junit.Test
    public void hasConflict2() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");

        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        c.addEdge(newC, class2);
        assertFalse(c.hasConflict(newC, class3));
    }

    @org.junit.Test
    public void getNodeCount() {
        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");

        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        c.addEdge(newC, class2);
        assertEquals(c.getNodeCount(), 3);
    }

    @org.junit.Test
    public void getEdgeCount() {

            Courses c = new Courses();
            Course newC = new Course("316", "Chemistry", 312, "Handanovich");
            Course class2 = new Course("212", "Gym", 112, "Martinez");
            Course class3 = new Course("12", "Hockey", 4, "Mayo");

            c.addNode(newC);
            c.addNode(class2);
            c.addNode(class3);
            c.addEdge(newC, class2);
            assertEquals(c.getEdgeCount(), 1);

    }

    @org.junit.Test
    public void getEdgeCount2() {

        Courses c = new Courses();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich");
        Course class2 = new Course("212", "Gym", 112, "Martinez");
        Course class3 = new Course("12", "Hockey", 4, "Mayo");

        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        c.addEdge(newC, class3);
        c.addEdge(class2, class3);
        c.addEdge(newC, class2);
        assertEquals(c.getEdgeCount(), 3);

    }


}