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

public class Current_Lecturers_Controller implements Initializable {

    Database_Handler database_handler = Database_Handler.getInstance();
    ResultSet resultSet;
    @FXML
    private JFXTreeTableView<Lecturers> table_lecturers;
    private ObservableList<Lecturers> observableList_lecs = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fetch_lecturers();

        JFXTreeTableColumn<Lecturers, String> column_id = new JFXTreeTableColumn<>("ID");
        column_id.setCellValueFactory(param -> param.getValue().getValue().id);
        column_id.setPrefWidth(130.0);

        JFXTreeTableColumn<Lecturers, String> column_name = new JFXTreeTableColumn<>("Name");
        column_name.setCellValueFactory(param -> param.getValue().getValue().name);
        column_name.setPrefWidth(130.0);

        JFXTreeTableColumn<Lecturers, String> column_paddress = new JFXTreeTableColumn<>("Postal Address");
        column_paddress.setCellValueFactory(param -> param.getValue().getValue().postal_Address);
        column_paddress.setPrefWidth(130.0);

        JFXTreeTableColumn<Lecturers, String> column_email = new JFXTreeTableColumn<>("Email");
        column_email.setCellValueFactory(param -> param.getValue().getValue().email);
        column_email.setPrefWidth(260.0);

        JFXTreeTableColumn<Lecturers, String> column_phone_number = new JFXTreeTableColumn<>("Phone Number");
        column_phone_number.setCellValueFactory(param -> param.getValue().getValue().phone_number);
        column_phone_number.setPrefWidth(130.0);

        JFXTreeTableColumn<Lecturers, String> column_deps = new JFXTreeTableColumn<>("Department");
        column_deps.setCellValueFactory(param -> param.getValue().getValue().department);
        column_deps.setPrefWidth(130.0);

        final TreeItem<Lecturers> root = new RecursiveTreeItem<Lecturers>(observableList_lecs, RecursiveTreeObject::getChildren);
        table_lecturers.getColumns().setAll(column_id, column_name, column_paddress, column_email, column_phone_number, column_deps);
        table_lecturers.setRoot(root);
        table_lecturers.setShowRoot(false);

    }

    private void fetch_lecturers() {
        String query = "SELECT * FROM Lecturer";
        resultSet = database_handler.execQuery(query);
        try {
            while (resultSet.next()) {
                String id = resultSet.getString("ID");
                String name = resultSet.getString("Lecturer_Name");
                String postal_address = resultSet.getString("Postal_Address");
                String email = resultSet.getString("Email");
                String phone_number = resultSet.getString("Phone_Number");
                String department = resultSet.getString("Department");

                observableList_lecs.add(new Lecturers(id, name, postal_address, phone_number, email, department));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static class Lecturers extends RecursiveTreeObject<Lecturers> {
        StringProperty id;
        StringProperty name;
        StringProperty postal_Address;
        StringProperty email;
        StringProperty phone_number;
        StringProperty department;

        public Lecturers(String id, String name, String postal_Address, String email, String phone_number, String department) {
            this.id = new SimpleStringProperty(id);
            this.name = new SimpleStringProperty(name);
            this.postal_Address = new SimpleStringProperty(postal_Address);
            this.email = new SimpleStringProperty(email);
            this.phone_number = new SimpleStringProperty(phone_number);
            this.department = new SimpleStringProperty(department);
        }
    }
}
