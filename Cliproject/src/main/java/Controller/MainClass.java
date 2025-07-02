package Controller;

import java.util.*;
import Service.ServiceLayer;

public class MainClass {


    public static void main(String[] args) {

        run();
    }

    public static void run(){
        // TODO Auto-generated method stub

        System.out.println("****Please let us know the action you want to perform.****");
        System.out.println();
        System.out.println("1. Enter New Task");
        System.out.println("2. Delete Existing Task");
        System.out.println("3. View Existing Task");
        System.out.println();
        System.out.print("Provide your input : ");


        Scanner sn =  new Scanner(System.in);
        int input = sn.nextInt();
        System.out.println();
        ServiceLayer.CheckInput(input);


//		System.out.println("Modify Existing Task");



    }



}
