package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Station implements Serializable {
    private final String name;
    private final String code;
    private Map<Train, Connection> connectionsRequests;

    public Station(String name, String code) {
        this.name = name;
        this.code = code;
        this.connectionsRequests = new HashMap<>();
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "[" + this.code + "] " + this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(name, station.name) && Objects.equals(code, station.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code);
    }
}
