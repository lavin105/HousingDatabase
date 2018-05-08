
/*This project consists of 9 classes all of which have their own purpose. Any time a table is printed to the console printf
 * is used with a specific format string in order to display data in an organized manor. In addition, the meta data of the
 * result sets are used in order to retrieve the names of the columns of the table the data is coming from. All insertions,
 * updates, and deletes use transactions. Queries that contained joins across three tables were created into a views so see the
 * DDL in order to view those queries. All selection queries can be exported to CSV files. A string builder and print write are used to
 * append the data to a csv file tht is written to is the user so desires. Soft delete is also incorporated
 * into the houses for sale and for rent. Indexes can also be seen in the DDL.*/

public class Main {

    public static void main(String args[]) throws InterruptedException{

        //initialize the entire system
        Engine e =new Engine();
        e.runStart();
        e.runBody();


    }
}

