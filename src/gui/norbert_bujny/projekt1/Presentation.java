package gui.norbert_bujny.projekt1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Presentation {
    public static void main(String[] args) {
        App appInstance = App.getInstance();
        appInstance.initialize(false);

        /**
         * Wagony
         */
        System.out.println("*** Wagony ***\n");

        CarsCommandsReceiver carsCommandsReceiver = (CarsCommandsReceiver) appInstance.getCommandsReceiverMap().get("cars");
        CarsCollection carsCollection = appInstance.getCarsCollection();

        /**
         * dodawanie wagonu
         */

        HeavyFreightCar heavyFreightCar = new HeavyFreightCar(2000, 8000, WayToLoadCargo.TOP_LOAD, "Drewno", new HashSet<>(Arrays.asList(CargoProtection.SAFETY_ROPES, CargoProtection.SAFETY_NET)));
        carsCollection.addItem(heavyFreightCar);

        /**
         * wylistowanie wagonów
         */

        System.out.println("\nWylistowanie wagonu po dodaniu nowego wagonu:\n");
        carsCommandsReceiver.printCarsList();

        /**
         * kopiowanie wagonu
         */

        BaseCar newHeavyFreightCar = carsCommandsReceiver.runCarCopier(heavyFreightCar);

        System.out.println("\n\nWylistowanie wagonu po skopiowaniu wagonu:\n");
        carsCommandsReceiver.printCarsList();

        /**
         * Usuwanie wagonu
         */

        carsCommandsReceiver.runCarDelete(newHeavyFreightCar);

        System.out.println("\n\nWylistowanie wagonu po usunięciu wagonu:\n");
        carsCommandsReceiver.printCarsList();

        /**
         * Stacje
         */

        System.out.println("\n\n*** Stacje ***\n");

        StationsCommandsReceiver stationsCommandsReceiver = (StationsCommandsReceiver) appInstance.getCommandsReceiverMap().get("stations");
        StationsGraph stationsGraph = appInstance.getStationsMap();

        /**
         * Dodawanie stacji
         */

        stationsCommandsReceiver.runCreateStation("Poznań", "POZ");
        stationsCommandsReceiver.runCreateStation("Warszawa Modlin", "WMI");

        /**
         * Dodawanie stacji z połączeniami
         */

        stationsCommandsReceiver.runCreateStation("Warszawa Centralna", "WAW", new ArrayList<>(Arrays.asList("POZ", "WMI")));

        /**
         * Wylistowanie stacji
         */

        System.out.println("\nWylistowanie stacji po dodaniu trzech stacji");
        stationsCommandsReceiver.printStationsList();

        /**
         * Usuwanie stacji
         */

        stationsCommandsReceiver.runDeleteStation("WMI");

        System.out.println("\nWylistowanie stacji po usunięciu jednej stacji");
        stationsCommandsReceiver.printStationsList();

        /**
         * Dodawanie większej ilości stacji bez połączeń
         */

        stationsCommandsReceiver.runCreateStation("Warszawa Modlin", "WMI");
        stationsCommandsReceiver.runCreateStation("Luboń", "LUB");
        stationsCommandsReceiver.runCreateStation("Nowy Tomyśl", "NWT");
        stationsCommandsReceiver.runCreateStation("Września", "WRZ");

        /**
         * Połączenia stacji
         */

        System.out.println("\n\n*** Połączenia stacji ***\n");

        ConnectionsCommandsReceiver connectionsCommandsReceiver = (ConnectionsCommandsReceiver) appInstance.getCommandsReceiverMap().get("connections");

        /**
         * Dodawanie pojedynczego połączenia
         */
        connectionsCommandsReceiver.runConnectionCreator("WMI", "WAW");

        /**
         * Dodawanie kilku połączeń
         */

        connectionsCommandsReceiver.runMultipleConnectionsCreator("LUB", Arrays.asList("POZ", "NWT"));

        /**
         * Dodawanie kilku połączeń podając stacje startowe (działa tak samo jak zwykłe dodawanie kilku połączeń)
         */

        connectionsCommandsReceiver.runConnectingToMultipleSourceStations("WRZ", Arrays.asList("POZ", "LUB"));

        /**
         * Wylistowanie stacji po zmianach
         */

        System.out.println("\nStacje po dodaniu nowych stacji i połączeń\n");
        stationsCommandsReceiver.printStationsList();

        /**
         * Znajdowanie trasy od stacji do stacji
         */

        System.out.println("\nTrasa: Warszawa Modlin - Września");
        connectionsCommandsReceiver.runPrintPath("WMI", "WRZ");

        /**
         * Pociągi
         */

        System.out.println("\n\n*** Pociągi ***\n");

        TrainsCommandsReceiver trainsCommandsReceiver = (TrainsCommandsReceiver) appInstance.getCommandsReceiverMap().get("trains");
        TrainsCollection trainsCollection = appInstance.getTrainsCollection();

        /**
         * Dodawanie pociągu
         */

        Train intercityBatory = null;
        try {
            intercityBatory = new Train(
                    stationsGraph.searchStationByCode("POZ"),
                    stationsGraph.searchStationByCode("WMI"),
                    "InterCity Batory",
                    225000.25,
                    15,
                    5
            );

            trainsCommandsReceiver.addTrainToCollections(intercityBatory);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        /**
         * Wylistowanie pociągów
         */

        trainsCommandsReceiver.printTrainsList();

        /**
         *  Przyczepianie wagonu
         */

        PassengerCar passengerCar = new PassengerCar(0, 3000, true, false, 50, PassengerCarClass.SECOND);
        BuffetCar buffetCar = new BuffetCar(2000, 4500, CuisineType.INTERNATIONAL, false, true);
        RefrigeratorCar refrigeratorCar = new RefrigeratorCar(1300, 6000, WayToLoadCargo.SIDE_LOAD, "Lody", true, -20);
        FreightCar freightCar = new FreightCar(1200, 4600, WayToLoadCargo.TOP_LOAD, "Części samochodowe");
        PostCar postCar = new PostCar(900, 3000, 20, new HashSet<>(Arrays.asList(ParcelsTypes.ENVELOPE, ParcelsTypes.MEDIUM, ParcelsTypes.SMALL)));
        LuggagePostCar luggagePostCar = new LuggagePostCar(1100, 3200, 40, new HashSet<>(Arrays.asList(ParcelsTypes.LARGE)), new HashSet<>(Arrays.asList(LuggageTypes.BIKE, LuggageTypes.FURNITURE)));
        GasCar gasCar = new GasCar(700, 1000, WayToLoadCargo.TOP_LOAD, "LPG", 100.20, true);
        FluidsCar fluidsCar = new FluidsCar(700, 3000, WayToLoadCargo.SIDE_LOAD, "Woda", 50.12, true);
        ExplosivesCar explosivesCar = new ExplosivesCar(800, 3100, WayToLoadCargo.SIDE_LOAD, "TNT", new HashSet<>(Arrays.asList(CargoProtection.SAFETY_ROPES)), PotentialExplosionPower.MEDIUM);
        ToxicCargoCar toxicCargoCar = new ToxicCargoCar(700, 3000, WayToLoadCargo.TOP_LOAD, "Grzyby", new HashSet<>(), PollutionLevel.LOW, "Spalanie");
        FluidToxicCargoCar fluidToxicCargoCar = new FluidToxicCargoCar(700, 3200, WayToLoadCargo.TOP_LOAD, "Ropa", new HashSet<>(Arrays.asList(CargoProtection.SAFETY_NET)), PollutionLevel.HIGH, "Należy dostarczyć na wylewisko", 200, true);

        carsCollection.addItem(passengerCar);
        carsCollection.addItem(buffetCar);
        carsCollection.addItem(refrigeratorCar);
        carsCollection.addItem(freightCar);
        carsCollection.addItem(postCar);
        carsCollection.addItem(luggagePostCar);
        carsCollection.addItem(gasCar);
        carsCollection.addItem(fluidsCar);
        carsCollection.addItem(explosivesCar);
        carsCollection.addItem(toxicCargoCar);
        carsCollection.addItem(fluidToxicCargoCar);

        if (intercityBatory != null) {
            trainsCommandsReceiver.runAttachCar(intercityBatory, passengerCar);
            trainsCommandsReceiver.runAttachCar(intercityBatory, buffetCar);
            trainsCommandsReceiver.runAttachCar(intercityBatory, refrigeratorCar);
            trainsCommandsReceiver.runAttachCar(intercityBatory, freightCar);

            // Próba podłączenia tego samego wagonu dwa razy
            trainsCommandsReceiver.runAttachCar(intercityBatory, freightCar);
        }

        System.out.println("\n\nLista pociągów po przyłączeniu wagonów\n");
        trainsCommandsReceiver.printTrainsList();

        /**
         *  Odczepienie wagonu
         */

        if (intercityBatory != null) {
            trainsCommandsReceiver.runDetachCar(intercityBatory, buffetCar);
            trainsCommandsReceiver.runDetachCar(intercityBatory, refrigeratorCar);
        }

        System.out.println("\n\nLista pociągów po odłączeniu wagonów\n");
        trainsCommandsReceiver.printTrainsList();

        /**
         * Wyjątki przy dodawaniu wagonów
         */

        System.out.println("\n\nWyjątki przy dodawaniu wagonów\n");

        Train verySmallTrain = null;
        try {
            verySmallTrain = new Train(
                    stationsGraph.searchStationByCode("WMI"),
                    stationsGraph.searchStationByCode("POZ"),
                    "Mały Pociąg",
                    8000,
                    2,
                    1
            );

            trainsCommandsReceiver.addTrainToCollections(verySmallTrain);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        System.out.println();

        if (verySmallTrain != null) {
            try {
                // ze względu na wagę
                verySmallTrain.attachCar(buffetCar);
                verySmallTrain.attachCar(heavyFreightCar.clone());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {
                // ze względu na ilość wagonów wymagających podłączenia do sieci elektrycznej
                verySmallTrain.detachCar(buffetCar);
                verySmallTrain.attachCar(passengerCar);
                verySmallTrain.attachCar(passengerCar.clone());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            try {
                // ze względu na ilość wagonów
                verySmallTrain.attachCar(freightCar.clone());
                verySmallTrain.attachCar(freightCar.clone());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        /**
         * usuwanie pociągu
         */

        trainsCommandsReceiver.runDeleteTrain(verySmallTrain);

        System.out.println("\n\nLista pociągów po usunięciu pociagu\n");
        trainsCommandsReceiver.printTrainsList();

        /**
         * Pobieranie raportu o pociągu
         */

        System.out.println("Raport o pociągu (warto zwrócić uwagę na wagę netto wagonów): \n");
        trainsCommandsReceiver.runPrintTrainReport(intercityBatory);

        /**
         * Ładowanie ładunku / passażerów
         */

        passengerCar.loadCargo(15);
        freightCar.loadCargo(1000);

        System.out.println("Raport o pociągu po załadowaniu towarów: \n");
        trainsCommandsReceiver.runPrintTrainReport(intercityBatory);

        /**
         * Wyładowanie ładunku / passażerów
         */

        passengerCar.unloadCargo(5);
        freightCar.unloadCargo(500);

        System.out.println("Raport o pociągu po wyładowaniu towarów: \n");
        trainsCommandsReceiver.runPrintTrainReport(intercityBatory);

        /**
         * Uruchomienie pociągu
         */

        trainsCommandsReceiver.runTrain(intercityBatory);

        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("Raport o pociągu po uruchomieniu go:  \n");
        trainsCommandsReceiver.runPrintTrainReport(intercityBatory);

        /**
         * Wyjątek RailroadHazard
         */

        Train veryFastTrain = null;
        try {
            veryFastTrain = new Train(
                    stationsGraph.searchStationByCode("WMI"),
                    stationsGraph.searchStationByCode("POZ"),
                    "Bardzo szybki pociąg",
                    8000,
                    2,
                    1
            );

            trainsCommandsReceiver.addTrainToCollections(veryFastTrain);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        veryFastTrain.setSpeed(210);
        trainsCommandsReceiver.runTrain(veryFastTrain);

        appInstance.run();
    }
}
