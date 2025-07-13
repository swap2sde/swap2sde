package Service;

import Controller.MainClass;
import DBConnection.OJDBCConnecter;
import org.json.JSONArray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class DeleteEntry {

    public static void deleteExistingEntry() {
        //Ask which entry he/she wants to delete HIGH,MEDIUM,LOW
        //Show the entry according to Severity
        //Take entry for which data to delete
        //Take YES or NO
        //Finally action to be performed

        try {
            //Ask which entry he/she wants to delete HIGH,MEDIUM,LOW
            System.out.println("--Which level of entry you want to delete--");
            System.out.println();
            System.out.println("1. HIGH");
            System.out.println("2. MEDIUM");
            System.out.println("3. LOW");

            System.out.println();
            System.out.print("Which serverity you want to delete : ");

            Scanner sn = new Scanner(System.in);

            int num = sn.nextInt();

            if (num > 3 || num < 1) {
                System.out.println("Please make a valid input");
                deleteExistingEntry();
            } else {
                //Show the entry according to Severity
                String serverity = "";
                if (num == 1) {
                    serverity = "HIGH";
                } else if (num == 2) {
                    serverity = "MEDIUM";
                } else {
                    serverity = "LOW";
                }

                try (Connection conn = OJDBCConnecter.getConnection()) {
                    String sql = "SELECT Listagg(JSON_OBJECT(id,name,task,status),',') AS json_result FROM DAILYTASK WHERE SEVERITY =?";
                    System.out.println("Delete query" + sql + " " + serverity);
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    stmt.setString(1, serverity);

                    ResultSet rs = stmt.executeQuery();
                    JSONArray results = new JSONArray();

                    if (rs.next()) {
                        String jsonString = rs.getString("json_result");

                        if (jsonString != null && !jsonString.isEmpty() && !jsonString.equals("")) {
                            // Wrap in brackets to convert to valid JSON array
                            results = new JSONArray("[" + jsonString +  "]");
                        }
                        else{
                            System.out.println("**Entry doesn't exist in " +  serverity + " catgeory**");
                            System.out.println();
                            deleteExistingEntry();
                        }
                    }
                    System.out.println(results.toString());
                    System.out.println();

                    rerun();

                } catch (Exception e) {
                    System.out.println("Please the error while deleting" + e);
                    deleteExistingEntry();
                }
            }
        } catch (Exception e) {
            System.out.println("--Entry is not correct please try again--" + e);
            deleteExistingEntry();
        }


    }

    public static void deleteEntryHere() throws SQLException, ClassNotFoundException {

        Scanner sn = new Scanner(System.in);

        System.out.print("Enter the ID : ");

        int num = sn.nextInt();

        //     CheckEntryExist(num);

        if(CheckEntryExist(num).equals("true")) {
            try (Connection conn = OJDBCConnecter.getConnection()) {
                String sql = "DELETE FROM DAILYTASK WHERE ID = ?";
                PreparedStatement stmnt = conn.prepareStatement(sql);
                stmnt.setInt(1, num);

                System.out.print("1. Are you sure you want to delete the entry entry Y/N: ");
                String a = sn.next();

                if (a.equals("Y")) {
                    int rs = stmnt.executeUpdate();
                    if (rs > 0) {
                        System.out.println("Entry deleted successfully.");
                    }
                } else if (a.equals("N")) {
                    rerun();
                } else {
                    System.out.println("Please make a valid entry");
                    rerun();
                }

            } catch (Exception e) {

            }
        }
        else {
            System.out.println("**** Entry does not exist with the given ID, Kindly enter a valid input****");
            rerun();
        }
    }

    public static void rerun() throws SQLException, ClassNotFoundException {


        System.out.println("1. Want rerun");
        System.out.println("2. Delete entry");
        System.out.println("3. Go back to main menu");

        System.out.print("Your input : "  );

        Scanner sn = new Scanner(System.in);
        int del = sn.nextInt();

        if(del == 1){
            deleteExistingEntry();
        }
        else if(del == 2){
            deleteEntryHere();
        }
        else if (del == 3){
            MainClass.run();
        }
        else {
            System.out.println("Please provide a valid entry ");
            rerun();
        }


    }

    public static String CheckEntryExist(int num){
        String bol = "false";

        try(Connection conn = OJDBCConnecter.getConnection()) {
            String sql = "SELECT COUNT(*) AS COUNT FROM DAILYTASK WHERE ID = ?";
            PreparedStatement stmnt = conn.prepareStatement(sql);
            stmnt.setInt(1,num);
            ResultSet rs = stmnt.executeQuery();



            if(rs.next()){
                int count = rs.getInt("COUNT");
                if (count >0) {
                    System.out.println("count :" + count);
                    bol = "true";
                }
            }
        }
        catch (Exception e){
            System.out.println("While checking Entry Exist CheckEntryExist : " + e);
        }

        System.out.println("bol : "+ bol);
        return bol;
    }

}