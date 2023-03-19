package gui.norbert_bujny.projekt1;

public class Station {
    private final String name;
    private final String code;

    public Station(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "["+ this.code +"] " + this.name;
    }

    //    TODO - override equals and hashCode here in menus map
}
