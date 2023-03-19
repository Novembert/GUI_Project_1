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

        this.stationsMap.addConnection(code, targetCode);
    }

    public void initializeMultipleConnectionsCreator() {
        String code = Utilities.handleUserRequiredInput("Podaj kod stacji startowej: ");
        List<String> targetCodes = Utilities.handleUserRequiredListInput("Podaj kody stacji docelowych (oddzielone przecinkiem): ");

        this.stationsMap.addMultipleConnections(code, targetCodes);
    }

    public void initializeConnectingToMultipleSourceStations() {
        String targetCode = Utilities.handleUserRequiredInput("Podaj kod stacji docelowej: ");
        List<String> codes = Utilities.handleUserRequiredListInput("Podaj kody stacji startowych (oddzielone przecinkiem): ");

        for (String code : codes) {
            this.stationsMap.addConnection(code, targetCode);
        }
    }

    public void printPath() {
        String code = Utilities.handleUserRequiredInput("Podaj kod stacji startowej: ");
        String targetCode = Utilities.handleUserRequiredInput("Podaj kod stacji docelowej: ");

        System.out.println(this.stationsMap.findPath(code, targetCode));
    }
}