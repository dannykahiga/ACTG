package Controllers;

import com.jfoenix.controls.*;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.controlsfx.control.MaskerPane;
import org.controlsfx.control.PopOver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author loyal
 */
public class Main_PageController implements Initializable {

    public static BorderPane bp_main_page;
    public static AnchorPane page_loaded = null;
    public static Label l_spacer;
    public static Label l_service_type;
    public static Label label_service_type_extension;
    public static MaskerPane masker_pane;
    public static JFXDialog dialogConfirm = new JFXDialog();
    public static JFXDialog dialogConfirm_generate_tt = new JFXDialog();

    @FXML
    public MaskerPane maskerpane;
    public JFXDialogLayout layoutConfirm = new JFXDialogLayout();
    public JFXDialogLayout layoutConfirm_Generate_tt = new JFXDialogLayout();

    HamburgerSlideCloseTransition hamburgerSlideCloseTransition;
    PopOver popOverAccounts = new PopOver();
    PopOver popOverHelp = new PopOver();
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private BorderPane borderPaneMainPage;
    @FXML
    private Label lable_spacer;
    @FXML
    private Label lable_service_type;
    @FXML
    private Text text_menu;
    @FXML
    private JFXButton button_accounts;
    @FXML
    private JFXButton button_help;
    @FXML
    private StackPane stack_pane_parent;
    @FXML
    private Label label_service_extension;

    public static void closeGenerationNotification() {
        Task task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                dialogConfirm_generate_tt.show();
                Thread.sleep(5000);
                return null;
            }

            @Override
            protected void succeeded() {
                super.succeeded();
                dialogConfirm_generate_tt.close();
            }

        };
        new Thread(task).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hamburgerSlideCloseTransition = new HamburgerSlideCloseTransition(hamburger);
        hamburgerSlideCloseTransition.setRate(-1);
        bp_main_page = borderPaneMainPage;
        borderPaneMainPage.setCenter(page_loaded);
        l_spacer = lable_spacer;
        l_service_type = lable_service_type;
        label_service_type_extension = label_service_extension;

        masker_pane = maskerpane;
        masker_pane.setVisible(false);

        try {
            dialogConfirm.setContent(layoutConfirm);
            dialogConfirm.setOverlayClose(false);
            dialogConfirm.setTransitionType(JFXDialog.DialogTransition.CENTER);
            layoutConfirm.setStyle("-fx-background-color: #3f51b5");
            dialogConfirm.setDialogContainer(stack_pane_parent);
            layoutConfirm.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane rootConfrim = FXMLLoader.load(getClass().getResource("/User_Interface/Confirmation_Notification_Window.fxml"));
            layoutConfirm.setBody(rootConfrim);

            dialogConfirm_generate_tt.setContent(layoutConfirm_Generate_tt);
            dialogConfirm_generate_tt.setOverlayClose(false);
            dialogConfirm_generate_tt.setTransitionType(JFXDialog.DialogTransition.CENTER);
            layoutConfirm_Generate_tt.setStyle("-fx-background-color: #3f51b5");
            dialogConfirm_generate_tt.setDialogContainer(stack_pane_parent);
            layoutConfirm_Generate_tt.getStylesheets().add(getClass().getResource("/CSS/Dialog_Add_Details.css").toExternalForm());
            Pane rootConfirm_generate_tt = FXMLLoader.load(getClass().getResource("/User_Interface/Confirm_Generate_Timetable.fxml"));
            layoutConfirm_Generate_tt.setBody(rootConfirm_generate_tt);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void showDrawer(MouseEvent event) throws IOException {
        drawer.setOverLayVisible(false);
        FXMLLoader fxmlLoader = new FXMLLoader();
        AnchorPane pane = fxmlLoader.load(getClass().getResource("/User_Interface/DrawerContent.fxml").openStream());
        double width = drawer.getWidth();
        double height = drawer.getHeight();
        pane.setPrefWidth(width);
        pane.setPrefHeight(height);
        hamburgerSlideCloseTransition.setRate(hamburgerSlideCloseTransition.getRate() * -1);
        hamburgerSlideCloseTransition.play();
        if (drawer.isShown()) {
            drawer.close();
            if (borderPaneMainPage.getCenter() == page_loaded) {
                DrawerContentController.p_add_details.setVisible(false);

            } else if (borderPaneMainPage.getCenter() == DrawerContentController.pane_add_dtls) {
                DrawerContentController.p_add_details.setVisible(true);

            } else if (borderPaneMainPage.getCenter() == DrawerContentController.pane_timetable_options) {
                DrawerContentController.p_timetable_optns.setVisible(true);

            } else if (borderPaneMainPage.getCenter() == DrawerContentController.pane_db) {
                DrawerContentController.p_database.setVisible(true);

            }
            text_menu.setText("Open Menu");
            drawer.setMouseTransparent(true);
        } else {
            drawer.open();
            if (borderPaneMainPage.getCenter() == page_loaded) {
                DrawerContentController.p_add_details.setVisible(false);

            } else if (borderPaneMainPage.getCenter() == DrawerContentController.pane_add_dtls) {
                DrawerContentController.p_add_details.setVisible(true);

            } else if (borderPaneMainPage.getCenter() == DrawerContentController.pane_timetable_options) {
                DrawerContentController.p_timetable_optns.setVisible(true);

            } else if (borderPaneMainPage.getCenter() == DrawerContentController.pane_db) {
                DrawerContentController.p_database.setVisible(true);

            }
            text_menu.setText("Close Menu");

            drawer.setMouseTransparent(false);
        }

        drawer.setSidePane(pane);
    }

    @FXML
    private void showAccountsPopup(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/User_Interface/User_Accounts.fxml"));
        popOverAccounts.setContentNode(root);
        popOverAccounts.setAnimated(true);
        popOverAccounts.setDetachable(false);
        popOverAccounts.setFadeInDuration(Duration.seconds(1.0));
        popOverAccounts.setFadeOutDuration(Duration.seconds(1.0));
        popOverAccounts.setCornerRadius(10);
        popOverAccounts.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        popOverAccounts.show(button_accounts);
    }

    @FXML
    private void showHelpPopup(ActionEvent event) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/User_Interface/Help.fxml"));
        popOverHelp.setContentNode(root);
        popOverHelp.setAnimated(true);
        popOverHelp.setDetachable(false);
        popOverHelp.setFadeInDuration(Duration.seconds(1.0));
        popOverHelp.setFadeOutDuration(Duration.seconds(1.0));
        popOverHelp.setCornerRadius(10);
        popOverHelp.setArrowLocation(PopOver.ArrowLocation.TOP_RIGHT);
        popOverHelp.show(button_help);
    }

}
