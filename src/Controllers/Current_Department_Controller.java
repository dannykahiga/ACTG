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

public class Current_Department_Controller implements Initializable {

    Database_Handler database_handler = Database_Handler.getInstance();
    ResultSet resultSet;
    @FXML
    private JFXTreeTableView<Current_Departments> table_current_departments;
    private ObservableList<Current_Departments> observableList_deps = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        table_current_departments.setEditable(true);

        fetch_departments();

        JFXTreeTableColumn<Current_Departments, String> column_id = new JFXTreeTableColumn<>("ID");
        column_id.setCellValueFactory(param -> param.getValue().getValue().id);
        column_id.setPrefWidth(299.0);

        JFXTreeTableColumn<Current_Departments, String> column_name = new JFXTreeTableColumn<>("Name");
        column_name.setCellValueFactory(param -> param.getValue().getValue().name);
        column_name.setPrefWidth(299.0);

        final TreeItem<Current_Departments> root = new RecursiveTreeItem<Current_Departments>(observableList_deps, RecursiveTreeObject::getChildren);
        table_current_departments.setShowRoot(false);
        table_current_departments.getColumns().setAll(column_id, column_name);
        table_current_departments.setRoot(root);
    }

    private void fetch_departments() {
        try {
            String query = "SELECT * FROM Department";
            resultSet = database_handler.execQuery(query);
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Name");
                observableList_deps.add(new Current_Departments(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    static class Current_Departments extends RecursiveTreeObject<Current_Departments> {
        StringProperty id;
        StringProperty name;

        public Current_Departments(String id, String name) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
        }
    }
}
