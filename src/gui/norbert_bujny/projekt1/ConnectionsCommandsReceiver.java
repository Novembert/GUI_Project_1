package gui.norbert_bujny.projekt1;

import java.util.List;

public class ConnectionsCommandsReceiver extends MenuCommandsReceiver {
    private StationsGraph stationsMap;

    public ConnectionsCommandsReceiver(App appReference) {
        super(appReference);
        this.stationsMap = appReference.getStationsMap();
    }

    public void initializeConnectionCreator() {
        String code = Utilities.handleUserRequiredInput("Podaj kod stacji startowej: ");
        String targetCode = Utilities.handleUserRequiredInput("Podaj kod stacji docelowej: ");

        this.runConnectionCreator(code, targetCode);
    }

    public void runConnectionCreator(String code, String targetCode) {
        try {
            this.stationsMap.addConnection(code, targetCode);
            System.out.println("Dodano 1 połączenie");
        } catch (StationNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void initializeMultipleConnectionsCreator() {
        String code = Utilities.handleUserRequiredInput("Podaj kod stacji startowej: ");
        List<String> targetCodes = Utilities.handleUserRequiredListInput("Podaj kody stacji docelowych (oddzielone przecinkiem): ");

        this.runMultipleConnectionsCreator(code, targetCodes);
    }

    public void runMultipleConnectionsCreator(String code, List<String> targetCodes) {
        int count = 0;

        try {
            this.stationsMap.addMultipleConnections(code, targetCodes);
            count++;
        } catch (StationNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Dodano " + count + " " + Utilities.getCorrectSingularOrPluralForm(count, "połączenie", "połączenia", "połączeń"));
    }

    public void initializeConnectingToMultipleSourceStations() {
        String targetCode = Utilities.handleUserRequiredInput("Podaj kod stacji docelowej: ");
        List<String> codes = Utilities.handleUserRequiredListInput("Podaj kody stacji startowych (oddzielone przecinkiem): ");

        this.runConnectingToMultipleSourceStations(targetCode, codes);
    }

    public void runConnectingToMultipleSourceStations(String targetCode, List<String> codes) {
        int count = 0;

        try {
            for (String code : codes) {
                this.stationsMap.addConnection(code, targetCode);
                count++;
            }
        } catch (StationNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Dodano " + count + " " + Utilities.getCorrectSingularOrPluralForm(count, "połączenie", "połączenia", "połączeń"));
    }

    public void initializePrintPath() {
        String code = Utilities.handleUserRequiredInput("Podaj kod stacji startowej: ");
        String targetCode = Utilities.handleUserRequiredInput("Podaj kod stacji docelowej: ");

        this.runPrintPath(code, targetCode);
    }

    public void runPrintPath(String code, String targetCode) {
        try {
            List<Connection> path = stationsMap.findPath(code, targetCode);
            String pathString = "";
            Station currentStation = this.stationsMap.searchStationByCode(code);
            for (Connection connection : path) {
                pathString = pathString + connection.toDirectionalString(currentStation) + ", ";
                currentStation = connection.getTargetStation(currentStation);
            }
            System.out.println(pathString);
        } catch (StationNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (PathNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}