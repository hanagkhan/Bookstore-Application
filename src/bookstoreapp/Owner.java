package bookstoreapp;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author hanagorikhan
 */
public class Owner {
   
    private static Owner owner_instance = null;
    
    private final static String username = "admin";
    private final static String password = "admin";
    public ArrayList<Customers> CustomerList = new ArrayList<Customers>();
    public ArrayList<Books> BookList = new ArrayList<Books>();
    
    File BooksFile = new File("file_books.txt");
    File CustomersFile = new File("file_customers.txt");
    
    private Owner() {
    }
    
    public static Owner getInstance() {
        if (owner_instance == null)
            owner_instance = new Owner();
  
        return owner_instance;
    }
    
    public String getUsername(){
        return username;
    }
    /*
    public void setUsername(String username){
        Customers.usernames.add(username);
    }
    
    public String getPassword(){
        return password;
    }
    */
    // ------------------------------------------------------------------------- adding books/customers
    public void addBook(Books book) throws IOException{
    }
    
     public void addCustomer(String name, String pswrd,int points){
        CustomerList.add(new Customers(name,pswrd,points));
        /*
        try {
            FileWriter book_fw = new FileWriter(CustomersFile);
            book_fw.write("Files in Java might be tricky, but it is fun enough!");
            book_fw.close();
            System.out.println("Successfully wrote to the file.");
            } 
        catch (IOException e) {
            System.out.println("An error occurred while adding customer to file.");
            e.printStackTrace();
            }
        */
    }
    
    // ------------------------------------------------------------------------- deleting books/customers 
    public void deleteBook(int index){
        BookList.remove(index);
    }
    
    public void deleteCustomer(int index){
        CustomerList.remove(index);
    }
   
}

