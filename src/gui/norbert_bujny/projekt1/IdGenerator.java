package gui.norbert_bujny.projekt1;

import java.lang.reflect.Field;

public abstract class IdGenerator {
    private static String carID = "C-0";
    private static String trainID = "T-0";

    public static String resolveID(String idName) {
        IdGenerator.increaseID(idName);
        return IdGenerator.getIdValue(idName);
    }

    private static void increaseID(String field) {
        String fieldValue = IdGenerator.getIdValue(field);
        int id = Integer.parseInt(fieldValue.substring(2));
        id = id + 1;
        String idPrefix = fieldValue.substring(0, 2);
        IdGenerator.carID = idPrefix + id;
    }

    private static String getIdValue(String field) {
        String fieldValue = "";
        try {
            Field idField = IdGenerator.class.getDeclaredField(field);
            idField.setAccessible(true);
            fieldValue = (String) idField.get(null);
        } catch (Throwable e) {
            System.err.println(e);
        }
        return fieldValue;
    }
}
