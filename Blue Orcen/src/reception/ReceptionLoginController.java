package reception;

import adminForm.AddUserController;
import adminForm.AdminLoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class ReceptionLoginController {
static String sendName="";
    public AnchorPane receptionLogin;
    public TextField getUserName;
    public TextField getPwd;
    public Label error;
   AddUserController ar[]=AddUserController.getDefaultUser();
    public void btnLogin(ActionEvent actionEvent) throws IOException {
            if(check()){
                Stage window =(Stage) receptionLogin.getScene().getWindow();
                window.setScene(new Scene(FXMLLoader.load(getClass().getResource("ReceptionMain.fxml"))));

            }else{
                error.setVisible(true);
            }

    }public boolean check(){
        for (AddUserController a : ar) {
            try {
                if (a.getName().equals(getUserName.getText()) && a.getPwd().equals(getPwd.getText())) {
                    sendName = a.getName();
                    return true;
                }
            } catch (Exception e) {
                return false;
            }

        }
        return false;
    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) receptionLogin.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/mainForm/MainForm.fxml"))));
    }
}
