package Controllers;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDecorator;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Filter_Timetable_Controller implements Initializable {
    @FXML
    private JFXComboBox combobox_course;
    public static JFXComboBox comboBox_course;

    @FXML
    private JFXComboBox combobox_semester;
    public static JFXComboBox comboBox_semester;

    @FXML
    private JFXComboBox combobox_academic_Yr;
    public static JFXComboBox comboBox_academic_yr;

    @FXML
    private JFXComboBox combobox_yr_of_study;
    public static JFXComboBox comboBox_yr_of_study;

    @FXML
    private JFXButton button_view;

    @FXML
    private JFXButton button_cancel;

    static ObservableList observableList_course = FXCollections.observableArrayList();
    static ObservableList observableList_semester = FXCollections.observableArrayList();
    static ObservableList observableList_academic_yr = FXCollections.observableArrayList();
    static ObservableList observableList_yr_of_study = FXCollections.observableArrayList();

    ObservableList observableList_table_data = FXCollections.observableArrayList();

    static ResultSet resultSet;
    static Database_Handler database_handler = Database_Handler.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        comboBox_course = combobox_course;
        comboBox_semester = combobox_semester;
        comboBox_academic_yr = combobox_academic_Yr;
        comboBox_yr_of_study = combobox_yr_of_study;

        String query = "SELECT * FROM Course";
        resultSet = database_handler.execQuery(query);
        String course = null;
        try {
            while (resultSet.next()) {
                course = resultSet.getString("Course_Name");
                observableList_course.addAll(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        comboBox_course.setItems(observableList_course);


    }

    @FXML
    void fetch_details(ActionEvent event) {
        String course = combobox_course.getValue().toString();
        String query = "SELECT * FROM Course WHERE Course_Name = '" + course + "'";
        resultSet = database_handler.execQuery(query);
        try {
            while (resultSet.next()) {
                String semester = resultSet.getString("Semester");
                String academic_YR = resultSet.getString("Academic_Year");
                String yr_of_study = resultSet.getString("Year_Of_Study");

                observableList_semester.addAll(semester);
                observableList_academic_yr.addAll(academic_YR);
                observableList_yr_of_study.addAll(yr_of_study);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        combobox_semester.setItems(observableList_semester);
        combobox_academic_Yr.setItems(observableList_academic_yr);
        combobox_yr_of_study.setItems(observableList_yr_of_study);
    }

    @FXML
    void Cancel(ActionEvent event) {

    }

    @FXML
    void view_timetable(ActionEvent event) {

        try {
            ((Node) event.getSource()).getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource("/User_Interface/Filtered_Timetable.fxml"));
            Stage stage = new Stage();
            JFXDecorator decorator = new JFXDecorator(stage, root, false, true, true);
            decorator.setCustomMaximize(true);
            Scene scene = new Scene(decorator);
            scene.getStylesheets().add(getClass().getResource("/CSS/tables.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/CSS/main_page.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void fetch_timetable_data(){
        String course = comboBox_course.getValue().toString();
        String semseter = comboBox_semester.getValue().toString();
        String academic_yr = comboBox_academic_yr.getValue().toString();
        String Yr_of_study = comboBox_yr_of_study.getValue().toString();
        String query = "SELECT * FROM Timetable_Data WHERE Course_Name = '"+course+"' AND Semester = '"+semseter+"' AND Academic_Year = '"+academic_yr+"' AND Year_Of_Study = '"+Yr_of_study+"'";
        resultSet = database_handler.execQuery(query);
        try {
            while (resultSet.next()) {
                String department = resultSet.getString("Department");
                Filtered_Timetable_Controller.l_department.setText(department);

                String course_name = resultSet.getString("Course_Name");
                Filtered_Timetable_Controller.l_course.setText(course_name);

                String unit_name = resultSet.getString("Unit_Name");
                String unit_code = resultSet.getString("Unit_Code");

                String yr_of_study = resultSet.getString("Year_Of_Study");
                Filtered_Timetable_Controller.l_yr_of_study.setText(yr_of_study);

                String lecturer = resultSet.getString("Lecturer_Name");
                String time = resultSet.getString("Meeting_Time");
                String venue = resultSet.getString("Room");

                String semester = resultSet.getString("Semester");
                Filtered_Timetable_Controller.l_semester.setText(semester);

                String academic_YR = resultSet.getString("Academic_Year");
                Filtered_Timetable_Controller.l_academic_yr.setText(academic_YR);

                String generation_time = resultSet.getString("Generation_Time");
                Filtered_Timetable_Controller.l_generation_time.setText(generation_time);

                Filtered_Timetable_Controller.observableList_td.add(new Filtered_Timetable_Controller.Timetable_Data(department, course_name, unit_code, unit_name, yr_of_study, venue, lecturer, time));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
