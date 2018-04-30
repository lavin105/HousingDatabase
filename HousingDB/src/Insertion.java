import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class Insertion {
   private Scanner scanString=new Scanner(System.in);
   private Scanner scanNum=new Scanner(System.in);
   private Connect c=new Connect();
   private Connection con=c.getConnection();


    public void insertForSale() throws SQLException{
        try {
            con.setAutoCommit(false);
            System.out.println("Please fill out the following fields in order to list your house for sale.");
            System.out.println("");
            System.out.println("Please enter the address of the home you wish to list.");
            String address=scanString.nextLine();
            System.out.println("Please enter the city where your house is located in.");
            String city=scanString.nextLine();
            System.out.println("Please enter your 5 digit zipcode of your home.");
            while (!scanNum.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the zipcode of your home.");
                scanNum.next();
            }
            int zip=scanNum.nextInt();
            System.out.println("Please enter the size of your home in square feet.");
            while (!scanNum.hasNextDouble()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the size of your home.");
                scanNum.next();
            }
            double size=scanNum.nextDouble();
            System.out.println("Please enter the number of bedrooms in your home.");
            while (!scanNum.hasNextDouble()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the number of bedrooms of your home.");
                scanNum.next();
            }
            double bedroom=scanNum.nextDouble();
            System.out.println("Please enter the number of bathrooms in your home.");
            while (!scanNum.hasNextDouble()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the number of bathrooms of your home.");
                scanNum.next();
            }
            double bathroom=scanNum.nextDouble();
            System.out.println("Finally, please enter the price you wish to list your home at.");
            while (!scanNum.hasNextDouble()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the  price of your home.");
                scanNum.next();
            }
            double price=scanNum.nextDouble();
            PreparedStatement addForSale=con.prepareStatement("INSERT INTO forsale(UserID, Address,City, ZipCode, Size, Bedrooms, Bathrooms, Price) VALUES (?,?,?,?,?,?,?,?)");
            addForSale.clearParameters();
            addForSale.setInt(1,LoginOrRegister.primary_keys);
            addForSale.setString(2, address);
            addForSale.setString(3,city);
            addForSale.setInt(4, zip);
            addForSale.setDouble(5, size);
            addForSale.setDouble(6, bedroom);
            addForSale.setDouble(7, bathroom);
            addForSale.setDouble(8,price);
            addForSale.executeUpdate();
            System.out.println("Your house has been successfully added to the for sale listings!");
            PreparedStatement p2 = con.prepareStatement("INSERT INTO logs VALUES(?,?,?)");
            p2.setString(1,"INSERT INTO forsale(UserID, Address,City, ZipCode, Size, Bedrooms, Bathrooms, Price) VALUES ("+LoginOrRegister.primary_keys+","+ address+","+city+","+zip+","+size+","+bedroom+","+bathroom+","+price+")");
            p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
            con.commit();

        }catch (SQLException e){
            con.rollback();

        }



    }
    public void insertForRent() throws SQLException {
        try {
            con.setAutoCommit(false);
            System.out.println("Please fill out the following fields in order to list your house for sale.");
            System.out.println("");
            System.out.println("Please enter the address of the home you wish to list.");
            String address=scanString.nextLine();
            System.out.println("Please enter the city where your house is located in.");
            String city=scanString.nextLine();
            System.out.println("Please enter your 5 digit zipcode of your home.");
            while (!scanNum.hasNextInt()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the zipcode of your home.");
                scanNum.next();
            }
            int zip=scanNum.nextInt();
            System.out.println("Please enter the size of your home in square feet.");
            while (!scanNum.hasNextDouble()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the size of your home.");
                scanNum.next();
            }
            double size=scanNum.nextDouble();
            System.out.println("Please enter the number of bedrooms in your home.");
            while (!scanNum.hasNextDouble()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the number of bedrooms of your home.");
                scanNum.next();
            }
            double bedroom=scanNum.nextDouble();
            System.out.println("Please enter the number of bathrooms in your home.");
            while (!scanNum.hasNextDouble()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the number of bathrooms of your home.");
                scanNum.next();
            }
            double bathroom=scanNum.nextDouble();
            System.out.println("Finally, please enter the price you wish to list your home at.");
            while (!scanNum.hasNextDouble()) {
                System.out.println("That's not a number!");
                System.out.println("Enter the  price of your home.");
                scanNum.next();
            }
            double price=scanNum.nextDouble();
            PreparedStatement addForSale=con.prepareStatement("INSERT INTO forrent(UserID, Address,City, ZipCode, Size, Bedrooms, Bathrooms, Price) VALUES (?,?,?,?,?,?,?,?)");
            addForSale.clearParameters();
            addForSale.setInt(1,LoginOrRegister.primary_keys);
            addForSale.setString(2, address);
            addForSale.setString(3,city);
            addForSale.setInt(4, zip);
            addForSale.setDouble(5, size);
            addForSale.setDouble(6, bedroom);
            addForSale.setDouble(7, bathroom);
            addForSale.setDouble(8,price);
            addForSale.executeUpdate();
            System.out.println("Your house has been successfully added to the for sale listings!");
            PreparedStatement p2 = con.prepareStatement("INSERT INTO logs VALUES(?,?,?)");
            p2.setString(1,"INSERT INTO forrent(UserID, Address,City, ZipCode, Size, Bedrooms, Bathrooms, Price) VALUES ("+LoginOrRegister.primary_keys+","+ address+","+city+","+zip+","+size+","+bedroom+","+bathroom+","+price+")");
            p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
            p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
            con.commit();


        }catch (SQLException e){
            con.rollback();

        }



    }






}
