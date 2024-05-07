import java.util.*;

public class ScheduleGenerator {
    private static boolean loop = true;
    private static Courses courses = new Courses();
    private static Rooms rooms = new Rooms();
    private static Calendar cal;
    private static HashMap<Integer, String> timeMap = new HashMap<Integer, String>();



    public static void main(String[] args) {
        runner();
    }

    public static void runner() {
        roomFiller();
        courseFiller();
        timeMapFiller();
        boolean tru = true;
        while(tru) {
            loop = true;
            Scanner sc = new Scanner(System.in);
            System.out.println("Hello, are you a professor or an administrator?");
            String classification = sc.next();
            while (loop) {
                if (Objects.equals(classification, "professor")) {
                    Course course = prof();
                    courses.addAllEdges();
                    cal = new Calendar(courses);
                    printInstructorSchedule(cal, course.getInstructor());
                    if (exit()) {
                        loop = false;
                        tru = false;
                    }
                } else if (Objects.equals(classification, "administrator")) {
                    System.out.println("Enter which department you would like to schedule: ");
                    String dept = sc.next();
                    courses.addAllEdges();
                    cal = new Calendar(courses);
                    printDepartmentSchedule(cal, dept);
                    loop = false;
                    tru = false;
                }
                if(!Objects.equals(classification, "professor") &&
                        !Objects.equals(classification, "administrator")){
                    loop = false;
                }
            }
            if(!tru) {
                sc.close();
            }
        }
    }



