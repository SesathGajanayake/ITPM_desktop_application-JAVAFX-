package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.*;


public class LecturesDetailsController implements Initializable {

    int index = -1;

//    @FXML
//    private ChoiceBox<String> facultyChoice;
//    private String[] facultyChoiceTable = {"Computing", "Engineering", "Business", "Humanities & Sciences,"};
//
//    @FXML
//    private ChoiceBox<String> DepartmentChoice2 ;
//    private String [] DepartmentChoiceTable = {"IT","SE","DS"};
//
//    @FXML
//    private ChoiceBox<String> CenterChoice3;
//    private String [] CenterChoiceTable = {"Malabe", "Metro", "Matara", "Kandy", "Kurunagala"," Jaffna"};
//
//    @FXML
//    private ChoiceBox<String> BuildingChoice4;
//    private String [] BuildingChoiceTable = {"New building"," D-block"};
//
//    @FXML
//    private ChoiceBox<String> LevelChoice5;
//    private String [] LevelChoiceTable = {"1","2","3","4","5","6"};


//        facultyChoice.getItems().addAll(facultyChoiceTable);
//        DepartmentChoice2.getItems().addAll(DepartmentChoiceTable);
//        CenterChoice3.getItems().addAll(CenterChoiceTable);
//        BuildingChoice4.getItems().addAll(BuildingChoiceTable);
//        LevelChoice5.getItems().addAll(LevelChoiceTable);

    @FXML
    private TableView<Lectures> managingTableLec;

    @FXML
    private TableColumn<Lectures, String> empidTable;

    @FXML
    private TableColumn<Lectures, String> lecnameTable;

    @FXML
    private TableColumn<Lectures, String> facultyTable;

    @FXML
    private TableColumn<Lectures, String> departmentTable;

    @FXML
    private TableColumn<Lectures, String> levelTable;

    @FXML
    private TableColumn<Lectures, String> buildingTable;

    @FXML
    private TableColumn<Lectures, String> centerTable;

    @FXML
    private TableColumn<Lectures, String> rankTable;

    @FXML
    private Button updateMange;

    @FXML
    private Button deleteMange;

    @FXML
    private TextField lecnameChoice;

    @FXML
    private TextField empidChoice;

    @FXML
    private TextField facultyChoice;

    @FXML
    private TextField departmentChoice;

    @FXML
    private TextField buildingChoice;

    @FXML
    private TextField levelChoice;

    @FXML
    private TextField rankChoice;

    @FXML
    private TextField centerChoice;



    String query =null;
    ResultSet resultsset =null;
    PreparedStatement preparedStatement;




    @FXML
    void getSelected (MouseEvent event) {
        index = managingTableLec.getSelectionModel().getFocusedIndex();
        if(index <= -1) {
            return;
        }
        empidChoice.setText(empidTable.getCellData(index).toString());
        lecnameChoice.setText(lecnameTable.getCellData(index).toString());
        facultyChoice.setText(facultyTable.getCellData(index).toString());
        departmentChoice.setText(departmentTable.getCellData(index).toString());
        centerChoice.setText(centerTable.getCellData(index).toString());
        buildingChoice.setText(buildingTable.getCellData(index).toString());
        levelChoice.setText(levelTable.getCellData(index).toString());
        rankChoice.setText(rankTable.getCellData(index).toString());

    }



    private void RefreshTable() {

        System.out.println(Lectures.class);



        lecnameTable.setCellValueFactory(new PropertyValueFactory<Lectures , String>("Lecturename"));
        empidTable.setCellValueFactory(new PropertyValueFactory<Lectures , String>("EmployeeID"));
        facultyTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Faculty"));
        departmentTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Department"));
        centerTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Center"));
        buildingTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Building"));
        levelTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Level"));
        rankTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Rank"));

        ObservableList<Lectures> list= getlectureslist();
        managingTableLec.setItems(list);

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

    public ObservableList<Lectures> getlectureslist(){

        ObservableList<Lectures> lectureslist = FXCollections.observableArrayList();
        Connection con = getConnection();
        String query = "SELECT * FROM lectures";

        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs= st.executeQuery(query);
            Lectures lectures;
            while(rs.next()) {

                lectures = new Lectures(rs.getString("EmployeeID"),rs.getString("Lecturename"),rs.getString("Faculty"),rs.getString("Department"),rs.getString("center"),rs.getString("Building"),rs.getString("Level"),rs.getString("Rank"));
                lectureslist.add(lectures);

            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();

        }

        return lectureslist;
    }


    public void showLectures() {
        ObservableList<Lectures> list= getlectureslist();


        lecnameTable.setCellValueFactory(new PropertyValueFactory<Lectures , String>("Lecturename"));
        empidTable.setCellValueFactory(new PropertyValueFactory<Lectures , String>("EmployeeID"));
        facultyTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Faculty"));
        departmentTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Department"));
        centerTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Center"));
        buildingTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Building"));
        levelTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Level"));
        rankTable.setCellValueFactory(new PropertyValueFactory<Lectures,String>("Rank"));

        managingTableLec.setItems(list);



    }



    @FXML
   public void updatebutton(ActionEvent event) {

        try {
            Connection conn =getConnection();

            String lecnameChoiceText = lecnameChoice.getText();
            String empidChoiceText =empidChoice.getText();
            String facultyChoiceText =facultyChoice.getText();
            String departmentChoiceText =departmentChoice.getText();
            String centerChoiceText = centerChoice.getText();
            String buildingChoiceText =buildingChoice.getText();
            String levelChoiceText =levelChoice.getText();
            String rankChoiceText =rankChoice.getText();


            String query ="update lectures set Lecturename='"+lecnameChoiceText+"',EmployeeID='"+empidChoiceText+"',Faculty='"+facultyChoiceText+"',Department ='"+departmentChoiceText+"',center ='"+centerChoiceText+"',Building ='"+buildingChoiceText+"',Level ='"+levelChoiceText+"',Rank ='"+rankChoiceText+"' where EmployeeID='"+empidChoiceText+"'";

            System.out.println(centerChoiceText);
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.execute();

            JOptionPane.showMessageDialog(null, "Update Successfully");
            RefreshTable();
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


    }


    @FXML
    void deletebutton(ActionEvent event) {

        Connection con =getConnection();
        String query ="delete from lectures where EmployeeID  =?";
        try {
            preparedStatement = getConnection().prepareStatement(query);
            preparedStatement.setString(1, empidChoice.getText());
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "Deleted Successfully");
            RefreshTable();
        }catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @FXML
    void clear(ActionEvent event) {

        lecnameChoice.setText(" ");
        empidChoice.setText(" ");
        facultyChoice.setText(" ");
        departmentChoice.setText(" ");
        buildingChoice.setText(" ");
        levelChoice.setText(" ");
        rankChoice.setText(" ");
        centerChoice.setText(" ");

    }

    @FXML
    void next(ActionEvent event) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("subjects.fxml"));
        Scene scene = new Scene(root);
        Stage stage =new Stage();
        stage.setScene(scene);
        stage.show();


    }

}
