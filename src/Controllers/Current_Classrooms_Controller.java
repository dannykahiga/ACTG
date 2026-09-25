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
import javafx.scene.control.TreeItem;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Current_Classrooms_Controller implements Initializable {

    ResultSet resultSet;
    Database_Handler database_handler = Database_Handler.getInstance();
    @FXML
    private JFXTreeTableView<Class_Rooms> table_current_classrooms;
    private ObservableList<Class_Rooms> observableList_classrooms = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        fetch_class_rooms();

        JFXTreeTableColumn<Class_Rooms, String> column_id = new JFXTreeTableColumn<>("ID");
        column_id.setCellValueFactory(param -> param.getValue().getValue().id);
        column_id.setPrefWidth(194.0);

        JFXTreeTableColumn<Class_Rooms, String> column_number = new JFXTreeTableColumn<>("Number");
        column_number.setCellValueFactory(param -> param.getValue().getValue().number);
        column_number.setPrefWidth(194.0);

        JFXTreeTableColumn<Class_Rooms, String> column_capacity = new JFXTreeTableColumn<>("Capacity");
        column_capacity.setCellValueFactory(param -> param.getValue().getValue().capacity);
        column_capacity.setPrefWidth(194.0);

        final TreeItem<Class_Rooms> root = new RecursiveTreeItem<Class_Rooms>(observableList_classrooms, RecursiveTreeObject::getChildren);
        table_current_classrooms.getColumns().setAll(column_id, column_number, column_capacity);
        table_current_classrooms.setShowRoot(false);
        table_current_classrooms.setRoot(root);
    }


    private void fetch_class_rooms() {
        String query = "SELECT * FROM Room";
        resultSet = database_handler.execQuery(query);
        try {
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String number = resultSet.getString("Number");
                String capacity = String.valueOf(resultSet.getInt("Capacity"));
                observableList_classrooms.add(new Class_Rooms(id, number, capacity));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class Class_Rooms extends RecursiveTreeObject<Class_Rooms> {
        StringProperty id;
        StringProperty number;
        StringProperty capacity;

        public Class_Rooms(String id, String number, String capacity) {
            this.id = new SimpleStringProperty(id);
            this.number = new SimpleStringProperty(number);
            this.capacity = new SimpleStringProperty(capacity);
        }
    }
}
