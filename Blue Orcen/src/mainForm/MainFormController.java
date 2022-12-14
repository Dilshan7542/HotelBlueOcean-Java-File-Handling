package mainForm;


import adminForm.*;
import javafx.beans.binding.StringExpression;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainFormController {

    public AnchorPane mainForm;

    public void btnAdmin(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) mainForm.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/adminForm/AdminLogin.fxml"))));

    }

    public void btnReception(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) mainForm.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/reception/ReceptionLogin.fxml"))));
    }
}