    public static boolean exit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to enter a new course? (yes or no)");
        String answer = sc.next();
        return answer.equals("no");
    }

    /**
     * fills in filler courses to build graph off of
     */
    public static void courseFiller() {
        Course course1 = new Course("1", "Chemistry", 25, "Martinez", rooms);
        Course course4 = new Course("17", "Chemistry", 45, "Martinez", rooms);
        Course course5 = new Course("19", "Chemistry", 55, "Martinez", rooms);
        Course course2 = new Course("2", "Alchemy", 30, "Rocker", rooms);
        Course course3 = new Course("3", "Spanish", 15, "Marshall", rooms);
        Course course6 = new Course("12", "Spanish", 29, "Marshall", rooms);
        Course course7 = new Course("31", "Spanish", 39, "Maldonado", rooms);
        Course course8 = new Course("122", "Spanish", 58, "Art", rooms);
        courses.addNode(course1);
        courses.addNode(course2);
        courses.addNode(course3);
        courses.addNode(course4);
        courses.addNode(course5);
        courses.addNode(course6);
        courses.addNode(course7);
        courses.addNode(course8);
    }

    /** Fills in set of rooms available
     *
     */
    public static void roomFiller() {
        Room room1 = new Room(1, 30, "1", "Bart", "Chemistry");
        Room room2 = new Room(2, 29, "2", "Macao", "Spanish");
        Room room3 = new Room(3, 40, "3", "Bart", "Chemistry");
        Room room4 = new Room(4, 25, "4", "Philly", "Alchemy");
        Room room5 = new Room(5, 30, "5", "Macao", "Spanish");
        Room room6 = new Room(6, 35, "6", "Philly", "Alchemy");
        Room room7 = new Room(7, 40, "7", "Macao", "Spanish");
        Room room8 = new Room(8, 45, "8", "Dexter", "Geography");


        rooms.addRoom(room1);
        rooms.addRoom(room2);
        rooms.addRoom(room3);
        rooms.addRoom(room4);
        rooms.addRoom(room5);
        rooms.addRoom(room6);
        rooms.addRoom(room7);
        rooms.addRoom(room8);

    }

    /**
     * Prints out a schedule by department
     * @param cal calendar to print out schedule for
     * @param name name of department to print
     */

    public static void printDepartmentSchedule(Calendar cal, String name) {
        cal.generateSchedule();
        List<CalendarTile> instruc = cal.getCourseByDepartment(name);
        printTimes();

        HashMap<String, CalendarTile> mwfClasses = new HashMap<String, CalendarTile>();
        HashMap<String, CalendarTile> ttClasses = new HashMap<String, CalendarTile>();
        for (CalendarTile tile : instruc) {
            if (Objects.equals(tile.getTimeSlot().getDaysInWeek(), "MWF")) {
                mwfClasses.put(tile.getTimeSlot().getStartTime(), tile);
            } else {
                ttClasses.put(tile.getTimeSlot().getStartTime(), tile);
            }
        }
        // maybe create a map of cour
        for (int i = 0; i < 28; i++) {

            printRow(i, mwfClasses, ttClasses);
        }
    }

    /**
     * Prints out schedule by instructor
     * @param cal
     * @param name
     */
    public static void printInstructorSchedule(Calendar cal, String name) {
        cal.generateSchedule();
        List<CalendarTile> instruc = cal.getCourseByInstructor(name);
        printTimes();

        HashMap<String, CalendarTile> mwfClasses = new HashMap<String, CalendarTile>();
        HashMap<String, CalendarTile> ttClasses = new HashMap<String, CalendarTile>();
        for (CalendarTile tile : instruc) {
            if (Objects.equals(tile.getTimeSlot().getDaysInWeek(), "MWF")) {
                mwfClasses.put(tile.getTimeSlot().getStartTime(), tile);
            } else {
                ttClasses.put(tile.getTimeSlot().getStartTime(), tile);
            }
        }
        // maybe create a map of cour
        for (int i = 0; i < 28; i++) {

            printRow(i, mwfClasses, ttClasses);
        }
    }

    /**
     * Prints out the Column headers
     */

    public static void printTimes() {
        System.out.println("               M                  T                  W                 TH                  F");
    }

    /**
     * Prints out an individual row
     * @param rowNum
     * @param mwfClasses
     * @param ttClasses
     */
    public static void printRow(int rowNum, HashMap<String, CalendarTile> mwfClasses,
                                HashMap<String, CalendarTile> ttClasses) {
        String time = timeMap.get(rowNum);
        String tGet = "";
        if (rowNum < 6) {
            tGet = "8AM";
        } else if (rowNum < 12) {
            tGet = "10AM";
        } else if (rowNum < 18) {
            tGet = "12PM";
        } else if (rowNum < 24) {
            tGet = "2PM";
        } else if (rowNum < 30) {
            tGet = "4PM";
        }
        CalendarTile mwClass = mwfClasses.get(tGet);
        CalendarTile ttClass = ttClasses.get(tGet);
        String row = "";
        String head = "";
        if (rowNum % 6 == 0) {
            row += time;
            row += "   ";
            head = "Course ID: ";
            row += rowSetter(mwClass, ttClass, head);

        } else if (rowNum % 6 == 1) {
            row += "     ";
            head = "Room: ";
            row += rowSetter(mwClass, ttClass, head);

        } else if (rowNum % 6 == 2) {
            row += "     ";
            head = "Bldg: ";
            row += rowSetter(mwClass, ttClass, head);
        }
        else if (rowNum % 6 == 3) {
            row += "     ";
            head = "Dept: ";
            row += rowSetter(mwClass, ttClass, head);
        }
        System.out.println(row);

    }

    /**
     * Sets the string value for a row to be printed
     * @param mwClass
     * @param ttClass
     * @param header
     * @return
     */
    public static String rowSetter(CalendarTile mwClass, CalendarTile ttClass, String header) {
        String row = "";
        String mWAddition = "";
        String ttAddition = "";
        if (Objects.equals(header, "Course ID: ")) {
            if(mwClass != null){
                mWAddition = mwClass.getCourse().getCourseID();
            } else {
                ttAddition = "";
            }
            if(ttClass != null){
                ttAddition = ttClass.getCourse().getCourseID();

            } else {
                ttAddition = "";
            }
        }
        if (Objects.equals(header, "Room: ")) {

            if(mwClass != null){
                mWAddition = String.valueOf(((mwClass.getCourse()).getRoom()));
            } else {
                ttAddition = "";
            }
            if(ttClass != null){
                ttAddition =  String.valueOf(((ttClass.getCourse()).getRoom()));

            } else {
                ttAddition = "";
            }
        }
        if (Objects.equals(header, "Bldg: ")) {


            if(mwClass != null){
                mWAddition = mwClass.getCourse().getBuilding();
            } else {
                ttAddition = "";
            }
            if(ttClass != null){
                ttAddition = ttClass.getCourse().getBuilding();

            } else {
                ttAddition = "";
            }

        }
        if (Objects.equals(header, "Dept: ")) {

            if(mwClass != null){
                mWAddition = mwClass.getCourse().getDepartment();
            } else {
                ttAddition = "";
            }
            if(ttClass != null){
                ttAddition = ttClass.getCourse().getDepartment();

            } else {
                ttAddition = "";
            }

        }
        int mwCount = 0;
        for (int i = 0; i < 5; i++) {
            int count = 0;
            if (i % 2 == 0) {
                if (mwClass != null) {
                    row += header;
                    count += header.length();
                    row += mWAddition;
                    if(mWAddition != null){
                        count += mWAddition.length();
                    }
                    while(count < 20 && mwCount < 2){
                        row += " ";
                        count++;
                    }
                } else {
                    while(count < 20 && mwCount < 2){
                        row += " ";
                        count++;
                    }
                }
                mwCount ++;
            } else {
                if (ttClass != null) {
                    row += header;
                    count += header.length();
                    row += ttAddition;
                    if(ttAddition != null){
                        count += ttAddition.length();
                    }
                    while(count < 20){
                        row += " ";
                        count++;
                    }
                } else {
                    while(count < 20){
                        row += " ";
                        count++;
                    }
                }
            }

        }
        return row;
    }

    /**
     * Creates a course from inputted info from a professor
     * @return
     */

    public static Course prof(){
        System.out.println("Enter your course id: ");
        Scanner scan = new Scanner(System.in);
        String id = scan.next();
        System.out.println("Enter your name: ");
        String name = scan.next();
        System.out.println("Enter department of course: ");
        String dept = scan.next();
        System.out.println("Enter course enrollment: ");
        int cap = scan.nextInt();

        Course newCourse = new Course(id, dept, cap, name, rooms);
        courses.addNode(newCourse);
        return newCourse;
    }

    /**
     * Fill map used late in printing
     */
    public static void timeMapFiller(){
        for(int i = 0; i < 26; i++){
            if(i < 6){
                timeMap.put(i, "8AM");
            } else if (i < 12) {
                timeMap.put(i, "10AM");
            } else if (i < 18) {
                timeMap.put(i, "12PM");
            } else if (i < 24) {
                timeMap.put(i, "2PM");
            } else if (i < 30) {
                timeMap.put(i, "4PM");
            }
        }
    }

}