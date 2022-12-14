package adminForm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.*;

public class RoomController {


    public TableView <AddRooms> mainTable;
    public Button saveRoom;
    int nextIndex;
    public TextField sRoomID;
    public TextField sRoomName;
    public TextField sRoomBeds;
    public TextField sRoomBath;
    public TextField searchRoom;
    public TextField sRoomPrice;
    public TableColumn cRoomPrice;
    public TableColumn cRoomId;
    public TableColumn cRoomName;
    public TableColumn cRoomBeds;
    public TableColumn cRoomBath;
    public TableColumn cRoomEdit;
    public TableColumn cRoomDelete;
    boolean editCheck=false;
   public static ObservableList <AddRooms> addRooms= FXCollections.observableArrayList();
  static int count;
  static int index;


public void initialize(){
        if(addRooms.size()>0){
            addRooms.clear();
        }
    cRoomId.setCellValueFactory(new PropertyValueFactory<>("roomId"));
    cRoomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
    cRoomBeds.setCellValueFactory(new PropertyValueFactory<>("roomBed"));
    cRoomBath.setCellValueFactory(new PropertyValueFactory<>("roomBath"));
    cRoomDelete.setCellValueFactory(new PropertyValueFactory<>("btn"));
    cRoomEdit.setCellValueFactory(new PropertyValueFactory<>("editBtn"));
    cRoomPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

            addData();

           for(AddRooms r:addRooms){
               Button btn=new Button("Delete");
               btn.setStyle("-fx-border-color: red");
               Button btn2=new Button("Edit");
               btn2.setStyle("-fx-border-color: green");
               r.setEditBtn(btn2);
               r.setBtn(btn);

               btn.setOnAction(event -> {
                   addRooms.remove(r);
                   insetText();
               });
               btn2.setOnAction(event -> {
                  int index= addRooms.indexOf(r);
                  r.getEditBtn().setStyle("-fx-background-color: #52E66B;-fx-border-color: blue");
                  sRoomID.setText(r.getRoomId());
                  sRoomID.setStyle("-fx-background-color: #ED8C8C;-fx-border-color:#0000FF;-fx-border-width: 3;-fx-border-radius: 3.5;-fx-font-weight: bold");
                   sRoomID.setDisable(true);
                  sRoomName.setStyle("-fx-background-color: #C7FACB;-fx-border-color:#0000FF;-fx-border-width: 3;-fx-border-radius: 3.5");
                  sRoomName.setPromptText(r.getRoomName());
                  sRoomBeds.setStyle("-fx-background-color: #C7FACB;-fx-border-color:#0000FF;-fx-border-width: 3;-fx-border-radius: 3.5");
                  sRoomBeds.setPromptText(""+r.getRoomBed());
                  sRoomBath.setStyle("-fx-background-color: #C7FACB;-fx-border-color:#0000FF;-fx-border-width: 3;-fx-border-radius: 3.5");
                  sRoomBath.setPromptText(""+r.getRoomBath());
                  sRoomPrice.setStyle("-fx-background-color: #C7FACB;-fx-border-color:#0000FF;-fx-border-width: 3;-fx-border-radius: 3.5");
                  sRoomPrice.setPromptText(""+r.getPrice());
                    for (AddRooms d:addRooms){
                        d.getBtn().setDisable(true);
                        d.getEditBtn().setDisable(true);
                    }
                    r.getEditBtn().setDisable(false);
                    editCheck=true;
                    this.index=index;
               });


           }


    mainTable.setItems(addRooms);

}

