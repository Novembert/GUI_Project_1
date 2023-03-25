package gui.norbert_bujny.projekt1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
            if (!stringList.equals("")) {
                result = Utilities.parseCommaSeparatedStringToList(stringList);
            }
        } while (result.size() == 0);


        return result;
    }

    public static MenuCompatibleEnumWrapper handleUserRequiredEnumInput(String prompt, MenuCompatibleEnumWrapper enumWrapper) {
        System.out.println(prompt);

        List<Enum> enumOptions = enumWrapper.getOptions();
        Utilities.printEnumOptions(enumOptions);

        int chosenOption;
        do {
            chosenOption = Integer.parseInt(Utilities.handleUserRequiredInput("Wybierz opcję: "));
        } while (chosenOption < 1 || chosenOption > enumOptions.size());
        enumWrapper.setChosenOption(chosenOption - 1);

        return enumWrapper;
    }

    public static MenuCompatibleEnumWrapper handleUserRequiredEnumSetInput(String prompt, MenuCompatibleEnumWrapper enumWrapper) {
        System.out.println(prompt);

        List<Enum> enumOptions = enumWrapper.getOptions();
        Utilities.printEnumOptions(enumOptions);

        List<Integer> chosenOptions;
        do {
            chosenOptions = Utilities.parseStringListToIntegerList(Utilities.handleUserRequiredListInput(prompt));
        } while (chosenOptions.stream().filter(i -> i < 1 || i > enumOptions.size()).collect(Collectors.toList()).size() > 0);

        for (Integer option : chosenOptions) {
            enumWrapper.setChosenOption(option - 1);
        }

        return enumWrapper;
    }

    private static void printEnumOptions(List<Enum> options) {
        int optionIndex = 1;

        for (Enum option : options) {
            System.out.println("[" + optionIndex + "] " + option.toString());
            optionIndex++;
        }
    }

    public static Boolean handleUserRequiredBooleanInput(String prompt) {
        String result = "";

        do {
            result = Utilities.handleUserRequiredInput(prompt + " [T/N]");
        } while (!result.equals("T") && !result.equals("N"));

        return result.equals("T");
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

    public static List<Integer> parseStringListToIntegerList(List<String> sourceList) {
        return sourceList.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
    }
}
