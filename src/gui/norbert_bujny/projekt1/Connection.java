package gui.norbert_bujny.projekt1;

import java.util.HashSet;
import java.util.Set;

public class Connection {
    private Set<Station> stations;
    private double distance;

    public Connection(Station st1, Station st2, double distance) {
        this.stations = new HashSet<>();
        this.stations.add(st1);
        this.stations.add(st2);
        this.distance = distance;
    }
}
