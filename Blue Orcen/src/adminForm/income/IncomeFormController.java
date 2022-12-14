package adminForm.income;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.YearMonth;

public class IncomeFormController {
public static ObservableList <Income> obIncomeList = FXCollections.observableArrayList();

    public AnchorPane incomeForm;


    public TableColumn billNumber;
    public TableColumn tID;
    public TableColumn tName;
    public TableColumn tDate;
    public TableColumn tTime;
    public TableColumn tPrice;
    public TableView mainTable;
    public Label totalMonth;

    public void initialize(){
        mainTable.setItems(obIncomeList);
            billNumber.setCellValueFactory(new PropertyValueFactory<>("billNumber"));
            tID.setCellValueFactory(new PropertyValueFactory<>("customerID"));
            tName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
            tDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            tTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            tPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
          int day=0;
          double priceForMonth=0.0d;
       LocalDate month=LocalDate.now();
            for(Income income:obIncomeList){
                day++;
                  if(month.lengthOfYear()>=day){
                     priceForMonth +=income.getPrice();
                  }
            }
            totalMonth.setText(""+priceForMonth);

    }

}
