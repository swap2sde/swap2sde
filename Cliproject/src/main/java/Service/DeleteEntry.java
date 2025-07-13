package Service;

import DBConnection.OJDBCConnecter;
import org.json.JSONArray;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class DeleteEntry {

    public static void deleteExistingEntry(){
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
                    System.out.println("Delete query" + sql+" " + serverity);
                    PreparedStatement stmt = conn.prepareStatement(sql);

                    stmt.setString(1, serverity);

                    ResultSet rs = stmt.executeQuery();
                    JSONArray results = new JSONArray();

                    if (rs.next()) {
                        String jsonString = rs.getString("json_result");

                        if (jsonString != null && !jsonString.isEmpty()) {
                            // Wrap in brackets to convert to valid JSON array
                            results = new JSONArray("[" + jsonString + "]");
                        }
                    }
                    System.out.println(results.toString());


                } catch (Exception e) {
                    System.out.println("Please the error while deleting" + e);
                    deleteExistingEntry();
                }
            }
        } catch (Exception e) {
            System.out.println("--Entry is not correct please try again--"+ e);
            deleteExistingEntry();
        }


    }

}
