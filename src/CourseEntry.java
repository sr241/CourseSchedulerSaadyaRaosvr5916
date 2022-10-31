/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author saadyarao
 */
public class CourseEntry {
    private String Semester;
    private String CourseCode;
    private String Description;
    private int Seats;
    
    public CourseEntry(String Semester, String CourseCode, String Description, int Seats){
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.Description = Description;
        this.Seats = Seats;
        
    }
    
    public String getSemester(){
        return this.Semester;
    }
    
    public String getCourseCode(){
        return this.CourseCode;
    }
    
    public String getDescription(){
        return this.Description;
    }
    
    public int getSeats(){
        return this.Seats;
    }
    
    
}
