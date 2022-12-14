package reception.booking;

import adminForm.AddRooms;
import adminForm.MealPackage.AddMeal;
import adminForm.MealPackage.MealFormController;
import adminForm.RoomController;
import com.jfoenix.controls.*;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import reception.Customer;
import reception.CustomerFormController;
import reception.payment.Payment;
import reception.payment.PaymentController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class BookingController {


    public GridPane mainGridForm;
    public JFXTextField cSetID;
    public JFXTextField cSetName;
    public JFXTextField cSetEmail;
    public JFXTextField cSetAddress;
    public JFXTextField cSetNumber;
    public JFXComboBox <AddRooms>cmbRoom;
    public JFXComboBox <AddMeal>cmbMeal;
    public JFXDatePicker bookingDate;
    public JFXTimePicker bookingTime;
    public JFXDatePicker leaveDate;
    public JFXTimePicker leaveTime;
    public JFXTextField cGetPrice;
    public  TableView bookingMainTable;
    public TableColumn tBID;
    public TableColumn tBNic;
    public TableColumn tBName;
    public TableColumn tBRoomNumber;
    public TableColumn tBMeal;
    public TableColumn tBDate;
    public TableColumn tBView;
    public TableColumn tBTime;
    public TableColumn tBEdit;
    public TableColumn tBCancel;
    public static double price=0.0d;
     static double roomPrice=0.0d;
     static double mealPrice=0.0d;
     static int incrementBID;
   public static ObservableList <AddBooking> obBookingList= FXCollections.observableArrayList();
    public JFXButton addBtn;
    boolean isUpdate=false;
    AddBooking updateBooking;
    boolean isBooked;
    boolean isPrice=true;

    public void initialize(){
        addComboBox();
        if(CustomerFormController.bookingSet){
            addBtn.setDisable(false);
        }

        tBID.setCellValueFactory(new PropertyValueFactory<>("bookingID"));
        tBNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        tBName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tBRoomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        tBMeal.setCellValueFactory(new PropertyValueFactory<>("mealPackage"));
        tBDate.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));
        tBTime.setCellValueFactory(new PropertyValueFactory<>("inTime"));
        tBView.setCellValueFactory(new PropertyValueFactory<>("btnView"));
        tBEdit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        tBCancel.setCellValueFactory(new PropertyValueFactory<>("btnCancel"));
            if(isUpdate==false){

        buttonFunction();
            }
        bookingMainTable.refresh();


        bookingTime.setValue(LocalTime.now());
        leaveTime.setValue(LocalTime.now());
        bookingDate.setValue(LocalDate.now());
        leaveDate.setValue(LocalDate.now());
        price=roomPrice+mealPrice;
        cGetPrice.setPromptText(""+price);
        cGetPrice.setDisable(true);
        if(isPrice){
            addBtn.setDisable(true);

        }else{
            isPrice=true;
        }

        if(CustomerFormController.bookingSet){
            addBtn.setDisable(false);
            Customer customer=CustomerFormController.sendCustomerDetails;
            if(customer.isBooked()){
                addBtn.setDisable(true);
            }
            cSetID.setPromptText(customer.getId());
            cSetID.setDisable(true);
            cSetName.setPromptText(customer.getName());
            cSetName.setDisable(true);
            cSetEmail.setPromptText(customer.getEmail());
            cSetEmail.setDisable(true);
            cSetAddress.setPromptText(customer.getAddress());
            cSetAddress.setDisable(true);
            cSetNumber.setPromptText(customer.getNumber());
            cSetNumber.setDisable(true);

            CustomerFormController.bookingSet=false;
        }else{
            customerHide();
        }
            bookingMainTable.refresh();



        bookingMainTable.setItems(obBookingList);
    }

    public void btnAddBooking(ActionEvent actionEvent) throws IOException {
            addBtn.setDisable(true);
        if(isUpdate){
            update(updateBooking);
            bookingMainTable.refresh();
            isUpdate=false;
            initialize();
            Stage window =(Stage) mainGridForm.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("Booking.fxml"))));

        }else{
            if (isCheck()) {
                if (cmbMeal.getSelectionModel().isEmpty() || cmbRoom.getSelectionModel().isEmpty()) {
                    Alert a = new Alert(Alert.AlertType.WARNING, "Please Select Meal and Room");
                    a.show();
                    addBtn.setDisable(false);
                } else {
                    String bookingID = "B00" + incrementBID++;
                    String nic = cSetID.getPromptText();
                    String name = cSetName.getPromptText();
                    String mealPackage = "";
                    String roomNumber = "";
                    if (cmbMeal.getValue() != null && cmbRoom.getValue() != null) {
                        roomNumber = cmbRoom.getValue().getRoomId();
                        mealPackage = cmbMeal.getValue().getFoodName();
                    }


                    LocalDate bDate;
                    LocalDate lDate;
                    LocalTime inTime;
                    LocalTime outTime;
                    AddBooking b = new AddBooking();
                    try {
                        bDate = bookingDate.getValue();
                        lDate = leaveDate.getValue();
                        inTime = bookingTime.getValue();
                        outTime = leaveTime.getValue();
                        b.setBookingID(bookingID);
                        b.setNic(nic);
                        b.setName(name);
                        b.setMealPackage(mealPackage);
                        b.setRoomNumber(roomNumber);
                        b.setBookingDate(bDate);
                        b.setLeaveDate(lDate);
                        b.setInTime(inTime);
                        b.setOutTime(outTime);
                        b.setComboRoom(cmbRoom.getValue());
                        b.setComboMeal(cmbMeal.getValue());
                        b.setPrice(price);
                        b.setPaid(true);
                        obBookingList.add(b);
                        bookingMainTable.setItems(obBookingList);
                        Alert booked=new Alert(Alert.AlertType.INFORMATION);
                        booked.setHeaderText(CustomerFormController.sendCustomerDetails.getEmail());
                        booked.setContentText("Booked Completed >>>>>>>");
                        booked.show();
                        addBtn.setDisable(true);
                        initialize();
                    } catch (Exception e) {
                        System.out.println(e);
                        Alert a = new Alert(Alert.AlertType.WARNING, "Wrong insert");
                        a.show();
                    }
                }
            }
        }

