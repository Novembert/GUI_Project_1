package gui.norbert_bujny.projekt1;

// https://www.baeldung.com/java-graphs

import java.io.Serializable;
import java.util.*;

public class StationsGraph implements Serializable {
    private final Map<Station, List<Station>> stationsConnections;

    StationsGraph() {
        this.stationsConnections = new HashMap<>();
    }

    StationsGraph(ModuleConfig config) {
        List<List<String>> configRows = config.getConfigRows();
        this.stationsConnections = new HashMap<>();

        for (List<String> configRow : configRows) {
            Station newStation = new Station(configRow.get(0), configRow.get(1));
            this.addStationToList(newStation, Utilities.parseCommaSeparatedStringToList(configRow.get(2)));
        }
    }

    public void addStationToList(Station station) {
        stationsConnections.putIfAbsent(station, new ArrayList<>());
    }

    public void addStationToList(Station station, List<String> connectionsCodes) {
        stationsConnections.putIfAbsent(station, new ArrayList<>());

        for (Station savedStation : stationsConnections.keySet()) {
//              TODO handle non-existing stationCode
            if (connectionsCodes.contains(savedStation.getCode())) {
                this.addConnection(savedStation, station);
            }
        }
    }

    public void removeStationFromList(Station station) {
        for (Station savedStation : stationsConnections.keySet()) {
            if (stationsConnections.get(savedStation).contains(station)) {
                this.removeConnection(savedStation, station);
            }
        }

        stationsConnections.remove(station);
    }

    public void removeStationFromList(String code) throws StationNotFoundException {
        Station stationToDelete = this.searchStationByCode(code);

        this.removeStationFromList(stationToDelete);
    }

    public void addConnection(Station station, Station targetStation) {
        this.stationsConnections.get(station).add(targetStation);
        this.stationsConnections.get(targetStation).add(station);
    }

    public void addConnection(String code, String targetCode) throws StationNotFoundException {
        Station station = this.searchStationByCode(code);
        Station targetStation = this.searchStationByCode(targetCode);

        this.addConnection(station, targetStation);
    }

    public void addMultipleConnections(String code, List<String> targetCodes) throws StationNotFoundException {
        for (String targetCode : targetCodes) {
            this.addConnection(code, targetCode);
        }
    }

    public void removeConnection(Station station, Station targetStation) {
        this.stationsConnections.get(station).remove(targetStation);
    }

//    TODO FIX!
    public Set<Station> findPath(String code, String targetStationCode) throws StationNotFoundException {
        Station station = this.searchStationByCode(code);
        Station targetStation = this.searchStationByCode(targetStationCode);

        Set<Station> visited = new LinkedHashSet<>();
        Queue<Station> queue = new LinkedList<>();

        queue.add(station);
        visited.add(station);

        while (!queue.isEmpty()) {
            Station currentStation = queue.poll();
            for (Station st : this.stationsConnections.get(currentStation)) {
                if (!visited.contains(targetStation)) {
                    visited.add(st);
                    queue.add(st);
                } else {
                    visited.add(st);
                    break;
                }
            }
        }
        return visited;
    }

    private Station searchStationByCode(String code) throws StationNotFoundException {
        Set<Station> stationsSet = this.stationsConnections.keySet();

        for (Station station : stationsSet) {
            if (station.getCode().equals(code)) {
                return station;
            }
        }

        throw new StationNotFoundException("Nie znaleziono stacji");
    }

    @Override
    public String toString() {
        return "StationsGraph{" +
                "stationsConnections=" + stationsConnections +
                '}';
    }
}

