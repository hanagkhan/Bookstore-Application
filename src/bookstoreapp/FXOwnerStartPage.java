/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author hanagorikhan
 */
public class FXOwnerStartPage {
    
    Stage window;
    Scene scene;
    VBox vb;
    Button books_btn, customers_btn, logout_btn;
    booksTable B_table = new booksTable();
    
    
 
    public void start(Stage primaryStage,ManageInfo manage) {
        
         System.out.println(manage.booknames.size());
          System.out.println(manage.usernames.size());
        
        window = primaryStage;
        
        vb = new VBox(10);
        vb.setAlignment(Pos.CENTER);
        
        books_btn = new Button();
        books_btn.setText("Books");
        
        customers_btn = new Button();
        customers_btn.setText("Customers");
        
        logout_btn = new Button();
        logout_btn.setText("Logout");
        
        
        books_btn.setOnAction(e->booksButtonClicked(manage)); 
        customers_btn.setOnAction(e->customersButtonClicked(manage)); 
        logout_btn.setOnAction(e->logoutButtonClicked()); 
     
        
        vb.getChildren().addAll(books_btn, customers_btn, logout_btn);
        scene = new Scene(vb, 200, 200);
        
        window.setTitle("Owner Start Page");
        window.setWidth(450);
        window.setHeight(550);
        window.setScene(scene);
        window.show();
    }
    
    public void booksButtonClicked(ManageInfo manage){
        
        B_table.start(window,manage);
    }
     
    public void customersButtonClicked(ManageInfo manage){
        customersTable C_table = new customersTable();
        C_table.start(window,manage);
    }

    public void logoutButtonClicked(){
        FXLogin login = new FXLogin();
        login.start(window);
    }
    
    /**
     * @param args the command line arguments
     */
 
    
}
