package reception;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ReceptionMainController {
    public BorderPane mainBorder;
    public AnchorPane centerBorder;
    public Label logout;
    public Label nameLB;

    public void initialize() {
        nameLB.setText(ReceptionLoginController.sendName);
    }


    public void addCustomer(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) mainBorder.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("CustomerForm.fxml"))));
    }

    public void addBooking(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) mainBorder.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("booking/Booking.fxml"))));

    }

    public void addPayment(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) mainBorder.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("payment/Payment.fxml"))));
    }

    public void cleanRoom(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("/reception/cleanRoom/CleanRoom.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Clan Room");
        stage.show();


    }

    public void btnLogout(MouseEvent mouseEvent) throws IOException {

        Stage window = (Stage) mainBorder.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/mainForm/MainForm.fxml"))));

    }
}
