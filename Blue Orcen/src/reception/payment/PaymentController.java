package reception.payment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import reception.Customer;
import reception.CustomerFormController;
import reception.booking.AddBooking;
import reception.booking.BookingController;

import java.io.IOException;

public class PaymentController {
    public GridPane mainGridForm;
    public JFXTextField cId;
    public JFXTextField cName;
    public JFXTextField cEmail;
    public JFXTextField cAddress;
    public JFXTextField cNumber;
    public TableView <Payment>mainTable;
    public TableColumn tBill;
    public TableColumn tNic;
    public TableColumn tName;
    public TableColumn tRoom;
    public TableColumn tMeal;
    public TableColumn tPrice;
    public TableColumn tArrived;
    public TableColumn tTime;
    public TableColumn tQty;
    public TableColumn tPayment;
    public static Payment sendPayment;
    static int billIncrement;
    static boolean isOn;
            public static ObservableList <Payment> obPaymentLint= FXCollections.observableArrayList();

    public JFXButton btnBack;

    public void initialize(){
                mainTable.refresh();
            for (AddBooking b : BookingController.obBookingList) {
        if(isCheckNic(b) && b.isPaid()) {
                Payment p = new Payment();
                p.setBill("RF00" + billIncrement++);
                p.setNic(b.getNic());
                p.setName(b.getName());
                p.setRoom(b.getRoomNumber());
                p.setMeal(b.getMealPackage());
                p.setPrice(b.getPrice());
                p.setArrived(b.getBookingDate());
                p.setTime(b.getInTime());
                p.setQty(2);
                p.setPrice(b.getPrice());

                obPaymentLint.add(p);
        }
            }
                tBill.setCellValueFactory(new PropertyValueFactory<>("bill"));
                tNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
                tName.setCellValueFactory(new PropertyValueFactory<>("name"));
                tRoom.setCellValueFactory(new PropertyValueFactory<>("room"));
                tMeal.setCellValueFactory(new PropertyValueFactory<>("meal"));
                tArrived.setCellValueFactory(new PropertyValueFactory<>("arrived"));
                tTime.setCellValueFactory(new PropertyValueFactory<>("time"));
                tQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
                tPayment.setCellValueFactory(new PropertyValueFactory<>("payment"));
                tPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
                for(Payment p:obPaymentLint){
                    Button btnPayment=new Button("Payment");
                    btnPayment.setStyle("-fx-border-color: blue;-fx-border-width: 2;-fx-border-radius: 3");
                    btnPayment.setCursor(Cursor.HAND);
                    p.setPayment(btnPayment);
                    p.getPayment().setOnAction(event -> {
                        isOn=true;
                        sendPayment=p;
                        Stage window = (Stage)mainGridForm.getScene().getWindow();
                        try {
                            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("BillPayment.fxml"))));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    });
                    mainTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        sentSelectValue(newValue);
                    });


                mainTable.setItems(obPaymentLint);
                }
            }
    public void btnBack(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) mainGridForm.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/reception/ReceptionMain.fxml"))));


    }public boolean isCheckNic(AddBooking b){

            for(Payment p:obPaymentLint){
            if(b.getNic().equals(p.getNic()))return false;
            }

        return true;
    }


    public void btnTable(MouseEvent mouseEvent) {

    }public void sentSelectValue(Payment p){
        for(Customer c: CustomerFormController.obCustomerList){
            if(p.getNic().equals(c.getId())){
                cId.setPromptText(c.getId());
                cName.setPromptText(c.getName());
                cEmail.setPromptText(c.getEmail());
                cAddress.setPromptText(c.getAddress());
                cNumber.setPromptText(c.getNumber());

            }
        }

    }
}
