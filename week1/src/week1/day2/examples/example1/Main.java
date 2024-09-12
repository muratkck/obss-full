package week1.day2.examples.example1;

import week1.day2.examples.example1.entities.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        boolean continueCalculation = true;

        while (continueCalculation) {

            System.out.println("Alan hesaplamak istediğiniz çokgeni seçiniz");

            Scanner scanner = new Scanner(System.in);
            System.out.print("1- Üçgen\n2- Kare\n3- Dikdörtgen\n4- Daire\n0- Çıkış\n> ");

            int choice = getIntInput(scanner, "Lütfen geçerli bir sayı giriniz!");

            // Let's try switch-case
            switch (choice) {
                case 0:{
                    continueCalculation = false;
                    System.out.println("Çıkış yapılıyor...");
                    break;
                }
                case 1:{
                    double floorArea = getDoubleInput(scanner, "Taban alanı:\n> ");
                    double height = getDoubleInput(scanner, "Yükseklik:\n> ");

                    Shape triangle = new Triangle(floorArea, height);
                    System.out.println("Sonuç: " + floorArea + " * " + height + "/ 2 " + " = " + triangle.getArea());
                    break;

                }
                case 2:{
                    double sideLength = getDoubleInput(scanner, "Kenar uzunluğu:\n> ");

                    Shape square = new Square(sideLength);
                    System.out.println("Sonuç: " + sideLength + " * " + sideLength + " = " + square.getArea());
                    break;
                }
                case 3:{
                    double shortSideLength = getDoubleInput(scanner, "Kısa kenar uzunluğu:\n> ");
                    double longSideLength = getDoubleInput(scanner, "Uzun kenar uzunluğu:\n> ");

                    Shape rectangle = new Rectangle(longSideLength, shortSideLength);
                    System.out.println("Sonuç: " + shortSideLength + " * " + longSideLength + " = " + rectangle.getArea());
                    break;
                }
                case 4:{
                    double radius = getDoubleInput(scanner, "Yarıçap uzunluğu:\n> ");

                    Shape circle = new Circle(radius);
                    System.out.println("Sonuç: " + radius + " * " + radius + " * " + Shape.PI + " = " + circle.getArea());
                    break;
                }
                default:{
                    System.out.println("Lütfen geçerli bir seçenek giriniz!");
                    break;
                }
            }
        }




    }

    private static int getIntInput(Scanner scanner, String errorMessage) {
        while (!scanner.hasNextInt()) {
            System.out.println(errorMessage);
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static double getDoubleInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Lütfen geçerli bir sayı giriniz!");
            scanner.next();
            System.out.print(prompt);
        }
        return scanner.nextDouble();
    }
}
