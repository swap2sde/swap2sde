package Service;

import static Controller.MainClass.run;

public class ServiceLayer {

    public static void CheckInput(int input){

        try{

        System.out.println("Input is : " + input);

        switch (input) {

            case 1:
                    AddEntry.addNewEntry();
                break;
            case 2:
                    DeleteEntry.deleteExistingEntry();
                break;
            case 3:
                    ViewEntry.viewExistingEntry();
                break;

            default: {
                System.out.println("----Please enter a valid input----") ;
                run();
            }
        }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

}
