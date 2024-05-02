import java.util.List;
import java.util.Map;

/**
 * Implements ICourses
 */

public class Courses implements ICourses{
    Map<Course, List<Course>> graph;
    private int nodeCount;
    private int edgeCount;

    @Override
    public boolean addNode(Course course) {
        return false;
    }

    @Override
    public boolean addEdge(Course course1, Course course2) {
        return false;
    }

    @Override
    public void addAllEdges() {

    }

    @Override
    public boolean hasConflict(Course course1, Course course2) {
        return false;
    }

    @Override
    public int getNodeCount() {
        return 0;
    }

    @Override
    public int getEdgeCount() {
        return 0;
    }

    @Override
    public Map<Course, List<Course>> getGraph() {
        return null;
    }
}