addBtn.setDisable(true);
    }

    public void backBtn(ActionEvent actionEvent) throws IOException {
        price=0.0d;
        roomPrice=0.0;
        mealPrice=0.0d;
        Stage window =(Stage) mainGridForm.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("/reception/CustomerForm.fxml"))));
    }

    public void leaveDate(ActionEvent actionEvent) {
    }

    public void leaveTime(ActionEvent actionEvent) {
    }

    public void cmbRoom(ActionEvent actionEvent) {
        AddRooms r=cmbRoom.getValue();
       roomPrice=r.getPrice();
isPrice=false;
       initialize();


    }

    public void cmbMeal(ActionEvent actionEvent) {
        AddMeal m=cmbMeal.getValue();
      mealPrice=m.getPrice();
            isPrice=false;
            initialize();


    }

    public void bookingDate(ActionEvent actionEvent) {
    }

    public void bookingTime(ActionEvent actionEvent) {

    }public void addComboBox(){

            for(AddRooms r:RoomController.addRooms){
                    if(isFindRoom(r)){
                        isRoomAvailable(r);
                    }

            }
            for(AddMeal m: MealFormController.obMealList){
                    isMealAvailable(m); // is not NEED-------------------------->>>>>>>>
                if(isFindMeal(m)){
                }
            }


    }public void isRoomAvailable(AddRooms r){
            cmbRoom.getItems().add(r);

    }public boolean isFindRoom(AddRooms r){
        for(AddBooking b:obBookingList){
            if(r.getRoomId().equals(b.getRoomNumber())){
                return false;
            }
        }
        return true;
    }public boolean isFindMeal(AddMeal m){
        for(AddBooking b:obBookingList){
            if(m.getFoodName().equals(b.getMealPackage())){
                return false;
            }
        }
        return true;
    }public void isMealAvailable(AddMeal m){
        cmbMeal.getItems().add(m);
    }
    public void buttonFunction(){
       for(AddBooking b:obBookingList){
           Button btnView=new Button("   View  ");
           btnView.setStyle("-fx-border-color: green;-fx-border-width: 2;-fx-border-radius: 3");
           b.setBtnView(btnView);
           b.getBtnView().setCursor(Cursor.HAND);
           b.getBtnView().setOnAction(event -> {
               for(AddBooking  btn: obBookingList){
                   btn.getBtnView().setText("   View  ");
               btn.getBtnView().setStyle("-fx-border-color: green;-fx-border-width: 2");
               }
               b.getBtnView().setText("   Out   ");
               b.getBtnView().setStyle("-fx-border-color: DarkMagenta;-fx-border-width: 2");

               addBtn.setDisable(true);
                allSetFields(b);



           });
           Button btnEdit=new Button("Edit"); // Edit button-------->>>
           btnEdit.setStyle("-fx-border-color: blue;-fx-border-width: 2;-fx-border-radius: 3");
           b.setBtnEdit(btnEdit);
           b.getBtnEdit().setCursor(Cursor.HAND);
           b.getBtnEdit().setOnAction(event -> {
                isUpdate=true;
               for(AddBooking  btn: obBookingList){
                   btn.getBtnView().setDisable(true);
                   btn.getBtnEdit().setDisable(true);
                   btn.getBtnCancel().setDisable(true);
               }
               b.getBtnEdit().setDisable(false);
               addBtn.setDisable(false);
                allSetFields(b);
                updateBooking=b;
           });
           Button btnCancel=new Button("Cancel");
           btnCancel.setStyle("-fx-border-color: red;-fx-border-width: 2;-fx-border-radius: 3");
           b.setBtnCancel(btnCancel);
           b.getBtnCancel().setCursor(Cursor.HAND);
           if(b.isPaid()==false){
               b.getBtnCancel().setDisable(true);
               b.getBtnCancel().setText("Paid");
               b.getBtnCancel().setStyle("-fx-background-color: BlueViolet");
               b.getBtnEdit().setDisable(true);
               b.getBtnView().setDisable(true);
           }
            b.getBtnCancel().setOnAction(event -> {

                b.setPaid(false);
                try {

                for(Payment p: PaymentController.obPaymentLint){
                    if(p.getNic().equals(b.getNic())){
                        PaymentController.obPaymentLint.remove(p);
                    }
                }
                }catch (Exception e){
                    System.out.println("Delete Error");
                }
                for(Customer c:CustomerFormController.obCustomerList){
                    if(c.getId().equals(b.getNic())){
                        c.setBooked(false);
                    }
                }
                obBookingList.remove(b);


            });


       }
    }public Customer getCustomer(AddBooking b){
        Customer a;
        for(Customer c:CustomerFormController.obCustomerList){

            if(c.getId().equals(b.getNic())){
                return c;
            }
        }
        return new Customer();
    }public boolean isCheck(){
        for(AddBooking b:obBookingList){
            if(b.getNic().equals(cSetID.getPromptText())){
                Alert a=new Alert(Alert.AlertType.WARNING,"This Customer Already booked");
                a.show();
                return false;
            }
        }
        if(cSetID.getPromptText().isEmpty()){
         Alert a=new Alert(Alert.AlertType.WARNING,"Please Add Customer");
         a.show();
        return false;
        }
        return true;
    }public void allSetFields(AddBooking b){
        cmbRoom.setPromptText(b.getComboRoom().toString());
        cmbMeal.setPromptText(b.getComboMeal().toString());
        Customer c=getCustomer(b);
        cSetID.setPromptText(c.getId());
        cSetName.setPromptText(c.getName());
        cSetEmail.setPromptText(c.getEmail());
        cSetAddress.setPromptText(c.getAddress());
        cSetNumber.setPromptText(c.getNumber());
        cGetPrice.setPromptText(""+b.getPrice());
        bookingDate.setValue(b.getBookingDate());
        bookingTime.setValue(b.getInTime());
        leaveDate.setValue(b.getLeaveDate());
        leaveTime.setValue(b.getOutTime());
    }public void update(AddBooking b){
        addComboBox();
       if(cmbMeal.getValue() !=null){
            b.setComboMeal(cmbMeal.getValue());
            b.setMealPackage(cmbMeal.getValue().getFoodName());
       }
        if(cmbRoom.getValue() !=null){
            b.setComboRoom(cmbRoom.getValue());
            b.setRoomNumber(cmbRoom.getValue().getRoomId());
        }
            b.setBookingDate(bookingDate.getValue());
            b.setLeaveDate(leaveDate.getValue());
            b.setInTime(bookingTime.getValue());
            b.setOutTime(leaveTime.getValue());
            b.setPrice(price);

    }public void customerHide(){
        cSetID.setDisable(true);
        cSetName.setDisable(true);
        cSetEmail.setDisable(true);
        cSetAddress.setDisable(true);
        cSetNumber.setDisable(true);
    }
}
