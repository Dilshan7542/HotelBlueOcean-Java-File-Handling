package adminForm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import mainForm.*;
import java.io.IOException;

public class AdminMainFormController{
    public Label getUserName;
    public AnchorPane adminMain;
    public Label type;
    public AnchorPane adminMainForm;


    public void initialize(){
        getUserName.setText(AdminLoginController.sendName);
    }


    public void btnRooms(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("Room.fxml"));
        adminMain.getChildren().add(load);
    }

    public void logout(MouseEvent mouseEvent) throws IOException {
        Stage window =(Stage) adminMainForm.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/mainForm/MainForm.fxml"))));

    }

    public void btnMeal(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("MealPackage/MealForm.fxml"));
        adminMain.getChildren().add(load);
        System.out.println("clicked");
    }

    public void btnIncome(ActionEvent actionEvent) throws IOException {
        Parent load = FXMLLoader.load(getClass().getResource("income/IncomeForm.fxml"));
        adminMain.getChildren().add(load);
    }

    public void btnUserAdd(ActionEvent actionEvent) {
    }
}
