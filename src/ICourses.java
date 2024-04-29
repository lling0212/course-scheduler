import java.util.List;
import java.util.Map;

/**
 * Interface for the Courses class that houses the graph with Course as nodes
 * and unweighted edges representing conflicts between a pair of courses
 */

public interface ICourses {

    /**
     * Adds a node to the graph by adding it to the map that represents the graph
     * as a key and an empty list as the value (to store its neighbors). Increment
     * node count
     *
     * @param course the Course object we want to add as a node
     * @return true if add was successful; false if node already in map
     */
    boolean addNode(Course course);

    /**
     * Adds an edge between 2 different courses to represent that a conflict exists
     * between them. Can be used as helper function for when trying to add all the
     * edges in the graph. Remember that the graph is undirected - so we need to
     * add the edge to both the nodes
     *
     * @param course1 the first Course in the conflict pair
     * @param course2 the second Course in the conflict pair
     *
     * @return true if add was successful (do nothing if edge already exists);
     *         false if 2 courses are the same.
     */
    
    boolean addEdge(Course course1, Course course2);

    /**
     * Adds all the edges to the graph. Used after all the course nodes are added.
     * Do this by using a nested loop to check each pair of courses. Use helper function
     * hasConflict to check if the pair has conflicts between them. If so, add an
     * edge between the two nodes
     *
     */
    void addAllEdges();

    /**
     * Checks if the courses have conflicts between them. (Conflict means that either the
     * 2 courses have the same instructor OR they need the same room)
     *
     * @param course1 the first Course in the pair
     * @param course2 the second Course in the pair
     *
     * @return true if conflict exists;
     *         false if 2 courses are the same or no conflicts
     */
    boolean hasConflict(Course course1, Course course2);

    /**
     * @return the count of nodes (courses) in the graph
     */
    int getNodeCount();

    /**
     * @return the count of edges in the graph (count one conflict as one edge)
     */
    int getEdgeCount();

    /**
     * @return the entire graph containing all the nodes and edges
     */
    Map<Course, List<Course>> getGraph();


}
