package spiel.utilities;

public class Helfer {

    public static String convertReiheInBuchstabe(int reihe) {
        switch (reihe) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            default:
                throw new RuntimeException("Bad Input");
        }
    }

    public static int convertBuchstabeInReihe(String buchstabe) {
        switch (buchstabe) {
            case "A":
                return 1;
            case "B":
                return 2;
            case "C":
                return 3;
            case "D":
                return 4;
            case "E":
                return 5;
            case "F":
                return 6;
            case "G":
                return 7;
            case "H":
                return 8;
            default:
                throw new RuntimeException("Bad Input");
        }
    }

}
