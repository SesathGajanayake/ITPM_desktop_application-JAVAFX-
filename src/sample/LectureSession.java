package sample;


import com.mysql.cj.exceptions.StreamingNotifiable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LectureSession implements Initializable {


    @FXML
    private ComboBox<String> sessionLec;

    @FXML
    private ComboBox<String> sessionTags;

    @FXML
    private ComboBox<String> sessionGroup;

    @FXML
    private ComboBox<String> sessionSubjects;

    @FXML
    private ComboBox<String> sessionCode;

    @FXML
    private TextField numOfStudentText;

    @FXML
    private TextField durationText;

    @FXML
    private TextField sessionSelectLec;

    @FXML
    private TextField sessionSelectTags;



    ObservableList <String> Lectureslist = FXCollections.observableArrayList(getLectureSessions());
    ObservableList <String> Taglist = FXCollections.observableArrayList(getTagSessions());
    ObservableList <String> Subjectnamelist = FXCollections.observableArrayList(getSubjectSessions());
    ObservableList <String> SubjectCodelist = FXCollections.observableArrayList(getSubjectCodeSessions());
    ObservableList <String> SubjectGrouplist = FXCollections.observableArrayList(getSubjectGroupSessions());


    public Connection getConnection() {
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itpm", "root","");
            return con;
        }catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
            return null;
        }
    }


    public void initialize (URL arg0, ResourceBundle arg1){

        sessionLec.setItems(Lectureslist);
        sessionTags.setItems(Taglist);
        sessionGroup.setItems(SubjectGrouplist);
        sessionSubjects.setItems(Subjectnamelist);
        sessionCode.setItems(SubjectCodelist);
    }



    private List<String> getLectureSessions() {

        List<String> options = new ArrayList<>();

        try {

            Connection con = getConnection();
            String query = "SELECT Lecturename FROM lectures";
            PreparedStatement statement = getConnection().prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options.add(set.getString("Lecturename"));
            }

            statement.close();
            set.close();

            // Return the List
            return options;

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }

    private List<String> getTagSessions (){

        List<String> options1 = new ArrayList<>();

        try {

            String query = "SELECT tagr FROM  tags";
            PreparedStatement statement = getConnection().prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options1.add(set.getString("tagr"));
            }

            statement.close();
            set.close();

            // Return the List
            return options1;

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


//
    private List<String> getSubjectGroupSessions() {

        List<String> options4 = new ArrayList<>();

        try {

            String query = "SELECT groupid FROM students";
            PreparedStatement statement = getConnection().prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options4.add(set.getString("groupid"));
            }

            statement.close();
            set.close();

            // Return the List
            return options4;

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }



    private List<String> getSubjectSessions() {

        List<String> options2 = new ArrayList<>();

        try {

            String query = "SELECT subjectn FROM  tags";
            PreparedStatement statement = getConnection().prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options2.add(set.getString("subjectn"));
            }

            statement.close();
            set.close();

            // Return the List
            return options2;

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }



    private List<String> getSubjectCodeSessions() {

        List<String> options3 = new ArrayList<>();

        try {

            String query = "SELECT subjectc FROM  tags";
            PreparedStatement statement = getConnection().prepareStatement(query);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                options3.add(set.getString("subjectc"));
            }

            statement.close();
            set.close();

            // Return the List
            return options3;

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }


    }



    @FXML
    void getselectTags(MouseEvent event) {

        sessionSelectTags.setText(sessionTags.getValue());

    }


    @FXML
    void getselectLecName(MouseEvent event) {


        sessionSelectLec.setText(sessionLec.getValue());

    }


    @FXML
    void nextbtnSave(ActionEvent event) {

        String sessionLecName = sessionLec.getValue();
        String sessionsubjectname = sessionSubjects.getValue();
        String sessioncode = sessionCode.getValue();
        String sessiongroup = sessionGroup.getValue();
        String sessiontag = sessionTags.getValue();
        String numofstudents = numOfStudentText.getText();
        String durationtext = durationText.getText();



        if (sessionLecName.isEmpty() || sessiontag.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText(null);
            alert.setContentText("please select correctly");
            alert.showAndWait();
        } else {
            //calling a insert method here
            insertlecSession();
        }
    }



        private void insertlecSession () {
            System.out.println("running");

            Connection con = getConnection();
            String query = "INSERT into managesessionm2 (lecturename,subjectname,subjectcode,groupid,tags,numofstudents,duration) values(?,?,?,?,?,?,?)";

            try {
                PreparedStatement preparedStatement = getConnection().prepareStatement(query);
                preparedStatement.setString(1, sessionLec.getValue());
                preparedStatement.setString(2, sessionSubjects.getValue());
                preparedStatement.setString(3, sessionCode.getValue());
                preparedStatement.setString(4, sessionGroup.getValue());
                preparedStatement.setString(5, sessionTags.getValue());
                preparedStatement.setString(6, numOfStudentText.getText());
                preparedStatement.setString(7, durationText.getText());

                preparedStatement.execute();
                JOptionPane.showMessageDialog(null, "Insert Successfully");
            } catch (SQLException ex) {

                JOptionPane.showMessageDialog(null, ex);
            }
        }

    @FXML
    void clear(ActionEvent event) {

//        sessionLec.setItems(null);
//        sessionSubjects.setItems(null);
//        sessionCode.setItems(null);
//        sessionGroup.setItems(null);
//        sessionTags.setItems(null);
        numOfStudentText.setText(" ");
        durationText.setText(" ");
        sessionSelectTags.setText(" ");
        sessionSelectLec.setText(" ");
    }

    @FXML
    void next(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("manageSession.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


}

