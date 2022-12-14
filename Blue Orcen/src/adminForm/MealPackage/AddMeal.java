package adminForm.MealPackage;

import javafx.scene.control.Button;

public class AddMeal {
    public AddMeal(){}
    private String id;
    private String foodName;
    private double price;
    private Button btnDelete;
    private Button btnEdit;

    public AddMeal(String id, String foodName, double price) {
        this.id = id;
        this.foodName = foodName;
        this.price = price;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    public Button getBtnEdit() {
        return btnEdit;
    }

    public void setBtnEdit(Button btnEdit) {
        this.btnEdit = btnEdit;
    }
    public String toString(){
        return "ID :"+getId()+"       Meal :"+ getFoodName() +"         Price :"+getPrice();
    }
}