    private void addData() {
        try {
            BufferedReader bf=new BufferedReader(new FileReader("src\\dbText\\Room.txt"));
                String line=bf.readLine();
                while(line !=null){
                    System.out.println(line);
                    final String[] fields = line.split(",");
                    int bed=Integer.parseInt(fields[2]);
                    int bath=Integer.parseInt(fields[3]);
                    double price=Double.parseDouble(fields[4]);
                    addRooms.add(new AddRooms(fields[0],fields[1],bed,bath,price));
                    line=bf.readLine();


                }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void saveRoom(ActionEvent actionEvent) {

    if(editCheck){
        update(index);
        mainTable.refresh();

        editCheck=false;
        defaultTextField();
        sRoomID.setDisable(false);



    }else{

        String id=sRoomID.getText();
        String name=sRoomName.getText();
        int beds=0;
        int bath=0;
        double price=-1.0d;
        try{

            beds=Integer.parseInt(sRoomBeds.getText());
            bath=Integer.parseInt(sRoomBath.getText());
            price=Double.parseDouble(sRoomPrice.getText());
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.WARNING,"Please Enter Valid Numbers");
            a.show();
        }
        boolean b=sRoomName.getText().trim().isEmpty();
        boolean b2=sRoomID.getText().trim().isEmpty();
        if(isCheck(id) && isValidIt(beds,bath,b,b2,price)) {
            addRooms.add(new AddRooms(id, name, beds, bath,price));
         insetText();

           initialize();
            defaultTextField();
        }else{
            if(isCheck(id)==false) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Is already Exist");
                alert.show();
            }
            System.out.println("Is Exist...");
        }
    }


    }

    private void insetText() {
        try {
            FileWriter fileWriter=new FileWriter("src\\dbText\\Room.txt");
            for (AddRooms ob : addRooms) {
                fileWriter.write(ob.getRoomId()+",");
                fileWriter.write(ob.getRoomName()+",");
                fileWriter.write(ob.getRoomBed()+",");
                fileWriter.write(ob.getRoomBath()+",");
                fileWriter.write(ob.getPrice()+"\n");
            }
            fileWriter.flush();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchRoom(ActionEvent actionEvent) {
      //  mainTable.getSortOrder().setAll(cRoomId);
   //  mainTable.

    }public boolean isCheck(String id){
    for(AddRooms r:addRooms){
       if(r.getRoomId().equals(id)){
           return false;
       };
    }
    return true;
    }public boolean isValidIt(int beds,int bath,boolean b ,boolean b2,double price){
            if(beds>=1 && bath>=1 && b==false && b2==false && price>=0){
                return true;
            }
            Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Please Fill the Box..");
            a.show();
    return false;
    }public void defaultTextField(){


        sRoomID.clear();
        sRoomID.setStyle(null);
        sRoomID.setPromptText("Room id");
        sRoomName.clear();
        sRoomName.setStyle(null);
        sRoomName.setPromptText("Room Name");
        sRoomBeds.clear();
        sRoomBeds.setStyle(null);
        sRoomBeds.setPromptText("Room Beds");
        sRoomBath.clear();
        sRoomBath.setStyle(null);
        sRoomBath.setPromptText("Room Bath");
        sRoomPrice.clear();
        sRoomPrice.setStyle(null);
        sRoomPrice.setPromptText("Room Price");
        for (AddRooms d:addRooms){
            d.getEditBtn().setStyle(null);
            d.getEditBtn().setStyle("-fx-border-color: green");
            d.getBtn().setDisable(false);
            d.getEditBtn().setDisable(false);
        }


    }public void update(int index){

    AddRooms r=addRooms.get(index);

        boolean b=sRoomName.getText().trim().isEmpty();
        int beds=0;
        int bath=0;
        double price=-1.0d;
            try {
            beds=Integer.parseInt(sRoomBeds.getText());
            bath=Integer.parseInt(sRoomBath.getText());
            price=Double.parseDouble(sRoomPrice.getText());
            }catch (Exception e){
                System.out.println("Error");
            }

        if(isValidIt(beds,bath,b,false,price)){
            r.setRoomName(sRoomName.getText());
    r.setRoomBed(beds);
    r.setRoomBath(bath);
    r.setPrice(price);
    r.getEditBtn().setStyle(null);
    r.getEditBtn().setStyle("-fx-border-color: green");
        sRoomID.setDisable(false);

        defaultTextField();
        insetText();
        }


    }



}
