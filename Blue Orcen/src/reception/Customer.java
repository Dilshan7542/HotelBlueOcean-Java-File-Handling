package reception;

import javafx.scene.control.Button;

import javax.swing.plaf.basic.BasicOptionPaneUI;

public class Customer {
    private String id;
    private String name;
    private String email;
    private String address;
    private String number;
    private Button btnBooking;
    private Button btnEdit;
    private Button btnDelete;
    private boolean isBooked;

public Customer(){}
    public Customer(String id, String name, String email, String address, String number) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.number = number;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Button getBtnBooking() {
        return btnBooking;
    }

    public void setBtnBooking(Button btnBooking) {
        this.btnBooking = btnBooking;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }
}
