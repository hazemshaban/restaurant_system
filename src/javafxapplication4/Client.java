package javafxapplication4;

import java.util.ArrayList;
import java.util.List;

public class Client extends Users {

    Tables table = new Tables();
    List<Dish> dishList = new ArrayList<>();
    private double money=0.0;
    boolean save=false;

    
    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public Tables getTable() {
        return table;
    }

    public void setTable(Tables table) {
        this.table = table;
    }

}
