package Service;

import Controller.MainClass;
import DBConnection.OJDBCConnecter;
import com.sun.tools.javac.Main;
import org.json.JSONArray;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ViewEntry {

    public static void viewExistingEntry(){

        //just view
        //swicth to main menu

        try{
            //Ask which entry he/she wants to delete HIGH,MEDIUM,LOW
            System.out.println("--Which level of entry you want to view--");
            System.out.println();
            System.out.println("1. HIGH");
            System.out.println("2. MEDIUM");
            System.out.println("3. LOW");

            ShowEntry();
        }
        catch (Exception e){
            System.out.println("Error print from ViewEntry : "+ e);
        }
    }

    public static void ShowEntry() {

        System.out.println();
        System.out.print("Which serverity you want to delete : ");

        Scanner sn = new Scanner(System.in);

        int num = sn.nextInt();

        if (num > 3 || num < 1) {
            System.out.println("Please make a valid input");
            viewExistingEntry();
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
              //  System.out.println("View query" + sql + " " + serverity);
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, serverity);

                ResultSet rs = stmt.executeQuery();
                JSONArray results = new JSONArray();

                if (rs.next()) {
                    String jsonString = rs.getString("json_result");

                    if (jsonString != null && !jsonString.isEmpty() && !jsonString.equals("")) {
                        // Wrap in brackets to convert to valid JSON array
                        String formattedJsonString = jsonString.replace("},{", "},\n{");
                        results = new JSONArray("[" + formattedJsonString + "]");
                    } else {
                        System.out.println("**Entry doesn't exist in " + serverity + " catgeory**");
                        System.out.println();
                        viewExistingEntry();
                    }
                }
                System.out.println(results.toString());
                System.out.println();

                MainClass.run();

            } catch (Exception e) {
                System.out.println("Please the error while deleting" + e);
                viewExistingEntry();
            }

        }
    }
}
