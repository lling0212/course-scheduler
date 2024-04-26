public class Course {
    private String courseID;
    private String department;
    private int capacity;
    private String instructorName;


    public Course(){

    }

    public String getCourseID(){
        return courseID;
    }
    public void setCourseID(String id){
        courseID = id;
    }

    public String getDepartment(){
        return department;
    }
    public void setDepartment(String dep){
        department = dep;
    }
    public int getCapacity(){
        return capacity;
    }
    public void setCapactiy(int cap){
        capacity = cap;
    }
    public String getInstructor(){
        return instructorName;
    }

    public void setInstructor(String name){
        instructorName = name;
    }



}
