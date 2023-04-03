package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Connection implements Serializable {
    private Set<Station> stations;
    private double distance;
    private boolean isUsed;

    public Connection(Station st1, Station st2, double distance) {
        this.stations = new HashSet<>();
        this.stations.add(st1);
        this.stations.add(st2);
        this.distance = distance;
        this.isUsed = false;
    }

    public Station getTargetStation(Station sourceStation) {
        return this.stations.stream().filter(station -> !station.equals(sourceStation)).toList().get(0);
    }

    public double getDistance() {
        return this.distance;
    }

    public void setIsUsed(Boolean isUsed) {
        this.isUsed = isUsed;
    }

    @Override
    public String toString() {
        return this.stations.toString() + " (" + this.distance + "km)";
    }

    public String toDirectionalString(Station from) {
        return from.toString() + " -> " + this.getTargetStation(from).toString() + " (" + distance + "km)";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connection that = (Connection) o;
        return Double.compare(that.distance, distance) == 0 && Objects.equals(stations, that.stations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stations, distance);
    }

}
