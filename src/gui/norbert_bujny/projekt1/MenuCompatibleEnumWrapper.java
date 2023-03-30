package gui.norbert_bujny.projekt1;

import java.util.*;

public class MenuCompatibleEnumWrapper<T extends Enum> {
    private List<T> wrappedEnumClassValues;

    private Set<T> chosenOptions = new HashSet<>();

    public MenuCompatibleEnumWrapper(T[] wrappedEnumClassValues) {
        this.wrappedEnumClassValues = Arrays.asList(wrappedEnumClassValues);
    }

    public List<T> getOptions() {
        return this.wrappedEnumClassValues;
    }

    public void setChosenOption(int optionIndex) {
        this.chosenOptions.add(this.wrappedEnumClassValues.get(optionIndex));
    }

    public T getChosenOption() {
        return (T) this.chosenOptions.toArray()[0];
    }

    public Set<T> getChosenOptions() {
        return this.chosenOptions;
    }
}
