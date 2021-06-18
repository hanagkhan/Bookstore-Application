/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

/**
 *
 * @author kapka
 */
public abstract class Points {
    private String status;
    
public Points(String status){
    this.status=status;
}

public static void redeem(CustomerStart c){
    double cost = c.totalCost(c.getCart());
    int discost = c.getPoints()/100;
    if(cost>discost){
        c.setPoints(0);
        c.setTotalCost(cost-discost);
    }
    if(cost<=discost){
        c.setPoints(c.getPoints()-(int)cost*100);
        c.setTotalCost(0);
    }
}
        
    
}
