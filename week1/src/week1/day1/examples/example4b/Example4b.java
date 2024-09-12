package week1.day1.examples.example4b;

public class Example4b {
    public static void main(String[] args) {

        String[][] areas = {
                {"Merkez", "Aydıntepe", "Demirözü"},
                {"Merkez", "Banaz", "Eşme", "Karahallı", "Sivaslı", "Ulubey"}
        };

        for (int i = 0; i < areas.length; i++) {
            if(i == 0) {
                System.out.println("Bayburt ilçeleri: ");
            }
            else if (i == 1) {
                System.out.println("Uşak ilçeleri: ");
            }
            for (String area : areas[i]) {
                System.out.println("- " + area);
            }
        }
    }
}
