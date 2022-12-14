package reception.payment;

import adminForm.AddRooms;
import adminForm.MealPackage.AddMeal;
import adminForm.MealPackage.MealFormController;
import adminForm.RoomController;
import adminForm.income.Income;
import adminForm.income.IncomeFormController;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import reception.Customer;
import reception.CustomerFormController;
import reception.booking.AddBooking;
import reception.booking.BookingController;
import reception.cleanRoom.Clean;
import reception.cleanRoom.CleanRoomController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class BillPaymentController {
    public GridPane mainGridForm;
    public JFXTextField cGetID;
    public JFXTextField cGetName;
    public JFXTextField cGetEmail;
    public JFXTextField cGetAddress;
    public JFXTextField cGetNumber;
    public JFXTextField cGetBillNumber;
    public TableView mainTable;
    public TableColumn tItem;
    public TableColumn tDes;
    public TableColumn tQty;
    public TableColumn tDiscount;
    public TableColumn tNetPrice;
    public TableColumn tGrossPrice;
    public TextField textTotal;
    public TextField txtExtra;
    public TextField txtDiscount;
    public TextField textNetAmount;
    AddRooms room;
    AddMeal meal;
    double price=0.0d;
    double total=0.0d;
    double discount=0.0d;
    double extraAmount=0.0d;
    boolean isType=true;
    Customer customerBill;
    public static boolean isPaid;
    public static ObservableList <Bill> obBillList= FXCollections.observableArrayList();
    public TextField txtCash;
    public void initialize(){

            if (PaymentController.isOn) {
                obBillList.clear();
                PaymentController.isOn = false;
            }
            mainTable.refresh();
            tItem.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
            tDes.setCellValueFactory(new PropertyValueFactory<>("description"));
            tQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
            tDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
            tNetPrice.setCellValueFactory(new PropertyValueFactory<>("netPrice"));
            tGrossPrice.setCellValueFactory(new PropertyValueFactory<>("grossPrice"));


            setFields();
            addRoom();
            addMeal();
            setNetPrice();
        txtExtra.setOnKeyTyped(event -> {
            setNetPrice();
        });

        txtDiscount.setOnKeyTyped(event -> {
            setNetPrice();
        });



        mainTable.setItems(obBillList);



    }

    public void settleBill(ActionEvent actionEvent) {
        double cash=0.0d;
        try{
            if(isValid(txtCash.getText())==false){
            cash=Double.parseDouble(txtCash.getText());
            }


        if(cash>=price){
            Alert a=new Alert(Alert.AlertType.INFORMATION,"Balance :"+ (cash-price));
            a.show();
                Income income=new Income();
                income.setBillNumber(PaymentController.sendPayment.getBill());
                income.setDate(LocalDate.now());
                income.setTime(LocalTime.now());
                income.setCustomerID(customerBill.getId());
                income.setCustomerName(customerBill.getName());
                income.setPrice(price);
            IncomeFormController.obIncomeList.add(income);
           Clean cls=new Clean();
           cls.setRoomID(PaymentController.sendPayment.getRoom()); //get room ID
           cls.setOutTime(LocalTime.now());
           for(AddRooms r:RoomController.addRooms){ // get Room Name
               if(PaymentController.sendPayment.getRoom().equals(r.getRoomId())){
           cls.setType(r.getRoomName());
               }

           }
            CleanRoomController.obCleanList.add(cls); // add Clean object
            try {
                PaymentController.obPaymentLint.remove(PaymentController.sendPayment);
                for (AddBooking b: BookingController.obBookingList){
                    if(b.getNic().equals(customerBill.getId())){
                        b.setPaid(false);
                    }
                }
            } catch (Exception e){
                System.out.println("remove error 2");
            }



            Stage window = (Stage)mainGridForm.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("Payment.fxml"))));


        }else{
            Alert a=new Alert(Alert.AlertType.WARNING,"Money is not enough");
            a.show();
        }

        }catch (Exception e){

        }

    }

    public void btnBack(ActionEvent actionEvent) throws IOException {
        Stage window =(Stage) mainGridForm.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("Payment.fxml"))));
    }

    public void btnAddItem(ActionEvent actionEvent) {
    }public void setFields(){
        for(Customer c: CustomerFormController.obCustomerList){
            if(c.getId().equals(PaymentController.sendPayment.getNic())){
                cGetID.setPromptText(c.getId());
                cGetName.setPromptText(c.getName());
                cGetEmail.setPromptText(c.getEmail());
                cGetAddress.setPromptText(c.getAddress());
                cGetNumber.setPromptText(c.getNumber());
                cGetBillNumber.setPromptText(PaymentController.sendPayment.getBill());
                customerBill=c;
            }
        }
    }public String checkItemCode(String field){
        for(AddRooms r: RoomController.addRooms){
            if(r.getRoomId().equals(field)){
                room=r;

                return r.getRoomName();
            }
        }
        for(AddMeal m: MealFormController.obMealList){
            if(m.getId().equals(field)){
            meal=m;

            return m.getFoodName();
            }
        }
        return "null";
    }public double checkPrice(String field){
        for(AddRooms r: RoomController.addRooms){
            if(r.getRoomId().equals(field)){
                room=r;

                return r.getPrice();
            }
        }
        for(AddMeal m: MealFormController.obMealList){
            if(m.getId().equals(field)){
                meal=m;

                return m.getPrice();
            }
        }
        return 0.0;
    }public void addRoom(){
        Bill bill=new Bill();
        bill.setBillID(PaymentController.sendPayment.getBill());
        bill.setItemCode(PaymentController.sendPayment.getRoom());
        checkItemCode(bill.getItemCode());
        bill.setDescription(checkItemCode(bill.getItemCode()));
        bill.setQty(1);
        bill.setDiscount(0);
        bill.setNetPrice(checkPrice(bill.getItemCode()));
        bill.setGrossPrice(checkPrice(bill.getItemCode()));
        obBillList.add(bill);
    }public void addMeal(){
        Bill bill=new Bill();
        bill.setBillID(PaymentController.sendPayment.getBill());
        checkMealId(PaymentController.sendPayment);
        bill.setItemCode(meal.getId());
        bill.setDescription(meal.getFoodName());
        bill.setDiscount(0);
        bill.setNetPrice(meal.getPrice());
        bill.setGrossPrice(meal.getPrice());
        bill.setQty(1);
        obBillList.add(bill);


    }public void checkMealId(Payment p){
        for (AddMeal m:MealFormController.obMealList){
            if(m.getFoodName().equals(p.getMeal())){
                meal=m;
            }
        }
    }public void setNetPrice(){
        price=0.0d;
       discount=0.0d;
       total=0.0d;
        for(Bill b:obBillList){
            price +=b.getGrossPrice();
        }
        textNetAmount.setText(""+price);
        textNetAmount.setStyle("-fx-font-size:14;-fx-font-weight: bold");
        textNetAmount.setDisable(true);
        textTotal.setDisable(true);
        textTotal.setStyle("-fx-font-size:16;-fx-font-weight: bold");



        try{

            textTotal.setText(""+price);
            price=0.0d;
            discount=0.0d;
            total=0.0d;
            for(Bill b:obBillList){
                price +=b.getGrossPrice();
            }

            if(isValid(txtDiscount.getText())==false){
            discount=(double) Double.parseDouble(txtDiscount.getText());
            }


            discount +=0.0;
            total =(double) price * (discount/100.0);
            if(isValid(txtExtra.getText())==false){
            extraAmount=Double.parseDouble(txtExtra.getText()+0);

            }
            price =(price+extraAmount)-total;
            textTotal.setText(""+(price));




        }catch (Exception e){
            System.out.println("error input");

        }

    }public boolean isValid(String field){
        boolean b=field.trim().isEmpty();
       return b;
    }

}
