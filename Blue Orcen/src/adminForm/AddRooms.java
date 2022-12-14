package adminForm;

import javafx.scene.control.Button;

public class AddRooms {
    private String roomId;
    private String roomName;
    private int roomBed;
    private int roomBath;
    private double price;
   private Button btn;
   private Button editBtn;
    public AddRooms(){}

    public AddRooms(String roomId, String roomName, int roomBed, int roomBath,double price) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomBed = roomBed;
        this.roomBath = roomBath;
        this.price=price;

    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public int getRoomBed() {
        return roomBed;
    }

    public void setRoomBed(int roomBed) {
        this.roomBed = roomBed;
    }

    public int getRoomBath() {
        return roomBath;
    }

    public void setRoomBath(int roomBath) {
        this.roomBath = roomBath;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    public Button getEditBtn() {
        return editBtn;
    }

    public void setEditBtn(Button editBtn) {
        this.editBtn = editBtn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public String toString(){
        return "ID :"+getRoomId()+"      Type :"+getRoomName() +"     Price :"+getPrice() ;
    }
}
