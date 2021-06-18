/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bookstoreapp;

import java.io.FileWriter;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author hanagorikhan
 */
public class customersTable {
    
    Stage window;
    Scene scene;
    TableView<Customers> customers_Table;
    TextField NameInput, PasswordInput;
    Label customers_heading;
    Button add, delete, back;
    HBox part2, part3;
    VBox layout;
    ObservableList<Customers> Customer_data = FXCollections.observableArrayList();
     
    
    
    public void start(Stage primaryStage,ManageInfo manage) {
        
        window=primaryStage;
        window.setTitle("BookStore App");
        for(int i =0;i<manage.usernames.size();i++){
           Customer_data.add(new Customers(manage.usernames.get(i),manage.passwords.get(i),manage.points.get(i)));}
        
    // --------------------------------------------------------------------------------------  label Heading    
        customers_heading = new Label("Customers List");
        customers_heading.setFont(new Font("Arial", 20));
 
    // --------------------------------------------------------------------------------------  Table
        // Book name column
        TableColumn<Customers, String> customerNameCol = new TableColumn("Username");
        customerNameCol.setMinWidth(150); 
        customerNameCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("username"));
        
        
        // Book Price column
        TableColumn<Customers, String> customerPasswordCol = new TableColumn("Password");
        customerPasswordCol.setMinWidth(150); 
        customerPasswordCol.setCellValueFactory(new PropertyValueFactory<Customers, String>("password"));
        
        // Book Price column
        TableColumn<Customers, Integer> customerPointsdCol = new TableColumn("Points");
        customerPointsdCol.setMinWidth(100); 
        customerPointsdCol.setCellValueFactory(new PropertyValueFactory<Customers, Integer>("points"));
    
    // --------------------------------------------------------------------------------------  TextFields
        NameInput = new TextField();
        NameInput.setPromptText("Username");
        NameInput.setMaxWidth(customerNameCol.getPrefWidth());
        
        PasswordInput = new TextField();
        PasswordInput.setMaxWidth(customerPasswordCol.getPrefWidth());
        PasswordInput.setPromptText("Password");
   
    // --------------------------------------------------------------------------------------  Buttons
        add = new Button("Add");
        add.setOnAction(e->addButtonClicked(manage));
       
        delete = new Button();
        delete.setText("Delete");
        delete.setOnAction(e->deleteButtonClicked(manage));

        back = new Button();
        back.setText("Back");
        back.setOnAction(e->backButtonClicked(manage));
        
    // --------------------------------------------------------------------------------------  Layout
    
         
        customers_Table = new TableView<Customers>();
        customers_Table.setItems(Customer_data);
        customers_Table.getColumns().addAll(customerNameCol, customerPasswordCol, customerPointsdCol);
        
        part2 = new HBox();
        part2.setSpacing(3);
        part2.getChildren().addAll(NameInput, PasswordInput, add);
        
        part3 = new HBox();
        part3.setSpacing(3);
        part3.getChildren().addAll(delete, back);
        
        layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(customers_heading, customers_Table, part2, part3);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(layout);
        
        scene = new Scene(root, 300, 250);
        window.setWidth(450);
        window.setHeight(550);
        window.setScene(scene);
        window.show();
    }
    
    public void addButtonClicked(ManageInfo manage){
        
        String c_name = NameInput.getText();
        String c_password = PasswordInput.getText();
        
        int c_points = 0;
        try
        {
            if ( c_name instanceof String && c_password instanceof String ){
                Customers C = new Customers(c_name, c_password,c_points);
                manage.usernames.add(c_name);
                manage.passwords.add(c_password);
                manage.points.add(c_points);
                customers_Table.getItems().add(C);
                FileWriter f = new FileWriter("Customer.txt",false);
        for(int i=0;i<manage.getUsernames().size();i++){
            f.write(""+manage.getUsernames().get(i) + " " + manage.getPasswords().get(i) + " " + manage.getPoints().get(i) + "\n");
        }
        f.close();
             }
            
        }
        catch(Exception e)
        {
            System.out.print("Fill out all the Customer details to add customer");
        }
        
        
       

    
        
        NameInput.clear();
        PasswordInput.clear();
    }
    
    public void deleteButtonClicked(ManageInfo manage){
        ObservableList<Customers> CustomerSelected, allCustomers;
        allCustomers = customers_Table.getItems();
        CustomerSelected = customers_Table.getSelectionModel().getSelectedItems();
        for(int j=0;j<CustomerSelected.size();j++){
        for(int i=0;i<manage.getUsernames().size();i++){
            if(manage.getUsernames().get(i).equals(CustomerSelected.get(j).getUsername()) && manage.getPasswords().get(i).equals(CustomerSelected.get(j).getPassword())){
                manage.usernames.remove(i);
                manage.passwords.remove(i);
                manage.points.remove(i);
            }
        }
        }
        try{
        FileWriter f = new FileWriter("customer.txt",false);
        for(int i=0;i<manage.getUsernames().size();i++){
            f.write(""+manage.getUsernames().get(i) + " " + manage.getPasswords().get(i) +" " + manage.getPoints().get(i) + "\n");
        }
        f.close();
             }
        
         catch(Exception e)
        {
            System.out.print("Error");
        }
        CustomerSelected.forEach(allCustomers::remove);
        
        
    }
    
    public void backButtonClicked(ManageInfo manage){
        FXOwnerStartPage O_startpage = new FXOwnerStartPage();
        O_startpage.start(window,manage);
    }
    
    
    
    
    /**
     * @param args the command line arguments
     */
    
    
}

