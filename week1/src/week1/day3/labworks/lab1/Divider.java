package week1.day3.labworks.lab1;

import week1.day3.labworks.lab1.exceptions.MyCheckedException;
import week1.day3.labworks.lab1.exceptions.MyUncheckedException;

public class Divider {

    public static double divide() throws MyCheckedException, MyUncheckedException {
        double a = 1;
        double b = 0;

        if (b == 0){
            throw new MyUncheckedException("Division by zero is not possible!");
        }
        return a / b;
    }
}
