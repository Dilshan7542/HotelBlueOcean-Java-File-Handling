package reception;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import reception.booking.AddBooking;
import reception.booking.BookingController;

import java.io.IOException;

public class CustomerFormController {
    public BorderPane mainFrame;
    public JFXTextField cGetId;
    public JFXTextField cGetName;
    public JFXTextField cGetEmail;
    public JFXTextField cGetAddress;
    public JFXTextField cGetNumber;
    public TableColumn tID;
    public TableColumn tName;
    public TableColumn tEmail;
    public TableColumn tAddress;
    public TableColumn tNumber;
    public TableColumn tBooking;
    public TableColumn tEdit;
    public TableColumn tDelete;
    public TableView mainTable;
  public static ObservableList <Customer> obCustomerList= FXCollections.observableArrayList();
  public  static Customer sendCustomerDetails;
  public  static Customer updateCustomer;


   static boolean isUpdate=false;
  public static boolean bookingSet=false;
    static {
        obCustomerList.add(new Customer("C001","Nimal","nimal@123","Panadura","075-0145565"));
        obCustomerList.add(new Customer("C002","Kamal","Kamal@123","Galle","076-0158543"));
        obCustomerList.add(new Customer("C003","Jayantha","Jayantha@123","Gampaha","075-0145541"));
        obCustomerList.add(new Customer("C004","Bandara","Bandara@123","Galle","076-0125544"));
        obCustomerList.add(new Customer("C005","Nizal","nizal@123","Panadura","075-014577"));
        obCustomerList.add(new Customer("C006","jhone","jhone@123","Colombo","078-0158548"));
        obCustomerList.add(new Customer("C007","Iresha","hashan@123","Yagoda","071-0145541"));
        obCustomerList.add(new Customer("C008","Hashan","hashan@123","Gampaha","076-0145544"));
        obCustomerList.add(new Customer("C009","Bandara","bandara@123","Galle","076-0125544"));
        obCustomerList.add(new Customer("C010","Shanaka","shanaka@123","Panadura","075-014577"));
    }
    public void initialize(){
        tID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tNumber.setCellValueFactory(new PropertyValueFactory<>("Number"));
        tBooking.setCellValueFactory(new PropertyValueFactory<>("btnBooking"));
        tEdit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        tDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
        System.out.println("initializer called");
        buttonFunction();
        for(Customer c:obCustomerList){
        for(AddBooking b: BookingController.obBookingList){
            if(c.getId().equals(b.getNic())){
                c.getBtnBooking().setText("Booked");
                c.getBtnBooking().setStyle("-fx-background-color: GreenYellow");
                c.setBooked(true);
                c.getBtnDelete().setDisable(true);
                if(b.isPaid()==false){
                    c.getBtnBooking().setStyle("-fx-background-color: BlueViolet");
                    c.getBtnBooking().setText("Paid");
                }
            }
        }

        }

        mainTable.setItems(obCustomerList);

    }

