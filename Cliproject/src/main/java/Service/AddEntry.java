package Service;

import Controller.MainClass;

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
               "    Name:\"\",\n" +
               "    Address:\"\",\n" +
               "    Task:\"\",\n" +
               "    Severity:\"\",\n" +
               "    Status:\"\"\n" +
               "}");


       // Take the new entry
       Scanner sn = new Scanner(System.in);

       String Entry = sn.next();

       if(Entry.equals("Exit")){
           MainClass.run();
       }
       else{

       }


      // data will store in DB



    }


}
