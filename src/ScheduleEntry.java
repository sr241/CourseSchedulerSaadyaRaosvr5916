/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Timestamp;
/**
 *
 * @author saadyarao
 */
public class ScheduleEntry {
    private String Semester;
    private String CourseCode;
    private String StudentID;
    private String Status;
    private Timestamp Timestamp;
    
    public ScheduleEntry(String Semester, String CourseCode, String StudentID, String Status, Timestamp Timestamp){
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.StudentID = StudentID;
        this.Status = Status;
        this.Timestamp = Timestamp;
    }
    
    public String getSemester(){
        return this.Semester;
    }
    
    public String getCourseCode(){
        return this.CourseCode;
    }
    
    public String getStudentID(){
        return this.StudentID;
    }
    
    public String getStatus(){
        return this.Status;
    }
    
    public Timestamp getTimestamp(){
        return this.Timestamp;
    }
}
