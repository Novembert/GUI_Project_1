package gui.norbert_bujny.projekt1;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class StationsGraph {
    private Map<Station, List<Station>> stationsConnections;

    StationsGraph() {
        this.stationsConnections = new HashMap<>();
    }

    public void setStationsConnections(Map<Station, List<Station>> stationsConnections) {
        this.stationsConnections = stationsConnections;
    }

    public Map<Station, List<Station>> getStationsConnections() {
        return this.stationsConnections;
    }

    public String getStationsList() {
        return this.stationsConnections.keySet().stream().map(s -> s.toString() + " || Połączenia -> " + this.stationsConnections.get(s)).collect(Collectors.joining("\n"));
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
    public List<Station> findPath(String code, String targetStationCode) throws StationNotFoundException, PathNotFoundException {

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
            for (Station neighbourStation : this.stationsConnections.get(currentStation)) {
                if (!visited.contains(neighbourStation)) {
                    path.put(neighbourStation, currentStation);
                    visited.add(neighbourStation);
                    stationsQueue.add(neighbourStation);
                }
            }
        }

        if (pathFound) {
            List<Station> resultPath = new ArrayList<>();
            Station pathStation = targetStation;
            while (pathStation != null) {
                resultPath.add(pathStation);
                pathStation = path.get(pathStation);
            }


            Collections.reverse(resultPath);
            resultPath.remove(0);

            return resultPath;
        } else {
            throw new PathNotFoundException();
        }
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

