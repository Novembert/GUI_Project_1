package gui.norbert_bujny.projekt1;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class StationsGraph {
    private Map<Station, List<Connection>> stationsConnections;

    StationsGraph() {
        this.stationsConnections = new HashMap<>();
    }

    public void setStationsConnections(Map<Station, List<Connection>> stationsConnections) {
        this.stationsConnections = stationsConnections;
    }

    public Map<Station, List<Connection>> getStationsConnections() {
        return this.stationsConnections;
    }

    public Set<Connection> getAllStationsConnections() {
        Set<Connection> connectionSet = new HashSet<>();
        for (List<Connection> connectionList : this.stationsConnections.values()) {
            for (Connection connection : connectionList) {
                connectionSet.add(connection);
            }
        }

        return connectionSet;
    }

    public String getStationsList() {
        return this.stationsConnections
                .keySet()
                .stream()
                .map(
                        s -> s.toString() + " || Połączenia -> " + this.stationsConnections
                                .get(s)
                                .stream()
                                .map(connection -> connection.toDirectionalString(s))
                                .collect(Collectors.joining(", ")))
                .collect(Collectors.joining("\n"));
    }

    public void addStationToList(Station station) {
        stationsConnections.putIfAbsent(station, new ArrayList<>());
    }

    public int addStationToList(Station station, List<String> connectionsCodes) {
        stationsConnections.putIfAbsent(station, new ArrayList<>());
        int addedConnectionsCount = 0;

        for (Station savedStation : stationsConnections.keySet()) {
            if (connectionsCodes.contains(savedStation.getCode())) {
                this.addConnection(savedStation, station);
                addedConnectionsCount++;
            }
        }

        return addedConnectionsCount;
    }

    public void removeStationFromList(Station station) {
        for (Station savedStation : stationsConnections.keySet()) {
            Connection connection = this.findConnectionFromTo(savedStation, station);
            if (connection != null) {
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
        Connection stationsConnection = new Connection(station, targetStation, 1);
        this.stationsConnections.get(station).add(stationsConnection);
        this.stationsConnections.get(targetStation).add(stationsConnection);
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
        this.stationsConnections.get(station).remove(this.findConnectionFromTo(station, targetStation));
        this.stationsConnections.get(targetStation).remove(this.findConnectionFromTo(targetStation, station));
    }

    public List<Connection> findPath(String code, String targetStationCode) throws StationNotFoundException, PathNotFoundException {

        Station station = this.searchStationByCode(code);
        Station targetStation = this.searchStationByCode(targetStationCode);
        boolean pathFound = false;

        Queue<Station> stationsQueue = new LinkedList<>();
        Set<Station> visited = new LinkedHashSet<>();

        Map<Station, Station> path = new HashMap<>();

        for (Station st : this.stationsConnections.keySet()) {
            path.put(st, null);
        }

        stationsQueue.add(station);
        visited.add(station);

        while (!stationsQueue.isEmpty()) {
            Station currentStation = stationsQueue.poll();
            if (currentStation.equals(targetStation)) {
                pathFound = true;
                break;
            }
            for (Connection stationConnection : this.stationsConnections.get(currentStation)) {
                Station neighbourStation = stationConnection.getTargetStation(currentStation);
                if (!visited.contains(neighbourStation)) {
                    path.put(neighbourStation, currentStation);
                    visited.add(neighbourStation);
                    stationsQueue.add(neighbourStation);
                }
            }
        }

        if (pathFound) {
            List<Connection> resultPath = new ArrayList<>();
            Station pathStation1 = targetStation;
            Station pathStation2 = path.get(targetStation);
            while (pathStation2 != null) {
                resultPath.add(this.findConnectionFromTo(pathStation1, pathStation2));
                pathStation1 = pathStation2;
                pathStation2 = path.get(pathStation1);
            }

            Collections.reverse(resultPath);

            return resultPath;
        } else {
            throw new PathNotFoundException();
        }
    }

    private Connection findConnectionFromTo(Station from, Station to) {
        return this.stationsConnections.
                get(from)
                .stream()
                .filter(connection -> connection.getTargetStation(from).equals(to))
                .findFirst()
                .orElse(null);
    }

    public Station searchStationByCode(String code) throws StationNotFoundException {
        Set<Station> stationsSet = this.stationsConnections.keySet();

        for (Station station : stationsSet) {
            if (station.getCode().equals(code)) {
                return station;
            }
        }

        throw new StationNotFoundException("Nie znaleziono stacji");
    }
}

