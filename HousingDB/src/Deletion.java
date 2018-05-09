import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
//class for deleting records from the database
public class Deletion {
    private Scanner scanString=new Scanner(System.in);
    private Scanner scanInt=new Scanner(System.in);
    Selection select=new Selection();
    Connect c =new Connect();
    Connection con=c.getConnection();
    //format needed for displaying the record in an organized way
    String format = "\u2503%1$-18s\u2503%2$-18s\u2503%3$-30s\u2503%4$-18s\u2503%5$-18s\u2503%6$-18s\u2503%7$-18s\u2503%8$-18s\u2503%9$-18s\u2503\n";

    //deletes houses for sale
    //users can only delete houses they own this is tracked with the use of their userID
    public void deleteForSale() throws SQLException {
        try {
            System.out.println("In order to remove a house for sale please enter the following information.");
            //query to display the houses that belong to the user
            PreparedStatement px = con.prepareStatement("SELECT * FROM ForSale WHERE UserID=?");

            px.setInt(1,LoginOrRegister.primary_keys);
            ResultSet rx = px.executeQuery();
            ResultSetMetaData rm = rx.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);


            if(!rx.isBeforeFirst()){
                System.out.println("No house for sale with ID "+ LoginOrRegister.primary_keys);


            }else{
                System.out.println("Displaying all houses for sale with your UserID, UserID: "+LoginOrRegister.primary_keys);
                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");

                while (rx.next()) {
                    System.out.format(format, rx.getInt(1),rx.getInt(2), rx.getString(3), rx.getString(4), rx.getInt(5), rx.getDouble(6), rx.getDouble(7), rx.getDouble(8), "$"+rx.getDouble(9));
                    System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");

                }
            }
                //start the deletion transaction
                con.setAutoCommit(false);
                System.out.println("Please enter the houses for sale ID or enter 0 to return to the main menu");
                while (!scanInt.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID of the house you wish to delete.");
                    scanInt.next();
                }
                int saleID = scanInt.nextInt();
                if(saleID==0){

                }else{
                    //query to insert the house to be deleted in a delete house for sale table
                    PreparedStatement p=con.prepareStatement("INSERT INTO DeletedHouseForSale SELECT * FROM forsale WHERE ForSaleID=? AND UserID=?");
                    p.setInt(1,saleID);
                    p.setInt(2, LoginOrRegister.primary_keys);
                    p.executeUpdate();
                    con.commit();

                    PreparedStatement pz = con.prepareStatement("INSERT INTO logs VALUES(?,?,?)");
                    pz.setString(1,"INSERT INTO DeletedHouseForSale SELECT * FROM forsale WHERE ForSaleID="+saleID+" AND UserID="+LoginOrRegister.primary_keys);
                    pz.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    pz.setInt(3,LoginOrRegister.primary_keys);pz.executeUpdate();
                    con.commit();
                    //query that actually deletes the record from the db
                    PreparedStatement ps = con.prepareStatement("DELETE FROM forsale WHERE ForSaleID=? AND UserID=?");
                    ps.setInt(1, saleID);
                    ps.setInt(2, LoginOrRegister.primary_keys);
                    //timer commented out used for testing purposes

                    //long startTime = System.currentTimeMillis();
                    ps.executeUpdate();
                    //long endTime = System.currentTimeMillis();
                    //System.out.println("That took " + (endTime - startTime) + " milliseconds");
                    con.commit();

                    System.out.println("Record officially deleted from our records.");
                    //log the queries in the log tables
                    //uses a callable statement
                    CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                    p2.setString(1,"DELETE FROM forsale WHERE ForSaleID= "+saleID+" AND UserID= "+ LoginOrRegister.primary_keys);
                    p2.setString(2,new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                    p2.setInt(3,LoginOrRegister.primary_keys);p2.executeUpdate();
                    con.commit();
                }





        }catch (SQLException e){
            con.rollback();
        }
    }
    //method for deleting houses for rent
    public void deleteForRent() throws SQLException {
        try {
            System.out.println("In order to remove a house for sale please enter the following information.");
            //display all the homes for rent that belong to the user logged in
            PreparedStatement px = con.prepareStatement("SELECT * FROM ForRent WHERE UserID=?");

            px.setInt(1,LoginOrRegister.primary_keys);
            ResultSet rx = px.executeQuery();
            ResultSetMetaData rm = rx.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);


            if(!rx.isBeforeFirst()){
                System.out.println("No house for sale with ID "+ LoginOrRegister.primary_keys);


            }else{
                System.out.println("Displaying all houses for sale with your UserID, UserID: "+LoginOrRegister.primary_keys);
                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");

                while (rx.next()) {
                    System.out.format(format, rx.getInt(1),rx.getInt(2), rx.getString(3), rx.getString(4), rx.getInt(5), rx.getDouble(6), rx.getDouble(7), rx.getDouble(8), "$"+rx.getDouble(9));
                    System.out.format(format, "------------------", "------------------", "------------------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");

                }
            }
                con.setAutoCommit(false);
                System.out.println("Please enter the houses for rent ID or enter 0 to return to the main menu");
                while (!scanInt.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID of the house you wish to delete.");
                    scanInt.next();
                }
                int saleID = scanInt.nextInt();
            if(saleID==0){

            }else {
                //insert the house to be deleted into the deleted for rent table
                PreparedStatement p = con.prepareStatement("INSERT INTO DeletedHouseForRent SELECT * FROM forrent WHERE ForRentID=? AND UserID=?");
                p.setInt(1, saleID);
                p.setInt(2, LoginOrRegister.primary_keys);
                p.executeUpdate();
                con.commit();
                PreparedStatement pz = con.prepareStatement("INSERT INTO logs VALUES(?,?,?)");
                pz.setString(1, "INSERT INTO DeletedHouseForRent SELECT * FROM forrent WHERE ForSaleID=" + saleID + " AND UserID=" + LoginOrRegister.primary_keys);
                pz.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                pz.setInt(3, LoginOrRegister.primary_keys);
                pz.executeUpdate();
                con.commit();
                //query to actually delete the house for rent from the database
                PreparedStatement ps = con.prepareStatement("DELETE FROM forrent WHERE ForRentID=? AND UserID=?");
                ps.setInt(1, saleID);
                ps.setInt(2, LoginOrRegister.primary_keys);
                ps.executeUpdate();
                con.commit();

                System.out.println("Record officially deleted from our records.");
                //log the queries in the log table using a stored procedure
                CallableStatement p2 = con.prepareCall("CALL addLog(?,?,?)");
                p2.setString(1, "DELETE FROM forrent WHERE ForSaleID= " + saleID + " AND UserID= " + LoginOrRegister.primary_keys);
                p2.setString(2, new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime()));
                p2.setInt(3, LoginOrRegister.primary_keys);
                p2.executeUpdate();
                con.commit();
            }



        }catch (SQLException e){
            con.rollback();
        }
    }


}
