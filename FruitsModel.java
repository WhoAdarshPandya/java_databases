/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_mongo_crud;

/**
 *
 * @author DC
 */
public class FruitsModel {

    private int id;
    private String name;
    private int price;
    private boolean isAvailable;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public boolean getAvailability() {
        return isAvailable;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setAailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}
