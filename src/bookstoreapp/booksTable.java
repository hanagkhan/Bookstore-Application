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
public class booksTable{
    
    Stage window;
    Scene scene;
    TableView<Books> Books_Table;
    TextField NameInput, PriceInput;
    Label books_heading;
    Button add, delete, back;
    HBox part2, part3;
    VBox layout;
    Owner owner = Owner.getInstance();
    
    
    public void start(Stage primaryStage,ManageInfo manage) {
        ObservableList<Books> book_data = FXCollections.observableArrayList();
        
        window=primaryStage;
        window.setTitle("BookStore App");
        System.out.println(manage.booknames.size());
         for(int i =0;i<manage.booknames.size();i++){
           
           book_data.add(new Books(manage.booknames.get(i),manage.bprices.get(i)));
           System.out.println(manage.booknames.get(i));
       }
       
        
    // --------------------------------------------------------------------------------------  label Heading    
        books_heading = new Label("Books List");
        books_heading.setFont(new Font("Arial", 20));
 
    // --------------------------------------------------------------------------------------  Table
        // Book name column
        TableColumn<Books, String> bookNameCol = new TableColumn("Book Name");
        bookNameCol.setMinWidth(200); 
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        
        
        // Book Price column
        TableColumn<Books, Double> bookPriceCol = new TableColumn("Book Price(CAD)");
        bookPriceCol.setMinWidth(200); 
        bookPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        
    
    // --------------------------------------------------------------------------------------  TextFields
        NameInput = new TextField();
        NameInput.setPromptText("book name");
        NameInput.setMaxWidth(bookNameCol.getPrefWidth());
        
        PriceInput = new TextField();
        PriceInput.setMaxWidth(bookPriceCol.getPrefWidth());
        PriceInput.setPromptText("book price");
   
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
    
        Books_Table = new TableView<Books>();
        Books_Table.setItems(book_data);
        Books_Table.getColumns().addAll(bookNameCol, bookPriceCol);
        
        part2 = new HBox();
        part2.setSpacing(3);
        part2.getChildren().addAll(NameInput, PriceInput, add);
        
        part3 = new HBox();
        part3.setSpacing(3);
        part3.getChildren().addAll(delete, back);
        
        layout = new VBox();
        layout.setSpacing(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(books_heading, Books_Table, part2, part3);
        
        StackPane root = new StackPane();
        root.getChildren().addAll(layout);
        
        scene = new Scene(root, 300, 250);
        window.setWidth(450);
        window.setHeight(550);
        window.setScene(scene);
        window.show();
    }
    
    public void addButtonClicked(ManageInfo manage){
        Books book ;
        String s_name = NameInput.getText();
        String s_price = PriceInput.getText();
        try
        {
            if ( s_name instanceof String && s_price instanceof String){
                book = new Books(s_name, Double.parseDouble(s_price));
                manage.booknames.add(s_name);
                manage.bprices.add(Double.parseDouble(s_price));
                
                Books_Table.getItems().add(book);
                
                
        FileWriter f = new FileWriter("books.txt",false);
        for(int i=0;i<manage.getBooknames().size();i++){
            f.write(""+manage.getBooknames().get(i) + " " + manage.getBprices().get(i) +"\n");
        }
        f.close();
             }
        }
         catch(Exception e)
        {
            System.out.print("Fill out all the book details to add book");
        }
        
        
        
     
    
        NameInput.clear();
        PriceInput.clear();
    }
    
    public void deleteButtonClicked(ManageInfo manage){
        ObservableList<Books> BookSelected, allBooks;
        allBooks = Books_Table.getItems();
        BookSelected = Books_Table.getSelectionModel().getSelectedItems();
        
        for(int j=0;j<BookSelected.size();j++){
        for(int i=0;i<manage.getBooknames().size();i++){
            if(manage.getBooknames().get(i).equals(BookSelected.get(j).getName())){
                manage.booknames.remove(i);
                manage.bprices.remove(i);
            }
        }
        }
        try{
        FileWriter f = new FileWriter("books.txt",false);
        for(int i=0;i<manage.getBooknames().size();i++){
            f.write(""+manage.getBooknames().get(i) + " " + manage.getBprices().get(i) +"\n");
        }
        f.close();
             }
        
         catch(Exception e)
        {
            System.out.print("Fill out all the book details to add book");
        }
        BookSelected.forEach(allBooks::remove);
        
        
        
    }
    
    public void backButtonClicked(ManageInfo manage){
        FXOwnerStartPage O_startpage = new FXOwnerStartPage();
        O_startpage.start(window,manage);
    }
    
    
    
    /**
     * @param args the command line arguments
     */
 
}
