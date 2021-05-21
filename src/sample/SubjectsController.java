package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;




import javafx.fxml.FXML;
import javafx.stage.Stage;

import javax.swing.*;

public class SubjectsController implements Initializable {

    @FXML
    private TextField subjectnameChoice;

    @FXML
    private TextField subjectcodeChoice;


    @FXML
    private TextField notuteChoice;

    @FXML
    private TextField nolecChoice;

    @FXML
    private TextField nolabChoice;

    @FXML
    private TextField noevaluationChoice;

    @FXML
    private ComboBox<String> offerdYearchoice;

    @FXML
    private RadioButton semOne;

    @FXML
    private ToggleGroup semester;

    @FXML
    private RadioButton semTwo;




    ObservableList<String> List1 = FXCollections.observableArrayList("1","2","3","4");




    String query =null;
    ResultSet resultsset =null;
    PreparedStatement preparedStatement;


    @FXML
    void save(ActionEvent event) {

        String offerdYearText = offerdYearchoice.getValue();
        String offerdsemesterChoiceText =getRadioBtn();
        String subjectnameChoiceText =subjectnameChoice.getText();
        String subjectcodeChoiceText=subjectcodeChoice.getText();
        String notuteChoiceText = notuteChoice.getText();
        String nolecChoiceText= nolecChoice .getText();
        String nolabChoiceText=nolabChoice.getText();
        String noevaluationChoiceText=noevaluationChoice.getText();


        if(offerdYearText.isEmpty() ||offerdsemesterChoiceText.isEmpty()||subjectnameChoiceText.isEmpty()||subjectcodeChoiceText.isEmpty()||notuteChoiceText.isEmpty()||nolecChoiceText.isEmpty()||nolabChoiceText.isEmpty() ||noevaluationChoiceText.isEmpty()) {
            Alert alert =new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Fill All Data");
            alert.showAndWait();
        }
        else {
            InsertStudent(); //call data insert method

        }

//        try {
//            Parent root = FXMLLoader.load(getClass().getResource("subjectDetails.fxml"));
//
//            Scene scene = new Scene(root);
//            Stage stage =new Stage();
//            stage.setScene(scene);
//            stage.show();
//
//
//        } catch(Exception e) {
//            e.printStackTrace();
//        }



    }

    private void InsertStudent() {


        Connection con =getConnection();
        String query ="insert into student (Offerdyear,Offerdsemster,Subjectname,Subjectcode,Numtutehrs,Numlechrs,Numlabhrs,Numevalhrs) values(?,?,?,?,?,?,?,?)";
        try {

            PreparedStatement preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, offerdYearchoice.getValue());
            preparedStatement.setString(2, getRadioBtn());
            preparedStatement.setString(3, subjectnameChoice.getText());
            preparedStatement.setString(4, subjectcodeChoice.getText());
            preparedStatement.setString(5, notuteChoice.getText());
            preparedStatement.setString(6, nolecChoice.getText());
            preparedStatement.setString(7, nolabChoice.getText());
            preparedStatement.setString(8, noevaluationChoice.getText());



            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Insert Successfully");
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }


    public void initialize(URL arg0, ResourceBundle arg1) {

        offerdYearchoice.setItems(List1);

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
    void radioSelect(ActionEvent event) {

        getRadioBtn();


    }

    public String getRadioBtn() {

        String message ="";
        if(semOne.isSelected()) {
            message += semOne.getText();
        }

        if(semTwo.isSelected()) {
            message += semTwo.getText();
        }

        System.out.println(message);
        return message;


    }

    @FXML
    void clear(ActionEvent event) {


//        offerdYearchoice.setItems(null);
        subjectnameChoice.setText(" ");
        subjectcodeChoice.setText(" ");
        notuteChoice.setText(" ");
        nolecChoice.setText(" ");
        nolabChoice.setText(" ");
        noevaluationChoice.setText(" ");
        nolabChoice.setText(" ");

    }

    @FXML
    void next(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("subjectDetails.fxml"));
        Scene scene = new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();



    }



}







