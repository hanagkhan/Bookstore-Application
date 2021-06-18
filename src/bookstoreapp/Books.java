/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import javafx.scene.control.CheckBox;
import java.util.ArrayList;

/**
 *
 * @author kapka
 */
public class Books {

    
    private String name;
    private double price;
    static double total=0;
    private CheckBox select;
    
public Books(String name, double price){
    this.name = name;
    this.price=price;
    this.select = new CheckBox();
}

public String getName(){
    return name;
}
   

public double getPrice(){
    return price;
}



public void buyBook(){
    total = total + getPrice();
}

public CheckBox getSelect(){
    return select;
}

public void setSelect(CheckBox select){
    this.select=select;
}

}
