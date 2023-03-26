package gui.norbert_bujny.projekt1;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.stream.Collectors;

public class CarTypesToObjectsMap {
    private static CarTypesToObjectsMap instance;
    private static String packageName = "gui.norbert_bujny.projekt1";
    public static EnumMap<CarTypes, String> map = new EnumMap<>(CarTypes.class);

    private CarTypesToObjectsMap() {
        for (CarTypes carType : EnumSet.allOf(CarTypes.class)) {
            this.map.put(carType, this.packageName + "." + this.parseEnumNameToObjectName(carType.name()));
        }
    }

    private String parseEnumNameToObjectName(String enumName) {
        return Arrays.stream(enumName.split("_"))
                .map(s -> s.toLowerCase())
                .map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
                .collect(Collectors.joining());
    }

    public static CarTypesToObjectsMap getInstance() {
        if (instance == null) {
            instance = new CarTypesToObjectsMap();
        }
        return instance;
    }
}
