package reception.payment;


import javafx.scene.control.Button;

import java.time.LocalDate;
import java.time.LocalTime;

public class Payment {
    private double price;
    private String bill;
    private String name;
    private String nic;
    private String room;
    private String meal;


    private int qty;
    private LocalTime time;
    private LocalDate arrived;
    private Button payment;

    public String getBill() {
        return bill;
    }

    public void setBill(String bill) {
        this.bill = bill;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }



    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Button getPayment() {
        return payment;
    }

    public void setPayment(Button payment) {
        this.payment = payment;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getArrived() {
        return arrived;
    }

    public void setArrived(LocalDate arrived) {
        this.arrived = arrived;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
