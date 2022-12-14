package adminForm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminLoginController  {

    static String sendName="";
    public AnchorPane adminLogin;
    public  TextField getUserName;
    public  TextField getPwd;
    public Label error;
 AddUserController ar[]= AddUserController.getDefaultUser();

    public void btnLogin(ActionEvent actionEvent) throws IOException {
        System.out.println();

       if(check()){
           Stage window =(Stage) adminLogin.getScene().getWindow();
           window.setScene(new Scene(FXMLLoader.load(getClass().getResource("AdminMainForm.fxml"))));
           System.out.println("Done");
       }else{
            error.setVisible(true);
       }

    }public boolean check(){
        for (int i = 0; i < ar.length; i++) {
            try {
            if(ar[i].getName().equals(getUserName.getText()) && ar[i].getPwd().equals(getPwd.getText())){
                sendName=ar[i].getName();
                return true;
            }
            }catch (Exception e){
                return false;
            }

        }
        return false;
    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) adminLogin.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/mainForm/MainForm.fxml"))));
    }
}