    public void saveBtn(ActionEvent actionEvent) {
        if(isUpdate){
            updateRow(updateCustomer);
            mainTable.refresh();
            defaultTextFields();

        }else{
            String id=cGetId.getText();
            String name=cGetName.getText();
            String email=cGetEmail.getText();
            String address=cGetAddress.getText();
            String number=cGetNumber.getText();
            int num=0;
            try {
                num=Integer.parseInt(number);
                String isEmpty[]={id,name,email,address,number};
                if(isCheck(id) && isValid(isEmpty)){
                    obCustomerList.add(new Customer(id,name,email,address,number));

                    initialize();
                    defaultTextFields();

                }
            }catch (Exception e){
                Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Please Input Valid Number");
                a.show();
            }
        }




    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) mainFrame.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("ReceptionMain.fxml"))));
    }public boolean isCheck(String id){
        for(Customer c:obCustomerList){
            if(c.getId().equals(id)){
                Alert a=new Alert(Alert.AlertType.WARNING,"Id is Exist...");
                a.show();
                return false;

            }
        }
        return true;
    }public boolean isValid(String check[]){
        for(String s:check)if(s.trim().isEmpty()){
        Alert a=new Alert(Alert.AlertType.WARNING,"Please input Valid Value");
        a.show();
        return false;
        }
        return true;
    }public void buttonFunction(){
        for(Customer c:obCustomerList){
            Button btnBooking=new Button("Booking"); // Booking button
            btnBooking.setStyle("-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 3");
            btnBooking.setCursor(Cursor.HAND);
            c.setBtnBooking(btnBooking);
            c.getBtnBooking().setOnAction(event -> {
                sendCustomerDetails=c;
                Stage window =(Stage) mainFrame.getScene().getWindow();
                try {
                    bookingSet=true;
                    window.setScene(new Scene(FXMLLoader.load(getClass().getResource("booking/Booking.fxml"))));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            Button btnEdit=new Button("Edit"); // Button Edit
            btnEdit.setStyle("-fx-border-color: blue;-fx-border-width: 2;-fx-border-radius: 3");
            btnEdit.setCursor(Cursor.HAND);
            c.setBtnEdit(btnEdit);
            c.getBtnEdit().setOnAction(event -> {
                    for(Customer btn:obCustomerList){
                        btn.getBtnEdit().setDisable(true);
                        btn.getBtnBooking().setDisable(true);
                        btn.getBtnDelete().setDisable(true);
                    }
                    c.getBtnEdit().setDisable(false);

                    cGetId.setPromptText(c.getId());
                    cGetId.setStyle("-fx-background-color: red");
                    cGetId.setDisable(true);
                    cGetId.clear();
                    cGetName.setPromptText(c.getName());
                    cGetName.setStyle("-fx-background-color: green");
                    cGetAddress.setPromptText(c.getAddress());
                    cGetAddress.setStyle("-fx-background-color: green");
                    cGetNumber.setPromptText(c.getNumber());
                    cGetNumber.setStyle("-fx-background-color: green");
                    cGetEmail.setPromptText(c.getEmail());
                    cGetEmail.setStyle("-fx-background-color: green");
                    isUpdate=true;
                    updateCustomer=c;
            });
            Button btnDelete=new Button("Delete"); // Button Delete
            btnDelete.setCursor(Cursor.HAND);
            btnDelete.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
            c.setBtnDelete(btnDelete);
            c.getBtnDelete().setOnAction(event -> {
                obCustomerList.remove(c);

            });






        }

    }public void defaultTextFields(){

        cGetId.setPromptText("Customer id");
        cGetId.setDisable(false);
        cGetId.setStyle(null);
        cGetId.clear();
        cGetName.setPromptText("Name");
        cGetName.setStyle(null);
        cGetName.clear();
        cGetAddress.setPromptText("Address");
        cGetAddress.setStyle(null);
        cGetAddress.clear();
        cGetNumber.setPromptText("Number");
        cGetNumber.setStyle(null);
        cGetNumber.clear();
        cGetEmail.setPromptText("Email");
        cGetEmail.setStyle(null);
        cGetEmail.clear();
        for(Customer btn:obCustomerList){
            btn.getBtnEdit().setDisable(false);
            btn.getBtnBooking().setDisable(false);
               btn.getBtnDelete().setDisable(false);
        }


    }public void updateRow(Customer c){
        String fields[]={cGetName.getText(),cGetEmail.getText(),cGetAddress.getText(),cGetNumber.getText()};
        int num=0;

        try{
            System.out.println("this");
            num=Integer.parseInt(cGetNumber.getText());
        if(isValid(fields)){

            isUpdate=false;
            c.setName(cGetName.getText());
            c.setEmail(cGetEmail.getText());
            c.setAddress(cGetAddress.getText());
            c.setNumber(cGetNumber.getText());
        }
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Please Input Valid Number");
            a.show();
        }

    }




}
