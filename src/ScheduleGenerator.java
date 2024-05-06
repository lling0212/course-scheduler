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
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, are you a professor or an administrator?");
        String classification = sc.next();
        while (loop) {
            if (Objects.equals(classification, "professor")) {
                Course course = prof();
                cal = new Calendar(courses);
                printInstructorSchedule(cal, course.getInstructor());
                if (exit()) {
                    loop = false;
                }
            } else if (Objects.equals(classification, "administrator")) {

            }
        }
    }

    public static boolean exit() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to enter a new course? (yes or no)");
        String answer = sc.next();
        return answer.equals("no");
    }

    public static void courseFiller() {
        Course course1 = new Course("1", "Chemistry", 20, "Martinez", rooms);
        Course course4 = new Course("17", "Chemistry", 12, "Martinez", rooms);
        Course course5 = new Course("19", "Chemistry", 21, "Martinez", rooms);
        Course course2 = new Course("2", "Alchemy", 30, "Rocker", rooms);
        Course course3 = new Course("3", "Spanish", 39, "Marshall", rooms);
        courses.addNode(course1);
        courses.addNode(course2);
        courses.addNode(course3);
        courses.addNode(course4);
        courses.addNode(course5);
    }

    public static void roomFiller() {
        Room room1 = new Room(1, 10, "1", "Bart", "Chemistry");
        Room room2 = new Room(2, 15, "2", "Macao", "Spanish");
        Room room3 = new Room(3, 20, "3", "Bart", "Chemistry");
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
        for (int i = 0; i < 26; i++) {

            printRow(i, mwfClasses, ttClasses);
        }
    }

    public static void printTimes() {
        System.out.println("                     M                     T                     W                     TH                     F");
    }

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
            row += "             ";
            head = " Course ID: ";
            row += rowSetter(mwClass, ttClass, head);
//            for (int i = 0; i < 5; i++) {
//                if (i % 2 == 0) {
//                    if (mwClass != null) {
//                        row += " Course ID: ";
//                        row += mwClass.getCourse().getCourseID();
//                        row += "               ";
//                    } else {
//                        row += "               ";
//                    }
//
//                } else {
//                    if (ttClass != null) {
//                        row += " Course ID: ";
//                        row += ttClass.getCourse().getCourseID();
//                        row += "               ";
//                    } else {
//                        row += "               ";
//
//                    }
//
//                }
//            }

        } else if (rowNum % 6 == 1) {
            row += "\t";


        } else if (rowNum % 6 == 2) {
            row += "\t";


        }
        System.out.println(row);

    }


    public static String rowSetter(CalendarTile mwClass, CalendarTile ttClass, String header) {
        String row = "";
        String mWAddition = "";
        String ttAddition = "";
        if (Objects.equals(header, " Course ID: ")) {
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
        if (Objects.equals(header, " Room Number: ")) {
            mWAddition = mwClass.getRoom().getRoomNumber();
            ttAddition = ttClass.getRoom().getRoomNumber();
        }
        if (Objects.equals(header, " Building: ")) {
            mWAddition = mwClass.getRoom().getBuilding();
            ttAddition = ttClass.getRoom().getBuilding();
        }
        if (Objects.equals(header, " Department: ")) {
            mWAddition = mwClass.getCourse().getDepartment();
            ttAddition = ttClass.getCourse().getDepartment();
        }
        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                if (mwClass != null) {
                    row += header;
                    row += mWAddition;
                    row += "               ";
                } else {
                    row += "               ";
                }
            } else {
                if (ttClass != null) {
                    row += header;
                    row += ttAddition;
                    row += "               ";
                } else {
                    row += "               ";
                }
            }

        }
        return row;
    }

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