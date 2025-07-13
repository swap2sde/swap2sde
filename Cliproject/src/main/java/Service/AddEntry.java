package Service;

import Controller.MainClass;
import DBConnection.OJDBCConnecter;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class AddEntry {

    public static void addNewEntry(){

        // Share the sample How to make entry
        // Take the new entry
        // data will store in DB


        // Share the sample How to make entry
        /**
         * {
         * {
         *     Name:
         *     Address:
         *     Task:
         *     Severity:
         *     Status:
         * }
         * UniqueNumber
         * }
         */
        System.out.println();
        System.out.println("Provide entry in below format or type Exit to escape");
        System.out.println("{\"Name\":\"\",\"Address\":\"\",\"Task\":\"\",\"Severity\":\"\",\"Status\":\"\"}");

        System.out.println();
        System.out.print("Make your entry : " );
        // Take the new entry

        try {
            Scanner sn = new Scanner(System.in);

            String Entry = sn.nextLine();

            if(Entry.equalsIgnoreCase("Exit")){
                MainClass.run();
            }
            else{
                System.out.println();

                JSONObject obj = new JSONObject(Entry.trim());

                System.out.println("Printing obj: " + obj);

                System.out.println("Get Name :" + obj.getString("Name"));
                System.out.println("Get Address :" + obj.getString("Address"));
                System.out.println("Get Task :" + obj.getString("Task"));
                System.out.println("Get Severity :" + obj.getString("Severity"));
                System.out.println("Get Status :" + obj.getString("Status"));


                String Name = obj.getString("Name");
                String Address = obj.getString("Address");
                String Task = obj.getString("Task");
                String Severity = obj.getString("Severity");
                String Status = obj.getString("Status");


                addDatatoDB(Name,Address,Task,Severity,Status);

            }
        }
        catch (Exception e) {
            // System.out.println("RunTime :"+ e);
            System.out.println("--Entry is not correct please try again--"+ e);
            addNewEntry();
        }

    }

    // data will store in DB
    public static void addDatatoDB(String name, String address, String task, String severity, String status) {

        try(Connection conn = OJDBCConnecter.getConnection()){

            String sql = "INSERT INTO DAILYTASK (NAME,ADDRESS,TASK,SEVERITY,STATUS) VALUES(?,?,?,?,?)";
            //  System.out.println(sql);
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,name.toUpperCase());
            stmt.setString(2,address.toUpperCase());
            stmt.setString(3,task.toUpperCase());
            stmt.setString(4,severity.toUpperCase());
            stmt.setString(5,status.toUpperCase());


            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println();
                System.out.println("Entry has been inserted sucessfully.");
            }

            MainClass.run();

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

     //   return null;
    }


}