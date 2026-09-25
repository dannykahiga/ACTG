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

public class Filtered_Timetable_Controller implements Initializable {

    @FXML
    private JFXTreeTableView<Timetable_Data> table_filtered_timetable;
    public static JFXTreeTableView<Timetable_Data> t_filtered_timetable;

    @FXML
    private Label label_department;
    public static Label l_department;

    @FXML
    private Label label_course;
    public static Label l_course;

    @FXML
    private Label label_semester;
    public static Label l_semester;

    @FXML
    private Label label_academic_yr;
    public static Label l_academic_yr;

    @FXML
    private Label label_yr_of_study;
    public static Label l_yr_of_study;

    @FXML
    private Label label_generation_date;
    public static Label l_generation_time;

    public  static ObservableList<Timetable_Data> observableList_td = FXCollections.observableArrayList();

    ResultSet resultSet;

    Database_Handler database_handler = Database_Handler.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        t_filtered_timetable = table_filtered_timetable;

        l_department = label_department;
        l_course = label_course;
        l_semester = label_semester;
        l_academic_yr = label_academic_yr;
        l_yr_of_study = label_yr_of_study;
        l_generation_time = label_generation_date;

        table_filtered_timetable.setEditable(true);

        fetch_timetable_data();

        JFXTreeTableColumn<Timetable_Data, String> column_unit_code = new JFXTreeTableColumn<>("Code");
        column_unit_code.setCellValueFactory(param -> param.getValue().getValue().unit_code);
        column_unit_code.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_unit_name = new JFXTreeTableColumn<>("Name");
        column_unit_name.setCellValueFactory(param -> param.getValue().getValue().unit_name);
        column_unit_name.setPrefWidth(100.0);

        JFXTreeTableColumn<Timetable_Data, String> column_unit = new JFXTreeTableColumn<>("Unit");
        column_unit.setPrefWidth(100.0);
        column_unit.getColumns().setAll(column_unit_code, column_unit_name);

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
        table_filtered_timetable.getColumns().setAll(column_unit, column_venue, column_lecturer, column_time);
        table_filtered_timetable.setRoot(root);
        table_filtered_timetable.setShowRoot(false);

    }
    private void fetch_timetable_data() {
        Filter_Timetable_Controller.fetch_timetable_data();
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
