package bookstoreapp;

import javafx.application.Application;
import static javafx.application.Application.launch;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author hanagorikhan
 */
public class FXLogin extends Application {
    
    Stage window;
    GridPane grid;
    Text title;
    Label userName, pw;
    TextField userTextField;
    PasswordField pwBox;
    Button Login_btn;
    HBox hbBtn;
    Scene scene;
    Owner owner;
    ManageInfo manage = new ManageInfo();
    CustomerStart customer = new CustomerStart();
    FXOwnerStartPage F = new FXOwnerStartPage();
    Books books;
    
    
            
    @Override
    public void start(Stage primaryStage) {
        
        
        try{
            File f = new File("books.txt");
            Scanner scanner = new Scanner(f);
            String line;
            while(scanner.hasNext()){
                line=scanner.next();
                manage.setName(line);
                line =scanner.next();
                manage.setPrice(Double.parseDouble(line));
                
            }
            
            f= new File("customer.txt");
            scanner = new Scanner(f);
            while(scanner.hasNext()){
                line=scanner.next();
                manage.setUsername(line);
                line = scanner.next();
                manage.setPassword(line);
                line=scanner.next();
                manage.setPoints(Integer.parseInt(line));
            }
            
                   
        }
        catch(IOException e){
            System.out.println("Error");
            e.printStackTrace();
        }
        
        
        
        
        
        
        
        
        window=primaryStage;
        window.setTitle("BookStore App");
        
        // ---------------------------------------------------------------------Grid 
            grid = new GridPane();
            grid.setAlignment(Pos.CENTER);
            grid.setHgap(10);
            grid.setVgap(10);

        // ---------------------------------------------------------------------Titile 
            title = new Text("Welcome to the BookStore App");
            title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
            
        // ---------------------------------------------------------------------Testfields/labels/button 
            userName = new Label("Username:");
            userTextField = new TextField();
            pw = new Label("Password:");
            pwBox = new PasswordField();
            Login_btn = new Button("Login");
            
        // ---------------------------------------------------------------------layout 
            hbBtn = new HBox(10);
            hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
            hbBtn.getChildren().add(Login_btn);
            
            grid.add(title, 0, 0, 2, 1);
            grid.add(userName, 0, 1);
            grid.add(userTextField, 1, 1);
            grid.add(pw, 0, 2);
            grid.add(pwBox, 1, 2);
            grid.add(hbBtn, 1, 4);
            
            Login_btn.setOnAction(e -> ownerORcustomer());
            
            scene = new Scene(grid, 300, 280);
            window.setScene(scene);
        window.show();
    }
    
    // depending on the username and password the login will be redirected to the owner-start-screen or customer-start-screen
    public void ownerORcustomer(){
        if(userTextField.getText().equals("admin") && pwBox.getText().equals("admin")){
                    F.start(window,manage);
        }
        
        for(int i=0; i<manage.getUsernames().size();i++){
            if(userTextField.getText().equals(manage.getUsernames().get(i)) && pwBox.getText().equals(manage.getPasswords().get(i))){
                
                customer.setUsername((String)(manage.getUsernames().get(i)));
                customer.setPoints((int)manage.getPoints().get(i));
                customer.setPassword((String)(manage.getPasswords().get(i)));
                
                
                customer.start(window,manage);
                
            }
        }

        }
    
        
        
    
    
    /**
     * @param args the command line arguments
     */ 
    public static void main(String[] args) {
        launch(args);
    }
    
}
