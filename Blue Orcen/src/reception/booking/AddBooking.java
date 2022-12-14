package reception.booking;

import adminForm.AddRooms;
import adminForm.MealPackage.AddMeal;
import javafx.scene.control.Button;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddBooking {
        private String bookingID;
        private String nic;
        private String name;
        private String roomNumber;
        private String mealPackage;

        private Button btnView;
        private Button btnEdit;
        private Button btnCancel;
        private double price;
        private LocalDate bookingDate;
        private LocalDate leaveDate;
        private LocalTime inTime;
        private LocalTime outTime;
        private AddRooms comboRoom;
        private AddMeal comboMeal;
        private boolean isPaid;


    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getMealPackage() {
        return mealPackage;
    }

    public void setMealPackage(String mealPackage) {
        this.mealPackage = mealPackage;
    }



    public Button getBtnView() {
        return btnView;
    }

    public void setBtnView(Button btnView) {
        this.btnView = btnView;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }

    public Button getBtnCancel() {
        return btnCancel;
    }

    public void setBtnCancel(Button btnCancel) {
        this.btnCancel = btnCancel;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }



    public LocalDate getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(LocalDate leaveDate) {
        this.leaveDate = leaveDate;
    }

    public LocalTime getInTime() {
        return inTime;
    }

    public void setInTime(LocalTime inTime) {
        this.inTime = inTime;
    }

    public LocalTime getOutTime() {
        return outTime;
    }

    public void setOutTime(LocalTime outTime) {
        this.outTime = outTime;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public AddRooms getComboRoom() {
        return comboRoom;
    }

    public void setComboRoom(AddRooms comboRoom) {
        this.comboRoom = comboRoom;
    }

    public AddMeal getComboMeal() {
        return comboMeal;
    }

    public void setComboMeal(AddMeal comboMeal) {
        this.comboMeal = comboMeal;
    }public String clear(){
        return "";
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
