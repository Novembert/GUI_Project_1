package gui.norbert_bujny.projekt1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public abstract class Utilities {
    public static String handleUserRequiredInput (String prompt) {
        Scanner scanner = new Scanner(System.in);

        String result;
        do {
            System.out.println(prompt);
            result = scanner.nextLine();
        } while (result.equals(""));

        return result;
    }

    public static String handleUserInput (String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);

        return  scanner.nextLine();
    }

    public static List<String> handleUserListInput (String prompt) {
        String stringList = Utilities.handleUserInput(prompt);
        if (!stringList.equals("")){
            return Utilities.parseCommaSeparatedStringToList(stringList);
        }

        return new ArrayList<>();
    }

    public static List<String> handleUserRequiredListInput (String prompt) {
        List<String> result = new ArrayList<>();

        do {
            String stringList = Utilities.handleUserInput(prompt);
            if (!stringList.equals("")){
                result = Utilities.parseCommaSeparatedStringToList(stringList);
            }
        } while (result.size() == 0 );


        return result;
    }

    public static List<String> parseCommaSeparatedStringToList(String str) {
        return Arrays.asList(str.split("\\s*,\\s*"));
    }

    public static List<String> parseSeparatedStringToList(String str, String separator) {
        return Arrays.asList(str.split("\\s*" + separator + "\\s*"));
    }

    public static String getPolishTranslationForBooleanValues(Boolean value) {
        return value ? "Tak" : "Nie";
    }
}
