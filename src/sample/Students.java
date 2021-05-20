package sample;

public class Students {

    private int ID;
    private String Offerdyear;
    private String Offerdsemester;
    private String SubjectCode;
    private String SubjectName;
    private String Numtutehrs;
    private String Numlechrs;
    private String Numlabhrs;
    private String Numevalhrs;

    Students (int ID , String Offerdyear, String Offerdsemester , String SubjectCode , String SubjectName , String Numtutehrs , String Numlechrs , String Numlabhrs , String Numevalhrs){

        this.ID=ID;
        this.Offerdyear = Offerdyear;
        this.Offerdsemester = Offerdsemester;
        this.SubjectCode = SubjectCode;
        this.SubjectName = SubjectName;
        this.Numtutehrs = Numtutehrs;
        this.Numlechrs = Numlechrs;
        this.Numlabhrs = Numlabhrs;
        this.Numevalhrs = Numevalhrs;

    }

    public int getID() {
        return ID;
    }

    public String getOfferdyear() {
        return Offerdyear;
    }

    public String getOfferdsemester() {
        return Offerdsemester;
    }

    public String getSubjectCode() {
        return SubjectCode;
    }

    public String getSubjectName() {
        return SubjectName;
    }

    public String getNumtutehrs() {
        return Numtutehrs;
    }

    public String getNumlechrs() {
        return Numlechrs;
    }

    public String getNumlabhrs() {
        return Numlabhrs;
    }

    public String getNumevalhrs() {
        return Numevalhrs;
    }




}
