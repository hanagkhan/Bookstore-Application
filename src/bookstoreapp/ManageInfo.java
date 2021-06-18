/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.util.ArrayList;

/**
 *
 * @author kapka
 */
public class ManageInfo {
    ArrayList<String>usernames = new ArrayList<String>();
    ArrayList<String>passwords = new ArrayList<String>();
    ArrayList<String> booknames = new ArrayList<String>();
    ArrayList<Double> bprices = new ArrayList<Double>();
    ArrayList<Integer>points = new ArrayList<Integer>();
    
    
    public void setUsername(String username){
        usernames.add(username);
    }
    public ArrayList getUsernames(){
        return usernames;
    }
    public ArrayList getBprices(){
        return bprices;
    }
    
    public void setPassword(String password){
        passwords.add(password);
    }
    
    public ArrayList getPasswords(){
        return passwords;
    }
    
    public ArrayList getPoints(){
        return points;
    }
    
    public ArrayList getBooknames(){
        return booknames;
    }
    
    public void setPrice(double price){
        bprices.add(price);
}
    public void setName(String name){
        booknames.add(name);
}
    public void setPoints(int point){
        points.add(point);
    }
}
