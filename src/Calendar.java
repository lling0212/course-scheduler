import java.util.*;

/**
 * implements ICalendar
 */
public class Calendar implements ICalendar {

    private final Courses courses;
    private final List<CalendarTile> schedule = new ArrayList<CalendarTile>();
    private final Map<String, List<CalendarTile>> scheduleByDepartment = new HashMap<>();
    private final Map<String, List<CalendarTile>> scheduleByInstructor = new HashMap<>();


    private int[] parent;
    private int[] size;


    /**
     * Constructor takes in a Courses object and initialize all schedules to be empty initially
     *
     */
    Calendar(Courses courses) {
        this.courses = courses;
    }

    @Override
    public void generateSchedule() {
        List<Set<Course>> conflictSets = generateConflictSets();
        List<Set<Course>> indepSets = generateIndepSets(conflictSets);
        int id = 1;
        for (Set<Course> indepSet : indepSets) {
            TimeSlot timeSlot = TimeSlotFactory.getTimeSlot(id);
            for (Course course : indepSet) {
                Room room = new Room(course.getRoom(), course.getCapacity(), null, null, course.getDepartment());
                CalendarTile calendarTile = new CalendarTile(room, course, timeSlot);
                schedule.add(calendarTile);
                if (!scheduleByDepartment.containsKey(course.getDepartment())) {
                    scheduleByDepartment.put(course.getDepartment(), new ArrayList<>());
                }
                scheduleByDepartment.get(course.getDepartment()).add(calendarTile);
                if (!scheduleByInstructor.containsKey(course.getInstructor())) {
                    scheduleByInstructor.put(course.getInstructor(), new ArrayList<>());
                }
                scheduleByInstructor.get(course.getInstructor()).add(calendarTile);
            }
            id++;
        }
    }


    private int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    private void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        if (size[rootX] < size[rootY]) {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        } else {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        }
    }

    @Override
    public List<Set<Course>> generateConflictSets() {
        List<Course> allCourses = courses.getAllCourses();
        Map<Course, Integer> mapping = new HashMap<>();
        List<List<Integer>> adj = new ArrayList<>();
        parent = new int[allCourses.size()];
        size = new int[allCourses.size()];
        for (int i = 0; i < allCourses.size(); i++) {
            mapping.put(allCourses.get(i), i);
            adj.add(new ArrayList<>());
            parent[i] = i;
            size[i] = 1;
        }
        Map<Course, List<Course>> graph = courses.getGraph();
        for (int i = 0; i < allCourses.size(); i++) {
            Course course = allCourses.get(i);
            List<Course> connectedNodes = graph.get(course);
            for (Course node : connectedNodes) {
                Integer index = mapping.get(node);
                adj.get(i).add(index);
                adj.get(index).add(i);
            }
        }
        for (int i = 0; i < allCourses.size(); i++) {
            for (int j : adj.get(i)) {
                this.union(i, j);
            }
        }

        Map<Integer, List<Integer>> components = new HashMap<>();
        for (int i = 0; i < allCourses.size(); i++) {
            int root = this.find(i);
            if (!components.containsKey(root)) {
                components.put(root, new ArrayList<>());
            }
            components.get(root).add(i);
        }
        List<Set<Course>> conflictSets = new ArrayList<>();
        Set<Integer> roots = components.keySet();
        for (Integer i : roots) {
            List<Integer> nodeIndex = components.get(i);
            Set<Course> conflictSet = new HashSet<>();
            for (Integer index : nodeIndex) {
                conflictSet.add(allCourses.get(index));
            }
            conflictSets.add(conflictSet);
        }
        return conflictSets;
    }


    @Override
    public List<Set<Course>> generateIndepSets(List<Set<Course>> conflictSets) {
        List<Set<Course>> result = new ArrayList<>();
        List<List<Course>> indepLists = new ArrayList<>();
        int maxLen = 0;

        for (int i = 0; i < conflictSets.size(); i++) {
            List<Course> src = new ArrayList<>(conflictSets.get(i));
            indepLists.add(src);
            maxLen = Math.max(maxLen, src.size());
        }

        for (int j = 0; j < maxLen; j++) {
            Set<Course> set = new HashSet<>();
            for (int k = 0; k < conflictSets.size(); k++) {
                if (j < indepLists.get(k).size()) {
                    set.add(indepLists.get(k).get(j));
                }
            }
            result.add(set);
        }

        return result;
    }

    @Override
    public List<CalendarTile> getCourseByDepartment(String department) {
        List<CalendarTile> result = new ArrayList<>();
        for (CalendarTile ct : this.schedule) {
            String myDepartment = ct.getCourse().getDepartment();
            if (Objects.equals(myDepartment, department)) {
                result.add(ct);
            }
        }
        return result;
    }

    @Override
    public List<CalendarTile> getCourseByInstructor(String instructor) {
        List<CalendarTile> result = new ArrayList<>();
        for (CalendarTile ct : this.schedule) {
            if (ct.getCourse().getInstructor().equals(instructor)) {
                result.add(ct);
            }
        }
        return result;
    }

    @Override
    public void printSchedule(List<CalendarTile> schedule) {
        for (CalendarTile ct : schedule) {
            System.out.println(ct.toString());
        }
    }

    @Override
    public List<CalendarTile> getSchedule() {

        return schedule;
    }


}
