package adminForm.MealPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.util.StringTokenizer;

public class MealFormController {
    public AnchorPane mealForm;
    public TextField tGetID;
    public TextField tGetName;
    public TextField tGetPrice;
    public TableColumn fId;
    public TableColumn fName;
    public TableColumn fPrice;
    public TableColumn fEdit;
    public TableColumn fDelete;
 public static ObservableList<AddMeal> obMealList= FXCollections.observableArrayList();
    public TableView tableMain;
    static boolean isOn=true;
    static boolean isOnEdit;
    public CheckBox checkBox;


    AddMeal updateIndex;
   static int incrementData=9;


    public void initialize(){
        if(obMealList.size()>0){
            obMealList.clear();
        }
        tGetID.setPromptText("food id");
        tGetName.setPromptText("food name");
        tGetPrice.setPromptText("price");

        fId.setCellValueFactory(new PropertyValueFactory<>("id"));
        fName.setCellValueFactory(new PropertyValueFactory<>("foodName"));
        fPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        fEdit.setCellValueFactory(new PropertyValueFactory<>("btnEdit"));
        fDelete.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));

        addData();

        for(AddMeal r:obMealList){
            Button btnEdit=new Button("Edit");
            btnEdit.setStyle("-fx-border-color: green");
            Button btnDelete=new Button("Delete");
            btnDelete.setStyle("-fx-border-color: red");
            r.setBtnEdit(btnEdit);
            r.setBtnDelete(btnDelete);
            r.getBtnDelete().setOnAction(event -> { // when Delete button clicked
                obMealList.remove(r);
                        insertData();
            });
            r.getBtnEdit().setOnAction(event -> {
                r.getBtnEdit().setStyle("-fx-background-color: #52E66B;-fx-border-color: blue");
                for(AddMeal isOff:obMealList){
                    isOff.getBtnDelete().setDisable(true);
                    isOff.getBtnEdit().setDisable(true);
                }
                tGetID.setText(r.getId());
                tGetID.setDisable(true);
                tGetID.setStyle("-fx-background-color: #ED8C8C;-fx-border-color:#0000FF;-fx-border-width: 3;-fx-border-radius: 3.5;-fx-font-weight: bold");
                tGetName.setPromptText(r.getFoodName());
                tGetName.setStyle("-fx-background-color: #C7FACB;-fx-border-color:#0000FF;-fx-border-width: 3;-fx-border-radius: 3.5");
                tGetPrice.setPromptText(""+r.getPrice());
                tGetPrice.setStyle("-fx-background-color: #C7FACB;-fx-border-color:#0000FF;-fx-border-width: 3;-fx-border-radius: 3.5");
                isOnEdit=true;
                checkBox.setDisable(true);
                checkBox.setSelected(false);
                setEdit(r);
            });

        }
        tableMain.setItems(obMealList);
        clearTextField();
        if(checkBox.isSelected())tGetID.setDisable(true);
    }

    private void addData() {
        try {
           BufferedReader bf=new BufferedReader(new FileReader("src\\dbText\\meal.txt"));
           int code= bf.read();
           String fields="";
           while(code !=-1){
               fields +=(char) code;
               code=bf.read();
           }

            StringTokenizer st=new StringTokenizer(fields);
               String token=st.nextToken("/");
           while(st.hasMoreTokens()){
            final String[] split = token.split(",");
            double price=Double.parseDouble(split[2]);
            obMealList.add(new AddMeal(split[0].trim(),split[1],price));
               token=st.nextToken("/");
           }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveFood(ActionEvent actionEvent) {
        if(isOnEdit){
            update(updateIndex);
            clearTextField();
            tableMain.refresh();
            isOnEdit=false;
            checkBox.setDisable(false);
            updateIndex.getBtnEdit().setStyle("-fx-border-color: green");

        }else{
        String id=tGetID.getText();
        String name=tGetName.getText();
        double price=-1.0d;
        try {
        price=Double.parseDouble(tGetPrice.getText());
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.WARNING,"Please Enter Number Of Price");
            a.show();
        }
        if(checkBox.isSelected()){
            incrementData=obMealList.size();

            id=String.format("F%03d",++incrementData);


            if (isValid(id, name, price) && isCheck(id)) {
                obMealList.add(new AddMeal(id, name, price));
                    insertData();

            }
            initialize();
        }else {
            if (isValid(id, name, price) && isCheck(id)) {
                obMealList.add(new AddMeal(id, name, price));
                    insertData();

                initialize();
            }
        }

        }



    }

    private void insertData() {
        try {
            FileWriter fileWriter=new FileWriter("src\\dbText\\meal.txt");
        for (AddMeal ob : obMealList) {
            fileWriter.write(ob.getId()+",");
            fileWriter.write(ob.getFoodName()+",");
            fileWriter.write(ob.getPrice()+"/\n");
        }
        fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isValid(String id, String name, double price){
        boolean bID=id.trim().isEmpty();
        boolean bName=name.trim().isEmpty();
        if(bID==false && bName==false && price>=0){
            return true;
        }
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Please Fill the Text");
        a.show();
        return false;
    }public boolean isCheck(String id){
        for(AddMeal a:obMealList){
            if(a.getId().equals(id)){
        Alert alert=new Alert(Alert.AlertType.ERROR,"Food Id is Already Exist....");
            alert.show();
             return false;
            }
        }
        return true;
    }public void update(AddMeal r){
        String name=tGetName.getText();
        double price=-1.00d;

        try {
         price=Double.parseDouble(tGetPrice.getText());
        }catch (Exception e){
            Alert a=new Alert(Alert.AlertType.WARNING,"Please Enter Number Of Price");
            a.show();
        }
        if(isValid("id",name,price)){
            r.setFoodName(name);
            r.setPrice(price);
            insertData();

        }

    }public void clearTextField(){
        tGetID.clear();
        tGetID.setDisable(false);
        tGetID.setStyle(null);
        tGetID.setPromptText("food id");
        tGetName.clear();
        tGetName.setStyle(null);
        tGetName.setPromptText("food name");
        tGetPrice.clear();
        tGetPrice.setStyle(null);
        tGetPrice.setPromptText("price");
        for (AddMeal btn:obMealList){
            btn.getBtnDelete().setDisable(false);
            btn.getBtnEdit().setDisable(false);
        }
    }public AddMeal setEdit(AddMeal updateIndex){
        return this.updateIndex=updateIndex;
    }

    public void idHide(ActionEvent actionEvent) {
        tGetID.setDisable(true);
        if(checkBox.isSelected()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "ON Auto increment Food Id");
            alert.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "OFF Auto increment You can Do it Manually");
            alert.show();
            tGetID.setDisable(false);
        }
    }
}
