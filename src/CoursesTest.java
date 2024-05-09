import org.junit.Test;

import static org.junit.Assert.*;

public class CoursesTest {

    @Test
    public void addNode() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        c.addNode(newC);
        assertTrue(c.getGraph().containsKey(newC));
    }

    @Test
    public void addNodeExist() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        c.addNode(newC);
        assertFalse(c.addNode(newC));
    }

    @Test
    public void addNode2() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);

        c.addNode(newC);
        c.addNode(class2);
        assertTrue(c.getGraph().containsKey(newC));
        assertTrue(c.getGraph().containsKey(class2));
    }

    @Test
    public void addEdge() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();

        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);

        c.addNode(newC);
        c.addNode(class2);
        c.addEdge(newC, class2);
        assertEquals(1, ((c.getGraph()).get(class2)).size());
        assertEquals(1, ((c.getGraph()).get(newC)).size());
    }

    @Test
    public void addEdgeInvalid() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();

        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);
        assertTrue(c.addEdge(newC, class2));
        c.addNode(newC);
        assertFalse(c.addEdge(newC, class2));

    }

    @Test
    public void addEdge2() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);

        c.addNode(newC);
        c.addNode(class2);
        assertEquals(0, ((c.getGraph()).get(class2)).size());
        assertEquals(0, ((c.getGraph()).get(newC)).size());
    }

    @Test
    public void addEdge3() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);

        c.addNode(newC);
        c.addNode(class2);
        c.addEdge(newC, class2);
        c.addEdge(newC, class2); // adding same edge shouldn't do anything
        assertEquals(1, ((c.getGraph()).get(newC)).size());
        assertEquals(1, ((c.getGraph()).get(class2)).size());
    }

    @Test
    public void addEdge4() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);
        Course class3 = new Course("12", "Hockey", 4, "Mayo", rooms);

        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        c.addEdge(newC, class2);
        c.addEdge(newC, class3);
        assertEquals(2, ((c.getGraph()).get(newC)).size());
        assertEquals(1, ((c.getGraph()).get(class2)).size());
        assertEquals(1, ((c.getGraph()).get(class3)).size());
    }

    @Test
    public void hasConflict() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();

        Room room1 = new Room(1, 350, "101", "Chem", "Chemistry");
        Room room2 = new Room(2, 150, "301", "Gym", "Gym");
        Room room3 = new Room(3, 20, "200", "Hockey", "Hockey");
        rooms.addRoom(room1);
        rooms.addRoom(room2);
        rooms.addRoom(room3);

        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);
        Course class3 = new Course("12", "Hockey", 4, "Mayo", rooms);
        // NO conflicts
        assertFalse(c.hasConflict(newC, class2));
        assertFalse(c.hasConflict(newC, class3));
        assertFalse(c.hasConflict(class3, class2));
    }

    @Test
    public void hasConflict2() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Room room1 = new Room(1, 50, "301", "Gym", "Gym");
        Room room2 = new Room(2, 150, "100", "Gym", "Gym");
        rooms.addRoom(room1);
        rooms.addRoom(room2);

        Course newC = new Course("211", "Gym", 110, "Martinez", rooms);
        Course class2 = new Course("212", "Gym", 20, "Martinez", rooms);
        Course class3 = new Course("112", "Gym", 4, "Mayo", rooms);

        assertTrue(c.hasConflict(newC, class2)); // same instructor: Martinez
        assertTrue(c.hasConflict(class2, class3)); // same room : 1
    }

    @Test
    public void testAddAllEdges() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Room room1 = new Room(1, 50, "301", "Gym", "Gym");
        Room room2 = new Room(2, 150, "100", "Gym", "Gym");
        rooms.addRoom(room1);
        rooms.addRoom(room2);

        Course newC = new Course("211", "Gym", 110, "Martinez", rooms);
        Course class2 = new Course("212", "Gym", 20, "Martinez", rooms);
        Course class3 = new Course("112", "Gym", 4, "Mayo", rooms);

        // class2, class3 should need room 1; newC should need room 2
        assertEquals(2, newC.getRoom());
        assertEquals(1, class2.getRoom());
        assertEquals(1, class3.getRoom());

        // construct the graph
        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        c.addAllEdges();

        // newC and class2 should have an edge, class2 and class3 should have an edge
        assertEquals(3, c.getNodeCount());
        assertEquals(2, c.getEdgeCount());

        assertTrue(c.getGraph().get(class2).contains(newC));
        assertTrue(c.getGraph().get(class2).contains(class3));
        assertTrue(c.getGraph().get(class3).contains(class2));
        assertTrue(c.getGraph().get(newC).contains(class2));

        assertFalse(c.getGraph().get(newC).contains(class3));
        assertFalse(c.getGraph().get(class3).contains(newC));
    }

    @Test
    public void getNodeCount() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);
        Course class3 = new Course("12", "Hockey", 4, "Mayo", rooms);

        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        c.addEdge(newC, class2);
        assertEquals(3, c.getNodeCount());
    }

    @Test
    public void getEdgeCount() {

        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);
        Course class3 = new Course("12", "Hockey", 4, "Mayo", rooms);

        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        c.addEdge(newC, class2);
        assertEquals(1, c.getEdgeCount());

    }

    @Test
    public void getEdgeCount2() {

        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);
        Course class3 = new Course("12", "Hockey", 4, "Mayo", rooms);

        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        c.addEdge(newC, class3);
        c.addEdge(class2, class3);
        c.addEdge(newC, class2);
        assertEquals(3, c.getEdgeCount());

    }

    @Test
    public void getAllCourses() {
        Courses c = new Courses();
        Rooms rooms = new Rooms();
        Course newC = new Course("316", "Chemistry", 312, "Handanovich", rooms);
        Course class2 = new Course("212", "Gym", 112, "Martinez", rooms);
        Course class3 = new Course("12", "Hockey", 4, "Mayo", rooms);
        c.addNode(newC);
        c.addNode(class2);
        c.addNode(class3);
        assertEquals(3, c.getAllCourses().size());
    }

}