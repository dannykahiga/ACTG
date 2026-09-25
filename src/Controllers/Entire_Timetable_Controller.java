package Controllers;

import Database_Controller.Database_Handler;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Entire_Timetable_Controller implements Initializable {
    @FXML
    private Label label_semester;

    @FXML
    private Label label_academic_yr;

    @FXML
    private Label label_generation_date;
    @FXML
    private JFXTreeTableView<Timetable_Data> table_entire_timetable;

    private ObservableList<Timetable_Data> observableList_td = FXCollections.observableArrayList();

    ResultSet resultSet;

    Database_Handler database_handler = Database_Handler.getInstance();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        table_entire_timetable.setEditable(true);

        fetch_timetable_data();

        JFXTreeTableColumn<Timetable_Data, String> column_dep = new JFXTreeTableColumn<>("Department");
        column_dep.setCellValueFactory(param -> param.getValue().getValue().department);
        column_dep.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_course = new JFXTreeTableColumn<>("Course");
        column_course.setCellValueFactory(param -> param.getValue().getValue().course);
        column_course.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_unit_code = new JFXTreeTableColumn<>("Code");
        column_unit_code.setCellValueFactory(param -> param.getValue().getValue().unit_code);
        column_unit_code.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_unit_name = new JFXTreeTableColumn<>("Name");
        column_unit_name.setCellValueFactory(param -> param.getValue().getValue().unit_name);
        column_unit_name.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_unit = new JFXTreeTableColumn<>("Unit");
        column_unit.setPrefWidth(100.0);
        column_unit.getColumns().setAll(column_unit_code, column_unit_name);

        JFXTreeTableColumn<Timetable_Data, String> column_yos = new JFXTreeTableColumn<>("Year_Of_Study");
        column_yos.setCellValueFactory(param -> param.getValue().getValue().yr_of_study);
        column_yos.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_venue = new JFXTreeTableColumn<>("Venue");
        column_venue.setCellValueFactory(param -> param.getValue().getValue().venue);
        column_venue.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_lecturer = new JFXTreeTableColumn<>("Lecturer");
        column_lecturer.setCellValueFactory(param -> param.getValue().getValue().lecturer);
        column_lecturer.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_time = new JFXTreeTableColumn<>("Time");
        column_time.setCellValueFactory(param -> param.getValue().getValue().time);
        column_time.setPrefWidth(100.0);

        final TreeItem<Timetable_Data> root = new RecursiveTreeItem<Timetable_Data>(observableList_td, RecursiveTreeObject::getChildren);
        table_entire_timetable.getColumns().setAll(column_dep, column_course, column_unit, column_yos, column_venue, column_lecturer, column_time);
        table_entire_timetable.setRoot(root);
        table_entire_timetable.setShowRoot(false);

    }

    private void fetch_timetable_data() {
        String query = "SELECT * FROM Timetable_Data";
        resultSet = database_handler.execQuery(query);
        try {
            while (resultSet.next()) {
                String department = resultSet.getString("Department");
                String course_name = resultSet.getString("Course_Name");
                String unit_name = resultSet.getString("Unit_Name");
                String unit_code = resultSet.getString("Unit_Code");
                String yr_of_study = resultSet.getString("Year_Of_Study");
                String lecturer = resultSet.getString("Lecturer_Name");
                String time = resultSet.getString("Meeting_Time");
                String venue = resultSet.getString("Room");
                String semester = resultSet.getString("Semester");
                String academic_YR = resultSet.getString("Academic_Year");
                String generation_time = resultSet.getString("Generation_Time");

                label_semester.setText(semester);
                label_academic_yr.setText(academic_YR);
                label_generation_date.setText(generation_time);

                observableList_td.add(new Timetable_Data(department, course_name, unit_code, unit_name, yr_of_study, venue, lecturer, time));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static class Timetable_Data extends RecursiveTreeObject<Timetable_Data> {
        StringProperty department;
        StringProperty course;
        StringProperty unit_name;
        StringProperty unit_code;
        StringProperty yr_of_study;
        StringProperty venue;
        StringProperty lecturer;
        StringProperty time;

        public Timetable_Data(String department, String course, String unit_name, String unit_code, String yr_of_study, String venue, String lecturer, String time) {
            this.department = new SimpleStringProperty(department);
            this.course = new SimpleStringProperty(course);
            this.unit_name = new SimpleStringProperty(unit_name);
            this.unit_code = new SimpleStringProperty(unit_code);
            this.yr_of_study = new SimpleStringProperty(yr_of_study);
            this.venue = new SimpleStringProperty(venue);
            this.lecturer = new SimpleStringProperty(lecturer);
            this.time = new SimpleStringProperty(time);
        }
    }
}
