package adminForm.income;

import java.time.LocalDate;
import java.time.LocalTime;

public class Income {
    private LocalDate date;
    private String customerID;
    private String customerName;
    private LocalTime time;
    private String billNumber;
    private double price;
public Income(){}
    public Income(LocalDate date, String customerID, String customerName, LocalTime time, String billNumber, double price) {
        this.date = date;
        this.customerID = customerID;
        this.customerName = customerName;
        this.time = time;
        this.billNumber = billNumber;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
