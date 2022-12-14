package reception.cleanRoom;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import reception.booking.AddBooking;
import reception.booking.BookingController;

public class CleanRoomController {


    public GridPane mainForm;
    public TableView mainTable;
    public TableColumn roomId;
    public TableColumn type;
    public TableColumn time;
    public TableColumn clean;
    public JFXButton btnCancel;
    public  static ObservableList <Clean>obCleanList= FXCollections.observableArrayList();
    public void initialize(){
        roomId.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        time.setCellValueFactory(new PropertyValueFactory<>("outTime"));
        clean.setCellValueFactory(new PropertyValueFactory<>("cleanBtn"));
        mainTable.setItems(obCleanList);
        for (Clean c:obCleanList){
            Button btnClean=new Button(" Clean ");
            btnClean.setCursor(Cursor.HAND);
            btnClean.setStyle("-fx-background-color: BlueViolet");
            c.setCleanBtn(btnClean);
            c.getCleanBtn().setOnAction(event -> {
                try {

                  for(AddBooking b: BookingController.obBookingList){
                      if(b.getRoomNumber().equals(c.getRoomID())){///
                          BookingController.obBookingList.remove(b);
                      }
                  }
                } catch (Exception e){
                    System.out.println("remove error 2");
                }
                obCleanList.remove(c);
            });
        }
    }

    public void btnCancel(ActionEvent actionEvent) {
        mainForm.getScene().getWindow().hide();
    }
}
