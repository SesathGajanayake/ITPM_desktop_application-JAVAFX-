package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

//    @FXML
//    private ChoiceBox<String> myChoicebox ;
//    private String [] faculty = {"Computing","Engineering","Business","Humanities & Sciences,"};
//
//    @FXML
//    private ChoiceBox<String> myChoicebox1 ;
//    private String [] department = {"IT","SE","DS"};
//
//    @FXML
//    private ChoiceBox<String> myChoicebox2;
//    private String [] building = {"New building"," D-block"};
//
//    @FXML
//    private ChoiceBox<String> myChoicebox3;
//    private String [] level = {"1","2","3","4","5","6"};
//
//    @Override
//    public void initialize(URL arg0, ResourceBundle arg1) {
//
//        myChoicebox.getItems().addAll(faculty);
//        myChoicebox1.getItems().addAll(department);
//        myChoicebox2.getItems().addAll(building);
//        myChoicebox3.getItems().addAll(level);
//
//    }
//





public class LecturesController implements Initializable{



    @FXML
    private TextField empId;

    @FXML
    private TextField rankGenerate;

    @FXML
    private TextField lecName1;

    @FXML
    private ComboBox<String> faculty;

    @FXML
    private ComboBox<String> department;

    @FXML
    private ComboBox<String> center;

    @FXML
    private ComboBox<String> building;

    @FXML
    private ComboBox<String> level;


    ObservableList <String> List1 =FXCollections.observableArrayList("Computing","Engineering","Business","Humanities & Sciences,");
    ObservableList <String> List2 =FXCollections.observableArrayList("IT","SE","DS");
    ObservableList <String> List3 =FXCollections.observableArrayList("Malabe" ,"Metro", "Kandy", "Jaffana","Galle","Kurunegala");
    ObservableList <String> List4 =FXCollections.observableArrayList("New building", "D-block");
    ObservableList <String> List5 =FXCollections.observableArrayList("1","2","3","4","5","6");




    String query =null;
    ResultSet resultsset =null;
    PreparedStatement preparedStatement;


    @FXML
    void generateRank(ActionEvent event) {

        String levelGen = level.getValue();
        String empIdGen = empId.getText();

        String output1 = levelGen+"."+empIdGen+"";


        rankGenerate.setText(output1);


    }


    @FXML
    void save(ActionEvent event) {

        String lecName1Text = lecName1.getText();
        String empIdText =empId.getText();
        String facultyIDText = faculty.getValue();
        String departmentText=department.getValue();
        String centerText = center.getValue();
        String buildingText= building .getValue();
        String levelText=level.getValue();
        String rankGenerateText=rankGenerate.getText();


        if(lecName1Text.isEmpty() ||empIdText.isEmpty()||facultyIDText.isEmpty()||departmentText.isEmpty()||centerText.isEmpty()||buildingText.isEmpty()||levelText.isEmpty() ||rankGenerateText.isEmpty()) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();
        }
        else {
            InsertLectures(); //call data insert method

        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("lecturesDetails.fxml"));

            Scene scene = new Scene(root);
            Stage stage =new Stage();
            stage.setScene(scene);
            stage.show();


        } catch(Exception e) {
            e.printStackTrace();
        }


    }

    private void InsertLectures() {


        Connection con =getConnection();
        String query ="insert into lectures (Lecturename,EmployeeID,Faculty,Department,center,Building,Level,Rank) values(?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, lecName1.getText());
            preparedStatement.setString(2, empId.getText());
            preparedStatement.setString(3, faculty.getValue());
            preparedStatement.setString(4, department.getValue());
            preparedStatement.setString(5, center.getValue());
            preparedStatement.setString(6, building.getValue());
            preparedStatement.setString(7, level.getValue());
            preparedStatement.setString(8, rankGenerate.getText());

            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Insert Successfully");
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }




    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        faculty.setItems(List1);
        department.setItems(List2);
        center.setItems(List3);
        building.setItems(List4);
        level.setItems(List5);



    }


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


    @FXML
    void clear(ActionEvent event) {

        faculty.setItems(null);
        department.setItems(null);
        center.setItems(null);
        building.setItems(null);
        level.setItems(null);
        empId.setText(" ");
        rankGenerate.setText(" ");
        lecName1.setText(" ");

    }

}



