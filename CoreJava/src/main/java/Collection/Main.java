package Collection;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> arr = new ArrayList<>();

        arr.add(5);
        arr.add(10);
        arr.add(50);

        System.out.println(arr.size());

        for(int x : arr){
            System.out.println(x);
        }



    }

}
