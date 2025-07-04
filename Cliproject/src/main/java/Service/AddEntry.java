package Service;

import Controller.MainClass;
import org.json.JSONObject;

import javax.naming.Name;
import java.util.Scanner;

public class AddEntry {

   public static void addNewEntry(){
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
       System.out.println("{\n" +
               "    \"Name\":\"\",\n" +
               "    \"Address\":\"\",\n" +
               "    \"Task\":\"\",\n" +
               "    \"Severity\":\"\",\n" +
               "    \"Status\":\"\"\n" +
               "}");


       System.out.println();
       System.out.print("Make your entry : " );
       // Take the new entry

       try {
           Scanner sn = new Scanner(System.in);

           String Entry = sn.nextLine();

           if(Entry.equals("Exit")){
               MainClass.run();
           }
           else{
               System.out.println("");

               JSONObject obj = new JSONObject(Entry.trim());

               System.out.println("Printing obj: " + obj);

               System.out.println("Get Name :" + obj.getString("Name"));
           }
       }
       catch (RuntimeException e) {
           System.out.println("RunTime :"+ e);
       } catch (Exception e) {
           System.out.println("RunTime :"+ e);
       }


      // data will store in DB



    }


}
