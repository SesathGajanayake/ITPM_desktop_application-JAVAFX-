package sample;

public class Lectures {

    private String EmployeeID;
    private String Lecturename;
    private String Faculty;
    private String Department;
    private String Center;
    private String Building;
    private String Level;
    private String Rank;

   public Lectures( String EmployeeID, String Lecturename, String Faculty, String Department, String Center, String Building, String Level, String Rank){
        this.EmployeeID = EmployeeID;
        this.Lecturename = Lecturename;
        this.Faculty = Faculty;
        this.Department = Department;
        this.Center = Center;
        this.Building = Building;
        this.Level = Level;
        this.Rank = Rank;
    }




    public String getEmployeeID() {
        return EmployeeID;
    }

    public String getLecturename() {
        return Lecturename;
    }

    public String getFaculty() {
        return Faculty;
    }

    public String getDepartment() {
        return Department;
    }

    public String getCenter() {
        return Center;
    }

    public String getBuilding() {
        return Building;
    }

    public String getLevel() {
        return Level;
    }

    public String getRank() {
        return Rank;
    }

}
