import java.util.Scanner;

public class NumbersToWords {

    public static void main(String[] args) {
        long number = 0;
        String numberString;
        String numberInWords;
        Scanner sc = new Scanner(System.in);
        boolean valid = true;

        System.out.printf("%nIngresar un valor numérico positivo (hasta 10^18): ");
        numberString = sc.nextLine();

        if ((numberString.length() == 19 && !numberString.equals("1000000000000000000"))
                || numberString.length() > 19 || numberString.startsWith("-")) {
            valid = false;
        } else {
            try {
                number = Long.parseLong(numberString);
            } catch (NumberFormatException e) {
                valid = false;
            }
        }

        if (valid) {
            numberInWords = numberToWords(number);
            numberInWords = numberInWords.substring(0, 1).toUpperCase() + numberInWords.substring(1);
            System.out.printf("%n" + numberInWords + "%n");
        } else {
            System.out.printf("%n¡Valor inválido!%n");
        }
    }

    public static String numberToWords(long number) {
        final String[] unidades = {
                "cero", "uno", "dos", "tres", "cuatro", "cinco", "seis", "siete", "ocho", "nueve"};
        final String[] decimos = {
                "diez", "once", "doce", "trece", "catorce", "quince", "dieciséis", "diecisiete", "dieciocho", "diecinueve"};
        final String[] vigesimos = {
                "veinte", "veintiuno", "veintidós", "veintitrés", "veinticuatro", "veinticinco", "veintiséis", "veintisiete",
                "veintiocho", "veintinueve"};
        final String[] decenas = {
                "treinta", "cuarenta", "cincuenta", "sesenta", "setenta", "ochenta", "noventa", "cien"};
        final String[] centenas = {
                "", "cien", "doscientos", "trescientos", "cuatrocientos", "quinientos", "seiscientos", "setecientos",
                "ochocientos", "novecientos"};

        String numberInWords = "";

        if (number < 10) {
            numberInWords = unidades[(int) number];
        } else if (number < 20) {
            numberInWords = decimos[(int) (number % 10)];
        } else if (number < 30) {
            numberInWords = vigesimos[(int) (number % 10)];
        } else if (number <= 100) {
            numberInWords += decenas[(int) ((number / 10) - 3)] + (number % 10 != 0 ? " y " + numberToWords(number % 10) : "");
        } else if (number < 200) {
            numberInWords += "ciento " + (number % 100 != 0 ? numberToWords(number % 100) : "");
        } else if (number < 1000) {
            numberInWords += centenas[(int) (number / 100)] + (number % 100 != 0 ? " " + numberToWords(number % 100) : "");
        } else if (number < 2000) {
            numberInWords += "mil" + (number % 1000 != 0 ? " " + numberToWords(number % 1000) : "");
        } else if (number < 1000_000) {
            numberInWords += numberToWords((int) (number / 1000));
            if (numberInWords.endsWith("uno"))
            { numberInWords = numberInWords.substring(0, numberInWords.length() - 1); }
            numberInWords += " mil" + (number % 1000 != 0 ? " " + numberToWords(number % 1000) : "");
        } else if (number < 2000_000) {
            numberInWords += "un millón" + (number % 1000_000 != 0 ? " " + numberToWords(number % 1000_000) : "");
        } else if (number < 1000_000_000_000L) {
            numberInWords += numberToWords((int) (number / 1000_000));
            if (numberInWords.endsWith("uno"))
            { numberInWords = numberInWords.substring(0, numberInWords.length() - 1); }
            numberInWords += " millones" + (number % 1000_000 != 0 ? " " + numberToWords(number % 1000_000) : "");
        } else if (number < 2000_000_000_000L) {
            numberInWords = "un billón" + (number % 1000_000_000_000L != 0 ? " " + numberToWords(number % 1000_000_000_000L) : "");
        } else if (number < 1000_000_000_000_000_000L) {
            numberInWords = numberToWords((int) (number / 1000_000_000_000L));
            if (numberInWords.endsWith("uno"))
            { numberInWords = numberInWords.substring(0, numberInWords.length() - 1); }
            numberInWords += " billones" + (number % 1000_000_000_000L != 0 ? " " + numberToWords(number % 1000_000_000_000L) : "");
        } else if (number == 1000_000_000_000_000_000L) {
            numberInWords = "un trillón";
        }

        return numberInWords;
    }
}