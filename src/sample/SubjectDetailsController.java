package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

//    @FXML
//    private Spinner MangeSpinnerTutorial;
//
//    @FXML
//    private Spinner MangeSpinnerLec;
//
//    @FXML
//    private Spinner MangeSpinnerLab;
//
//    @FXML
//    private Spinner MangeSpinnerEvaluation;
//
//
//    @FXML
//    private ChoiceBox<String> MangeOfferdYearChoice;
//    private String[] MangeOfferdYear = {"1", "2", "3", "4"};
//
//
//
//    public void initialize(URL arg0, ResourceBundle arg1) {


public class SubjectDetailsController implements Initializable {

    int index = -1;


    @FXML
    private TableView<Students> mangesubjectTable;

    @FXML
    private TableColumn<Students, Integer> idTable;

    @FXML
    private TableColumn<Students, String> offerdyearTable;

    @FXML
    private TableColumn<Students, String> offerdsemesterTable;

    @FXML
    private TableColumn<Students, String> subjectnameTable;

    @FXML
    private TableColumn<Students, String> subjectcodeTable;

    @FXML
    private TableColumn<Students, String> TutehrsTable;

    @FXML
    private TableColumn<Students, String> LecturshrsTable;

    @FXML
    private TableColumn<Students, String> labhrsTable;

    @FXML
    private TableColumn<Students, String> evaluationhrsTable;

    @FXML
    private TextField subjectnameChoice;

    @FXML
    private TextField subjectcodeChoice;

    @FXML
    private Button updatebtn;

    @FXML
    private Button deletebtn;

    @FXML
    private TextField offerdyearChoice;

    @FXML
    private TextField offerdsemesterChoice;

    @FXML
    private TextField numoflecChoice;

    @FXML
    private TextField numoflabChoice;

    @FXML
    private TextField numofevaluationChoice;

    @FXML
    private TextField numoftutehrsChoice;


    @FXML
    private TextField idChoice;


    @FXML
    void update(ActionEvent event) {

        try {
            Connection con =getConnection();

            String idTableText = idChoice.getText();
            String offerdyearTableText = offerdyearChoice.getText();
            String offerdsemesterTableText = offerdsemesterChoice.getText();
            String subjectnameTableText = subjectnameChoice.getText();
            String subjectcodeTableText=subjectcodeChoice.getText();
            String tutehrsTableText = numoftutehrsChoice.getText();
            String lecturshrsTableText = numoflecChoice.getText();
            String labhrsTableText = numoflabChoice.getText();
            String evaluationhrsTableText = numofevaluationChoice.getText();


            System.out.println(idTableText);

            String query ="update student set Id ='"+idTableText+"',Offerdyear ='"+offerdyearTableText+"',Offerdsemster ='"+offerdsemesterTableText+"',Subjectname ='"+subjectnameTableText+"',Subjectcode ='"+subjectcodeTableText+"',Numtutehrs ='"+tutehrsTableText+"',Numlechrs ='"+lecturshrsTableText+"',Numlabhrs ='"+labhrsTableText+"',Numevalhrs ='"+evaluationhrsTableText+"'where Id='"+idTableText+"'";


            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Update Successfully");
            RefreshTable();
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }



    }



    String query =null;
    ResultSet resultsset =null;
    PreparedStatement preparedStatement;




    @FXML
    void getSelected (MouseEvent event) {
        index = mangesubjectTable.getSelectionModel().getFocusedIndex();
        if(index <= -1) {
            return;
        }

        idChoice.setText(idTable.getCellData(index).toString());
        offerdyearChoice.setText(offerdyearTable.getCellData(index).toString());
        offerdsemesterChoice.setText(offerdsemesterTable.getCellData(index).toString());
        subjectnameChoice.setText(subjectnameTable.getCellData(index).toString());
        subjectcodeChoice.setText(subjectcodeTable.getCellData(index).toString());
        numoftutehrsChoice.setText(TutehrsTable.getCellData(index).toString());
        numoflecChoice.setText(LecturshrsTable.getCellData(index).toString());
        numoflabChoice.setText(labhrsTable.getCellData(index).toString());
        numofevaluationChoice.setText(evaluationhrsTable.getCellData(index).toString());


    }


    private void RefreshTable() {


        ObservableList<Students> list = getstudentslist();


        idTable.setCellValueFactory(new PropertyValueFactory<Students, Integer>("ID"));
        offerdyearTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Offerdyear"));
        offerdsemesterTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Offerdsemester"));
        subjectnameTable.setCellValueFactory(new PropertyValueFactory<Students , String>("SubjectName"));
        subjectcodeTable.setCellValueFactory(new PropertyValueFactory<Students , String>("SubjectCode"));
        TutehrsTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Numtutehrs"));
        LecturshrsTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Numlechrs"));
        labhrsTable.setCellValueFactory(new PropertyValueFactory<Students,String>("Numlabhrs"));
        evaluationhrsTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Numevalhrs"));

        mangesubjectTable.setItems(list);



    }



    public void initialize(URL arg0, ResourceBundle arg1) {
    showLectures();
    RefreshTable();

    }

    public Connection getConnection() {
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root", "");
            return con;
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }

    }

    public ObservableList<Students> getstudentslist() {

        ObservableList<Students> studentsList = FXCollections.observableArrayList();
        Connection con = getConnection();
        String query = "SELECT * FROM student";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);
            Students student;
            while (rs.next()) {

                student = new Students(rs.getInt("Id"),rs.getString("Offerdyear"),rs.getString("Offerdsemster"),rs.getString("Subjectname"),rs.getString("Subjectcode"),rs.getString("Numtutehrs"),rs.getString("Numlechrs"),rs.getString("Numlabhrs"),rs.getString("Numevalhrs"));
                studentsList.add(student);


            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }

        return studentsList;
    }


    public void showLectures() {

        ObservableList<Students> list = getstudentslist();


        idTable.setCellValueFactory(new PropertyValueFactory<Students, Integer>("ID"));
        offerdyearTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Offerdyear"));
        offerdsemesterTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Offerdsemester"));
        subjectnameTable.setCellValueFactory(new PropertyValueFactory<Students , String>("SubjectName"));
        subjectcodeTable.setCellValueFactory(new PropertyValueFactory<Students , String>("SubjectCode"));
        TutehrsTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Numtutehrs"));
        LecturshrsTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Numlechrs"));
        labhrsTable.setCellValueFactory(new PropertyValueFactory<Students,String>("Numlabhrs"));
        evaluationhrsTable.setCellValueFactory(new PropertyValueFactory<Students , String>("Numevalhrs"));

        mangesubjectTable.setItems(list);


    }

    @FXML
    void delete(ActionEvent event) {
        Connection con =getConnection();
        String query ="delete from student where Id =?";
        try {
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, idChoice.getText());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
            RefreshTable();
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }

    @FXML
    void clear(ActionEvent event) {

        idChoice.setText(" ");
        subjectnameChoice.setText(" ");
        subjectcodeChoice.setText(" ");
        offerdyearChoice.setText(" ");
        offerdsemesterChoice.setText(" ");
        numoflecChoice.setText(" ");
        numoflabChoice.setText(" ");
        numofevaluationChoice.setText(" ");
        numoftutehrsChoice.setText(" ");

    }


    @FXML
    void next(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("lectureSession.fxml"));
        Scene scene = new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();


    }



}








