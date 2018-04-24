import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.*;
import java.util.Scanner;

public class Selection extends Connect {
    Scanner scan=new Scanner(System.in);
    Scanner scanString=new Scanner(System.in);
    String format = "\u2503%1$-18s\u2503%2$-18s\u2503%3$-18s\u2503%4$-18s\u2503%5$-18s\u2503%6$-18s\u2503%7$-18s\u2503%8$-18s\u2503%9$-18s\u2503\n";

    public void selectForSale() {
        StringBuilder s=new StringBuilder();
        System.out.println("Would you like to filter your selection?");
        System.out.println("1 Yes, 2 No");
        int filter=scan.nextInt();
        if(filter==2) {

            try {
                PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale");
                ResultSet r = p.executeQuery();
                ResultSetMetaData rm = r.getMetaData();

                String col1 = rm.getColumnName(1);
                String col2 = rm.getColumnName(2);
                String col3 = rm.getColumnName(3);
                String col4 = rm.getColumnName(4);
                String col5 = rm.getColumnName(5);
                String col6 = rm.getColumnName(6);
                String col7 = rm.getColumnName(7);
                String col8= rm.getColumnName(8);
                String col9=rm.getColumnName(9);
                if(!r.isBeforeFirst()){
                    System.out.println("No record exists");


                }else{
                    System.out.println("");
                    System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                    System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                    System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                    s.append(col1);
                    s.append(',');
                    s.append(col2);
                    s.append(',');
                    s.append(col3);
                    s.append(',');
                    s.append(col4);
                    s.append(',');
                    s.append(col5);
                    s.append(',');
                    s.append(col6);
                    s.append(',');
                    s.append(col7);
                    s.append(',');
                    s.append(col8);
                    s.append(',');
                    s.append(col9);
                    s.append('\n');

                    while (r.next()) {
                        System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                        System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                        s.append(r.getInt(1));
                        s.append(',');
                        s.append(r.getInt(2));
                        s.append(',');
                        s.append(r.getString(3));
                        s.append(',');
                        s.append(r.getString(4));
                        s.append(',');
                        s.append(r.getInt(5));
                        s.append(',');
                        s.append(r.getDouble(6));
                        s.append(',');
                        s.append(r.getDouble(7));
                        s.append(',');
                        s.append(r.getDouble(8));
                        s.append(',');
                        s.append(r.getDouble(9));
                        s.append('\n');

                    }
                    System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID to filter by.");
                        scan.next(); // this is important!
                    }
                    int csv=scan.nextInt();
                    if(csv==1){
                        System.out.println("Please enter the name of the file you wish to export this data to.");
                        String _file=scanString.nextLine();
                        try {
                            PrintWriter pw = new PrintWriter(new File(_file));
                            pw.write(s.toString());
                            pw.close();
                        }catch (IOException e){
                            System.out.println("Unable to create file");
                        }

                        System.out.println("Exported");
                    }else{
                        System.out.println("Not exported");
                    }

                }




            } catch (SQLException r) {
                    System.out.println(r.getMessage());

            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }


        }else if(filter==1){

            System.out.println("What would you like to filter faculty by?");
            System.out.println("1-ID, 2-Address, 3-City, 4-Zip Code, 5-Size, 6-# of Bedrooms, 7-# of Bathrooms, 8-Price");
            int filter2=scan.nextInt();
            if(filter2==1){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE ForSaleID=?");
                    System.out.println("Enter the ID to filter by.");

                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID to filter by.");
                        scan.next(); // this is important!
                    }
                    int id=scan.nextInt();
                    p.setInt(1,id);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()){
                        System.out.println("No house for sale with ID "+ id);


                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }



                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==2){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Address LIKE ?");
                    System.out.println("Enter the Address to filter by.");
                    String add=scanString.nextLine();
                    p.setString(1,add+'%');
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale with an address of " + add);

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }


                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==3){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE City=?");
                    System.out.println("Enter the City to filter by.");
                    String city=scanString.nextLine();
                    p.setString(1,city);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale with the city "+city );

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }


                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==4){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE ZipCode=?");
                    System.out.println("Enter the ZipCode to filter by.");

                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a popper zipcode!");
                        System.out.println("Enter the Zipcode to filter by.");
                        scan.next(); // this is important!
                    }


                    int zcode=scan.nextInt();
                    p.setInt(1,zcode);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale with the zipcode "+ zcode);

                    }else {
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }


                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }else if(filter2==5){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Size=?");
                    System.out.println("Enter the Size (sqft) to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a popper size!");
                        System.out.println("Enter the size to filter by.");
                        scan.next(); // this is important!
                    }
                    double sz=scan.nextDouble();
                    p.setDouble(1,sz);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale is "+sz +" square feet");


                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }

                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==6){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Bedrooms=?");
                    System.out.println("Enter the number of Bedrooms to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a popper number of bedrooms!");
                        System.out.println("Enter the number of bedrooms to filter by.");
                        scan.next(); // this is important!
                    }
                    double bed=scan.nextDouble();
                    p.setDouble(1,bed);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale has "+ bed + " bedrooms");


                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }


                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }else if(filter2==7){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Bathrooms=?");
                    System.out.println("Enter the number of Bathrooms to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a popper number of bathrooms!");
                        System.out.println("Enter the number of bathrooms to filter by.");
                        scan.next(); // this is important!
                    }
                    double bath=scan.nextDouble();
                    p.setDouble(1,bath);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale has "+ bath + " bathrooms");


                    }else{

                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }


                    }


                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==8){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Price=?");
                    System.out.println("Enter the Price to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a popper price!");
                        System.out.println("Enter the price to filter by.");
                        scan.next(); // this is important!
                    }
                    double price=scan.nextDouble();
                    p.setDouble(1,price);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);


                    if(!r.isBeforeFirst()) {
                        System.out.println("No house for sale has a price of "+ price + " dollars");

                    }else {
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1), r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------", "------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }


                    }


                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else {
                System.out.println("Not a valid option please choose again");
                selectForSale();
            }







        }else {
            System.out.println("Not a valid option please choose again");
            selectForSale();
        }
    }
    public void selectForRent() {
        StringBuilder s=new StringBuilder();
        System.out.println("Would you like to filter your selection?");
        System.out.println("1 Yes, 2 No");
        int filter=scan.nextInt();
        if(filter==2) {

            try {
                PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent");
                ResultSet r = p.executeQuery();
                ResultSetMetaData rm = r.getMetaData();

                String col1 = rm.getColumnName(1);
                String col2 = rm.getColumnName(2);
                String col3 = rm.getColumnName(3);
                String col4 = rm.getColumnName(4);
                String col5 = rm.getColumnName(5);
                String col6 = rm.getColumnName(6);
                String col7 = rm.getColumnName(7);
                String col8= rm.getColumnName(8);
                String col9=rm.getColumnName(9);

                if(!r.isBeforeFirst()){
                    System.out.println("No record exists");

                }else{
                    System.out.println("");
                    System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                    System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                    System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                    s.append(col1);
                    s.append(',');
                    s.append(col2);
                    s.append(',');
                    s.append(col3);
                    s.append(',');
                    s.append(col4);
                    s.append(',');
                    s.append(col5);
                    s.append(',');
                    s.append(col6);
                    s.append(',');
                    s.append(col7);
                    s.append(',');
                    s.append(col8);
                    s.append(',');
                    s.append(col9);
                    s.append('\n');
                    while (r.next()) {
                        System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                        System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                        s.append(r.getInt(1));
                        s.append(',');
                        s.append(r.getInt(2));
                        s.append(',');
                        s.append(r.getString(3));
                        s.append(',');
                        s.append(r.getString(4));
                        s.append(',');
                        s.append(r.getInt(5));
                        s.append(',');
                        s.append(r.getDouble(6));
                        s.append(',');
                        s.append(r.getDouble(7));
                        s.append(',');
                        s.append(r.getDouble(8));
                        s.append(',');
                        s.append(r.getDouble(9));
                        s.append('\n');
                    }
                    System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID to filter by.");
                        scan.next(); // this is important!
                    }
                    int csv=scan.nextInt();
                    if(csv==1){
                        System.out.println("Please enter the name of the file you wish to export this data to.");
                        String _file=scanString.nextLine();
                        try {
                            PrintWriter pw = new PrintWriter(new File(_file));
                            pw.write(s.toString());
                            pw.close();
                        }catch (IOException e){
                            System.out.println("Unable to create file");
                        }

                        System.out.println("Exported");
                    }else{
                        System.out.println("Not exported");
                    }

                }






            } catch (SQLException r) {
                    r.printStackTrace();
            }finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }else if(filter==1){

            System.out.println("What would you like to filter faculty by?");
            System.out.println("1-ID, 2-Address, 3-City, 4-Zip Code, 5-Size, 6-# of Bedrooms, 7-# of Bathrooms, 8-Price");
            int filter2=scan.nextInt();
            if(filter2==1){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE ForRentID=?");
                    System.out.println("Enter the ID to filter by.");
                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a number!");
                        System.out.println("Enter the ID to filter by.");
                        scan.next(); // this is important!
                    }
                    int id=scan.nextInt();
                    p.setInt(1,id);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent with the ID "+id);

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==2){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Address LIKE ?");
                    System.out.println("Enter the Address to filter by.");
                    String add=scanString.nextLine();
                    p.setString(1,add+'%');
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house exists with the address "+ add);

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }


                    }



                } catch (SQLException r) {
                r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==3){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE City=?");
                    System.out.println("Enter the City to filter by.");
                    String city=scanString.nextLine();
                    p.setString(1,city);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent exists with the city "+city);

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==4){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE ZipCode=?");
                    System.out.println("Enter the ZipCode to filter by.");
                    while (!scan.hasNextInt()) {
                        System.out.println("That's not a proper zipcode!");
                        System.out.println("Enter the zipcode to filter by.");
                        scan.next(); // this is important!
                    }
                    int zcode=scan.nextInt();
                    p.setInt(1,zcode);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent has the zipcode "+zcode);

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                        r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }else if(filter2==5){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Size=?");
                    System.out.println("Enter the Size (sqft) to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a proper size!");
                        System.out.println("Enter the size to filter by.");
                        scan.next(); // this is important!
                    }
                    double sz=scan.nextDouble();
                    p.setDouble(1,sz);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent exists that is "+sz+" square feet");

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }



                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==6){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Bedrooms=?");
                    System.out.println("Enter the number of Bedrooms to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a proper number of bedrooms!");
                        System.out.println("Enter the number of bedrooms to filter by.");
                        scan.next(); // this is important!
                    }
                    double bed=scan.nextDouble();
                    p.setDouble(1,bed);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No hose for rent has "+bed+" bedrooms");

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


            }else if(filter2==7){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Bathrooms=?");
                    System.out.println("Enter the number of Bathrooms to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a proper number of bathrooms!");
                        System.out.println("Enter the number of bathrooms to filter by.");
                        scan.next(); // this is important!
                    }
                    double bath=scan.nextDouble();
                    p.setDouble(1,bath);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house for rent has "+bath+" bathrooms");

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else if(filter2==8){
                try {
                    PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Price=?");
                    System.out.println("Enter the Price to filter by.");
                    while (!scan.hasNextDouble()) {
                        System.out.println("That's not a proper price!");
                        System.out.println("Enter the price to filter by.");
                        scan.next(); // this is important!
                    }
                    double price=scan.nextDouble();
                    p.setDouble(1,price);
                    ResultSet r = p.executeQuery();
                    ResultSetMetaData rm = r.getMetaData();

                    String col1 = rm.getColumnName(1);
                    String col2 = rm.getColumnName(2);
                    String col3 = rm.getColumnName(3);
                    String col4 = rm.getColumnName(4);
                    String col5 = rm.getColumnName(5);
                    String col6 = rm.getColumnName(6);
                    String col7 = rm.getColumnName(7);
                    String col8= rm.getColumnName(8);
                    String col9=rm.getColumnName(9);

                    if(!r.isBeforeFirst()){
                        System.out.println("No house with the price "+price+" exists");

                    }else{
                        System.out.println("");
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                        System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                        s.append(col1);
                        s.append(',');
                        s.append(col2);
                        s.append(',');
                        s.append(col3);
                        s.append(',');
                        s.append(col4);
                        s.append(',');
                        s.append(col5);
                        s.append(',');
                        s.append(col6);
                        s.append(',');
                        s.append(col7);
                        s.append(',');
                        s.append(col8);
                        s.append(',');
                        s.append(col9);
                        s.append('\n');
                        while (r.next()) {
                            System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));
                            System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                            s.append(r.getInt(1));
                            s.append(',');
                            s.append(r.getInt(2));
                            s.append(',');
                            s.append(r.getString(3));
                            s.append(',');
                            s.append(r.getString(4));
                            s.append(',');
                            s.append(r.getInt(5));
                            s.append(',');
                            s.append(r.getDouble(6));
                            s.append(',');
                            s.append(r.getDouble(7));
                            s.append(',');
                            s.append(r.getDouble(8));
                            s.append(',');
                            s.append(r.getDouble(9));
                            s.append('\n');
                        }
                        System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                        while (!scan.hasNextInt()) {
                            System.out.println("That's not a number!");
                            System.out.println("Enter the ID to filter by.");
                            scan.next(); // this is important!
                        }
                        int csv=scan.nextInt();
                        if(csv==1){
                            System.out.println("Please enter the name of the file you wish to export this data to.");
                            String _file=scanString.nextLine();
                            try {
                                PrintWriter pw = new PrintWriter(new File(_file));
                                pw.write(s.toString());
                                pw.close();
                            }catch (IOException e){
                                System.out.println("Unable to create file");
                            }

                            System.out.println("Exported");
                        }else{
                            System.out.println("Not exported");
                        }

                    }




                } catch (SQLException r) {
                    r.printStackTrace();
                }finally {
                    try {
                        con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }else {
                System.out.println("Not a valid option please choose again");
                selectForRent();
            }







        }else {
            System.out.println("Not a valid option please choose again");
            selectForRent();
        }
    }

    public void avgPriceForSalePerCity(){
        StringBuilder s=new StringBuilder();

        try {
            System.out.println("Please enter the city in which you would like to see the average price of houses for sale.");
            String city = scanString.nextLine();
            PreparedStatement p = con.prepareStatement("SELECT City, AVG(Price) as AverageHousePrice FROM forsale WHERE City=? GROUP BY City");
            p.setString(1,city);
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String format ="\u2503%1$-20s\u2503%2$-20s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No house exists with the city "+city);

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++");
                System.out.format(format, col1, col2);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getDouble(2));
                    System.out.format(format, "--------------------", "--------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getDouble(2));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }





        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    public void avgPriceForRentPerCity(){
        StringBuilder s=new StringBuilder();

        try {
            System.out.println("Please enter the city in which you would like to see the average price of houses for rent.");
            String city = scanString.nextLine();
            PreparedStatement p = con.prepareStatement("SELECT City, AVG(Price) as AverageHousePrice FROM forrent WHERE City=? GROUP BY City");
            p.setString(1,city);
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String format ="\u2503%1$-20s\u2503%2$-20s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No record exists with the city "+city);

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++");
                System.out.format(format, col1, col2);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getDouble(2));
                    System.out.format(format, "--------------------", "--------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getDouble(2));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public void userForSaleForRent(){
        StringBuilder s=new StringBuilder();

        try {
            System.out.println("Displaying users with a house for sale and for rent");
            PreparedStatement p = con.prepareStatement("SELECT * FROM SellsAndRents");
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String format ="\u2503%1$-20s\u2503%2$-20s\u2503%3$-20s\u2503%4$-20s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("No record exists");

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                System.out.format(format, col1, col2,col3,col4);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getString(2),r.getInt(3),r.getString(4));
                    System.out.format(format, "--------------------", "--------------------","--------------------","--------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void viewAgents(){
        StringBuilder s=new StringBuilder();

        try{
            PreparedStatement p = con.prepareStatement("SELECT agents.AgentID, AgentFirstName, AgentLastName, CityJurisdiction, Phone, AgentEmail FROM agents JOIN agentinformation ON agents.AgentID=agentinformation.AgentID");
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3=rm.getColumnName(3);
            String col4=rm.getColumnName(4);
            String col5=rm.getColumnName(5);
            String col6=rm.getColumnName(6);


            String format ="\u2503%1$-20s\u2503%2$-20s\u2503%3$-20s\u2503%4$-20s\u2503%5$-20s\u2503%6$-20s\u2503\n";



            if(!r.isBeforeFirst()){
                System.out.println("Records do not exists");

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                System.out.format(format, col1, col2,col3, col4,col5,col6);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getInt(1),r.getString(2), r.getString(3),r.getString(4),r.getInt(5), r.getString(6));
                    System.out.format(format, "--------------------", "--------------------","--------------------","--------------------","--------------------","--------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getString(6));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    public void betweenPriceRangeSale(){
        StringBuilder s=new StringBuilder();

        try{
            System.out.println("In order to find houses between a certain range we need a min and a max");
            System.out.println("What is the minimum price you are searching for?");
            while (!scan.hasNextDouble()) {
                System.out.println("That's not a proper minimum price!");
                System.out.println("What is the minimum price you are searching for?");
                scan.next(); // this is important!
            }
            double min=scan.nextDouble();
            System.out.println("What is the maximum price you are searching?");
            while (!scan.hasNextDouble()) {
                System.out.println("That's not a proper maximum price!");
                System.out.println("What is the maximum price you are searching for?\"");
                scan.next(); // this is important!
            }
            double max=scan.nextDouble();
            PreparedStatement p = con.prepareStatement("SELECT * FROM ForSale WHERE Price BETWEEN ? AND ?");
            p.setDouble(1, min);
            p.setDouble(2,max);
            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);



            if(!r.isBeforeFirst()){
                System.out.println("No house for sale between the prices of "+min+ " and "+max);

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append(',');
                s.append(col8);
                s.append(',');
                s.append(col9);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));

                    System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getInt(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getDouble(6));
                    s.append(',');
                    s.append(r.getDouble(7));
                    s.append(',');
                    s.append(r.getDouble(8));
                    s.append(',');
                    s.append(r.getDouble(9));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        }catch (SQLException e){
        e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void betweenPriceRangeRent(){
        StringBuilder s=new StringBuilder();

        try{
            System.out.println("In order to find houses between a certain range we need a min and a max");
            System.out.println("What is the minimum price you are searching for?");
            while (!scan.hasNextDouble()) {
                System.out.println("That's not a proper minimum price!");
                System.out.println("What is the minimum price you are searching for?");
                scan.next(); // this is important!
            }
            double min=scan.nextDouble();
            System.out.println("What is the maximum price you are searching?");
            while (!scan.hasNextDouble()) {
                System.out.println("That's not a proper maximum price!");
                System.out.println("What is the maximum price you are searching for?\"");
                scan.next(); // this is important!
            }
            double max=scan.nextDouble();
            PreparedStatement p = con.prepareStatement("SELECT * FROM ForRent WHERE Price BETWEEN ? AND ?");
            p.setDouble(1, min);
            p.setDouble(2,max);
            ResultSet r = p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();

            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3 = rm.getColumnName(3);
            String col4 = rm.getColumnName(4);
            String col5 = rm.getColumnName(5);
            String col6 = rm.getColumnName(6);
            String col7 = rm.getColumnName(7);
            String col8= rm.getColumnName(8);
            String col9=rm.getColumnName(9);



            if(!r.isBeforeFirst()){
                System.out.println("No house for rent between "+min+" and "+max);

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                System.out.format(format, col1, col2, col3, col4, col5, col6, col7, col8, col9);
                System.out.format(format, "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++", "++++++++++++++++++","++++++++++++++++++","++++++++++++++++++","++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append(',');
                s.append(col8);
                s.append(',');
                s.append(col9);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getInt(1),r.getInt(2), r.getString(3), r.getString(4), r.getInt(5), r.getDouble(6), r.getDouble(7), r.getDouble(8), r.getDouble(9));

                    System.out.format(format, "------------------", "------------------", "------------------", "------------------", "------------------", "------------------","------------------","------------------","------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getInt(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getDouble(6));
                    s.append(',');
                    s.append(r.getDouble(7));
                    s.append(',');
                    s.append(r.getDouble(8));
                    s.append(',');
                    s.append(r.getDouble(9));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }




        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void viewAgentsByCity(){
        StringBuilder s=new StringBuilder();

        try{
            System.out.println("Please enter the city in which you like to filter agents by.");
            String city=scanString.nextLine();
            PreparedStatement p = con.prepareStatement("SELECT agents.AgentID, AgentFirstName, AgentLastName, CityJurisdiction, Phone, AgentEmail FROM agents JOIN agentinformation ON agents.AgentID=agentinformation.AgentID WHERE CityJurisdiction=?");
            p.setString(1, city);
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3=rm.getColumnName(3);
            String col4=rm.getColumnName(4);
            String col5=rm.getColumnName(5);
            String col6=rm.getColumnName(6);


            String format ="\u2503%1$-20s\u2503%2$-20s\u2503%3$-20s\u2503%4$-20s\u2503%5$-20s\u2503%6$-20s\u2503\n";


            if(!r.isBeforeFirst()){
                System.out.println("No agent exists with jurisdiction over the city "+city);

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                System.out.format(format, col1, col2,col3, col4,col5,col6);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getInt(1),r.getString(2), r.getString(3),r.getString(4),r.getInt(5), r.getString(6));
                    System.out.format(format, "--------------------", "--------------------","--------------------","--------------------","--------------------","--------------------");
                    s.append(r.getInt(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getString(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getInt(5));
                    s.append(',');
                    s.append(r.getString(6));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public void AgentsAndAddressForSale(){
        StringBuilder s=new StringBuilder();

        try{
        PreparedStatement p = con.prepareStatement("SELECT Address, forsale.City, ZipCode, AgentFirstName, AgentLastName, agentinformation.Phone, AgentEmail FROM forsale JOIN agentinformation on forsale.city=agentinformation.CityJurisdiction JOIN agents ON agents.AgentID=agentinformation.AgentID");
        ResultSet r=p.executeQuery();
        ResultSetMetaData rm = r.getMetaData();
        String col1 = rm.getColumnName(1);
        String col2 = rm.getColumnName(2);
        String col3=rm.getColumnName(3);
        String col4=rm.getColumnName(4);
        String col5=rm.getColumnName(5);
        String col6=rm.getColumnName(6);
        String col7=rm.getColumnName(7);



        String format ="\u2503%1$-20s\u2503%2$-20s\u2503%3$-20s\u2503%4$-20s\u2503%5$-20s\u2503%6$-20s\u2503%7$-20s\u2503\n";

            if(!r.isBeforeFirst()){
                System.out.println("Record does not exist");

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                System.out.format(format, col1, col2,col3, col4,col5,col6, col7);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getString(2), r.getInt(3),r.getString(4),r.getString(5), r.getInt(6), r.getString(7));
                    System.out.format(format, "--------------------", "--------------------","--------------------","--------------------","--------------------","--------------------","--------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getInt(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getString(5));
                    s.append(',');
                    s.append(r.getInt(6));
                    s.append(',');
                    s.append(r.getString(7));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }


    }catch (SQLException e){
        e.printStackTrace();
    }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void AgentsAndAddressForRent(){
        StringBuilder s=new StringBuilder();

        try{
            PreparedStatement p = con.prepareStatement("SELECT Address, forrent.City, ZipCode, AgentFirstName, AgentLastName, agentinformation.Phone, AgentEmail FROM forrent JOIN agentinformation on forsale.city=agentinformation.CityJurisdiction JOIN agents ON agents.AgentID=agentinformation.AgentID");
            ResultSet r=p.executeQuery();
            ResultSetMetaData rm = r.getMetaData();
            String col1 = rm.getColumnName(1);
            String col2 = rm.getColumnName(2);
            String col3=rm.getColumnName(3);
            String col4=rm.getColumnName(4);
            String col5=rm.getColumnName(5);
            String col6=rm.getColumnName(6);
            String col7=rm.getColumnName(7);



            String format ="\u2503%1$-20s\u2503%2$-20s\u2503%3$-20s\u2503%4$-20s\u2503%5$-20s\u2503%6$-20s\u2503%7$-20s\u2503\n";


            if(!r.isBeforeFirst()){
                System.out.println("Record does not exist");

            }else{
                System.out.println("");
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                System.out.format(format, col1, col2,col3, col4,col5,col6, col7);
                System.out.format(format, "++++++++++++++++++++", "++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++","++++++++++++++++++++");
                s.append(col1);
                s.append(',');
                s.append(col2);
                s.append(',');
                s.append(col3);
                s.append(',');
                s.append(col4);
                s.append(',');
                s.append(col5);
                s.append(',');
                s.append(col6);
                s.append(',');
                s.append(col7);
                s.append('\n');
                while (r.next()){
                    System.out.format(format, r.getString(1),r.getString(2), r.getInt(3),r.getString(4),r.getString(5), r.getInt(6), r.getString(7));
                    System.out.format(format, "--------------------", "--------------------","--------------------","--------------------","--------------------","--------------------","--------------------");
                    s.append(r.getString(1));
                    s.append(',');
                    s.append(r.getString(2));
                    s.append(',');
                    s.append(r.getInt(3));
                    s.append(',');
                    s.append(r.getString(4));
                    s.append(',');
                    s.append(r.getString(5));
                    s.append(',');
                    s.append(r.getInt(6));
                    s.append(',');
                    s.append(r.getString(7));
                    s.append('\n');
                }
                System.out.println("Would you like to export this data to a CSV file press 1 to export press 2 to continue without exporting?");
                while (!scan.hasNextInt()) {
                    System.out.println("That's not a number!");
                    System.out.println("Enter the ID to filter by.");
                    scan.next(); // this is important!
                }
                int csv=scan.nextInt();
                if(csv==1){
                    System.out.println("Please enter the name of the file you wish to export this data to.");
                    String _file=scanString.nextLine();
                    try {
                        PrintWriter pw = new PrintWriter(new File(_file));
                        pw.write(s.toString());
                        pw.close();
                    }catch (IOException e){
                        System.out.println("Unable to create file");
                    }

                    System.out.println("Exported");
                }else{
                    System.out.println("Not exported");
                }

            }



        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }






}
