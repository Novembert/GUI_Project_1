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

    public static int handleUserRequiredInputInt(String prompt) {
        Scanner scanner = new Scanner(System.in);

        Integer result = null;
        boolean error = false;
        do {
            System.out.println(prompt);
            try {
                result = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                error = true;
                System.out.println("Zły format");
            }
        } while (error);

        return result;
    }

    public static double handleUserRequiredInputDouble(String prompt) {
        Scanner scanner = new Scanner(System.in);

        Double result = null;
        boolean error = false;
        do {
            System.out.println(prompt);
            try {
                result = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                error = true;
                System.out.println("Zły format");
            }
        } while (error);

        return result;
    }

    public static String handleUserInput(String prompt) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(prompt);

        return scanner.nextLine();
    }

    public static List<String> handleUserListInput(String prompt) {
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

    public static String handleUserRequiredStringListInput(String prompt, List<String> options) {
        System.out.println(prompt);

        Utilities.printListOptions(options);

        int chosenOption;
        do {
            chosenOption = Utilities.handleUserRequiredInputInt("Wybierz opcję: ");
        } while (chosenOption < 1 || chosenOption > options.size());

        return options.get(chosenOption - 1);
    }

    public static MenuCompatibleEnumWrapper handleUserRequiredEnumInput(String prompt, MenuCompatibleEnumWrapper enumWrapper) {
        System.out.println(prompt);

        List<Enum> enumOptions = enumWrapper.getOptions();
        Utilities.printEnumOptions(enumOptions);

        int chosenOption = -1;
        do {
            chosenOption = Utilities.handleUserRequiredInputInt("Wybierz opcję: ");
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
        } while (chosenOptions.stream().filter(i -> i < 1 || i > enumOptions.size()).toList().size() > 0);

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

    private static void printListOptions(List<String> options) {
        int optionIndex = 1;

        for (String option : options) {
            System.out.println("[" + optionIndex + "] " + option);
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

    public static void printRetryInformation(int retry, int max) {
        System.out.println("[Próba " + retry + ". z " + max + "]");
    }

    public static String getCorrectSingularOrPluralForm(int number, String singular, String plural1, String plural2) {
        if (number == 1) return singular;
        if (number < 5 && number > 1) return plural1;
        return plural2;
    }

    public static String visualizeProgress(int a, int b) {
        double progressPercentage = a * 100 / b;
        return progressPercentage + "% " + Utilities.getProgressVisualizer(progressPercentage);
    }

    public static String visualizeProgress(double a, double b) {
        Double progressPercentage = a * 100 / b;
        Number progressPercentageRounded = Math.round(progressPercentage);
        String progressPercentageString = progressPercentage.isNaN() ? "0" : progressPercentageRounded.toString();
        return progressPercentageString + "% " + Utilities.getProgressVisualizer(progressPercentage);
    }

    private static String getProgressVisualizer(double progressPercentage) {
        int completed = (int) progressPercentage / 10;
        int not_completed = 10 - completed;

        return "[" + Utilities.repeatString(completed, "=") + Utilities.repeatString(not_completed, " ") + "]";
    }

    /**
     * https://stackoverflow.com/a/16812721
     */
    public static String repeatString(int count, String with) {
        return new String(new char[count]).replace("\0", with);
    }
}
