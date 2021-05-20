package sample;


public class Session {

    private int ID;
    private String Lecturename;
    private String Subjectname;
    private String Subjectcode;
    private String GroupID;
    private String Tags;
    private String NumofStudents;
    private String Duration;



    Session (int ID , String Lecturename, String Subjectname , String Subjectcode , String GroupID , String Tags , String NumofStudents , String Duration){

        this.ID=ID;
        this.Lecturename = Lecturename;
        this.Subjectname = Subjectname;
        this.Subjectcode = Subjectcode;
        this.GroupID = GroupID;
        this.Tags = Tags;
        this.NumofStudents = NumofStudents;
        this.Duration = Duration;

    }


    public int getID() {
        return ID;
    }

    public String getLecturename() {
        return Lecturename;
    }

    public String getSubjectname() {
        return Subjectname;
    }

    public String getSubjectcode() {
        return Subjectcode;
    }

    public String getGroupID() {
        return GroupID;
    }

    public String getTags() {
        return Tags;
    }

    public String getNumofStudents() {
        return NumofStudents;
    }

    public String getDuration() {
        return Duration;
    }


}
