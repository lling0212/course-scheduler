import java.util.*;

/**
 * Implements ICourses
 */

public class Courses implements ICourses{
    Map<Course, List<Course>> graph = new HashMap<Course, List<Course>>();
    private int nodeCount = 0;
    private int edgeCount = 0;

    @Override
    public boolean addNode(Course course) {
        if (graph.containsKey(course)) {
            return false;
        }
        else {
            graph.put(course, new ArrayList<Course>());
            nodeCount++;
        }
        return true;
    }

    @Override
    public boolean addEdge(Course course1, Course course2) {
        if (!graph.containsKey(course1)) {
            this.addNode(course1);
        }
        if (!graph.containsKey(course2)) {
            this.addNode(course2);
        }
        List<Course> list = graph.get(course1);
        if (list.size() > 0 && list.contains(course2)) {
            return false;
        }
        list.add(course2);
        List<Course> list2 = graph.get(course2);
        if (list2.size() > 0 && list2.contains(course1)) {
            return false;
        }
        list2.add(course1);
        edgeCount++;
        return true;
    }

    @Override
    public void addAllEdges() {
        Set<Course> nodes = graph.keySet();
        for (Course course1 : nodes) {
            for (Course course2 : nodes) {
                if (course1.equals(course2)) {
                    continue;
                }
                if (hasConflict(course1, course2)) {
                    addEdge(course1, course2);
                }
            }
        }
    }

    @Override
    public boolean hasConflict(Course course1, Course course2) {
        String a = course1.getInstructor();
        String b = course2.getInstructor();
        if (a == null || b == null) {
            return false;
        }
        int c = course1.getRoom();
        int d = course2.getRoom();
        if (c == -1 || d == -1) {
            return false;
        }
        return a.equals(b) || c == d;
    }

    @Override
    public int getNodeCount() {
        return nodeCount;
    }

    @Override
    public int getEdgeCount() {
        return edgeCount;
    }

    @Override
    public Map<Course, List<Course>> getGraph() {
        return graph;
    }


    List<Course> getAllCourses() {
        List<Course> list = new ArrayList<Course>();
        Set<Course> nodes = graph.keySet();
        for (Course course : nodes) {
            list.add(course);
        }
        return list;
    }
}
