/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author saadyarao
 */
public class ScheduleQueries {
    private static Connection connection;
    private static ArrayList<String> faculty = new ArrayList<String>();
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getScheduledStudentsByCourse;
    private static PreparedStatement getWaitlistedStudentsByCourse;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static ResultSet resultSet;
    
    public static void addScheduleEntry(ScheduleEntry entry)
    {
        connection = DBConnection.getConnection();
        try
        {
            addScheduleEntry = connection.prepareStatement("insert into app.schedule (semester, coursecode, studentid, status, timestamp) values (?, ?, ?, ?, ?)");
            addScheduleEntry.setString(1, entry.getSemester());
            addScheduleEntry.setString(2, entry.getCourseCode());
            addScheduleEntry.setString(3, entry.getStudentID());
            addScheduleEntry.setString(4, entry.getStatus());
            addScheduleEntry.setTimestamp(5, entry.getTimestamp());
            addScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> schedule = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduleByStudent = connection.prepareStatement("select semester, coursecode, studentid, status, timestamp from app.schedule where semester = ? and studentid = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            resultSet = getScheduleByStudent.executeQuery();
            
            while(resultSet.next())
            {
                ScheduleEntry entry = new ScheduleEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getTimestamp(5));
                schedule.add(entry);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedule;
        
    }
    
    public static int getScheduledStudentCount(String currentSemester, String courseCode)
    {
        connection = DBConnection.getConnection();
        int scheduledStudents = 0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(studentID) from app.schedule where semester = ? and coursecode = ?");
            getScheduledStudentCount.setString(1, currentSemester);
            getScheduledStudentCount.setString(2, courseCode);
            resultSet = getScheduledStudentCount.executeQuery();
            
            while(resultSet.next())
            {
                scheduledStudents = resultSet.getInt(1);
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return scheduledStudents;
    }
    
    public static ArrayList<ScheduleEntry> getScheduledStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> studentS = new ArrayList<ScheduleEntry>();
        try
        {
            getScheduledStudentsByCourse = connection.prepareStatement("select studentid, timestamp from app.schedule where semester = ? and coursecode = ? and status = ?");
            getScheduledStudentsByCourse.setString(1, semester);
            getScheduledStudentsByCourse.setString(2, courseCode);
            getScheduledStudentsByCourse.setString(3, "S");
            resultSet = getScheduledStudentsByCourse.executeQuery();
            
            while(resultSet.next()){
                studentS.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), "S", resultSet.getTimestamp(2)));
            }
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return studentS;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByCourse(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> studentW = new ArrayList<ScheduleEntry>();
        try
        {
            getWaitlistedStudentsByCourse = connection.prepareStatement("select studentid, timestamp from app.schedule where semester = ? and coursecode = ? and status = ?");
            getWaitlistedStudentsByCourse.setString(1, semester);
            getWaitlistedStudentsByCourse.setString(2, courseCode);
            getWaitlistedStudentsByCourse.setString(3, "W");
            resultSet = getWaitlistedStudentsByCourse.executeQuery();
            
            while(resultSet.next()){
                studentW.add(new ScheduleEntry(semester, courseCode, resultSet.getString(1), "W", resultSet.getTimestamp(2)));
            }
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return studentW;
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        try
        {
            dropStudentScheduleByCourse = connection.prepareStatement("delete from app.schedule where studentid = ? and semester = ? and coursecode = ?");
            dropStudentScheduleByCourse.setString(1, studentID);
            dropStudentScheduleByCourse.setString(2, semester);
            dropStudentScheduleByCourse.setString(3, courseCode);
            dropStudentScheduleByCourse.executeUpdate();    
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        try
        {
            dropScheduleByCourse = connection.prepareStatement("delete from app.schedule where semester = ? and coursecode = ?");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2, courseCode);
            dropScheduleByCourse.executeUpdate();    
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void updateScheduleEntry(String semester, ScheduleEntry entry){
        connection = DBConnection.getConnection();
        try
        {
            updateScheduleEntry = connection.prepareStatement("update app.schedule set status = 'S' where semester = ? and studentid = ? and coursecode = ?");
            updateScheduleEntry.setString(1, semester);
            updateScheduleEntry.setString(2, entry.getStudentID());
            updateScheduleEntry.setString(3, entry.getCourseCode());
            updateScheduleEntry.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

    }
}