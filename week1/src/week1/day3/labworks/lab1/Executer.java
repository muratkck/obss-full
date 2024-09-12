package week1.day3.labworks.lab1;


import week1.day3.labworks.lab1.exceptions.MyCheckedException;
import week1.day3.labworks.lab1.exceptions.MyUncheckedException;

public class Executer {

    public static double exec() {

        double divisionResult = 0; // default 0
        try{
            divisionResult = Divider.divide();
        }
        catch (MyCheckedException err){
            System.out.println("MyCheckedException occurred: " + err.getMessage());
        }
        catch (MyUncheckedException err){
            System.out.println("MyUncheckedException occurred: " + err.getMessage());
        }
        return divisionResult;
    }
}
