/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;
/**
 *
 * @author hanagorikhan
 */
public class Customers {
    
    private String username;
    private String password;
    private int points;
    private String status;
    
    
    
    public Customers(String username, String password,int points){
        this.username = username;
        this.password = password;
        this.points = points;
        status = "silver";
    }
    // ------------------------------------- getters 
    public String getUsername(){
        return username;
    }
    
    public String getPassword(){
        return password;
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
   
    public int getPoints(){
        return points;
    }
    
    public String getStatus(){
        return status;
    }
    // ------------------------------------- setters 
    public void setPoints(double TC){ // TC is total cost 
        points += (int)TC*10;
        setStatus();
    }
    
    public void setStatus(){
        if (getPoints()>=1000)
            status = "gold";
        else 
            status = "silver";
    }
}

