package gui.norbert_bujny.projekt1;

import java.util.*;

public class Initializer {
    public static void initialize(App app) {
        /**
         * Stacje
         */
        StationsGraph stationsMap = app.getStationsMap();

        stationsMap.addStationToList(new Station("Poznań główny", "POZ"), Arrays.asList("KRZ", "PDZ"));
        stationsMap.addStationToList(new Station("Złotniki", "ZŁT"), Arrays.asList("POZ", "WRG"));
        stationsMap.addStationToList(new Station("Wargowo", "WRG"), Arrays.asList("ZŁT", "OBK"));
        stationsMap.addStationToList(new Station("Oborniki", "OBK"), Arrays.asList("WRG", "CHD"));
        stationsMap.addStationToList(new Station("Chodzież", "CHD"), Arrays.asList("OBK", "PŁG"));
        stationsMap.addStationToList(new Station("Kiekrz", "KRZ"), Arrays.asList("POZ", "ROK"));
        stationsMap.addStationToList(new Station("Rokietnica", "ROK"), Arrays.asList("KRZ", "SZO"));
        stationsMap.addStationToList(new Station("Szamotuły", "SZO"), Arrays.asList("ROK", "WRO"));
        stationsMap.addStationToList(new Station("Wronki", "WRO"), Arrays.asList("SZO", "MŁY"));
        stationsMap.addStationToList(new Station("Miały", "MŁY"), Arrays.asList("WRO", "DWM"));
        stationsMap.addStationToList(new Station("Drawski Młyn", "DWM"), Arrays.asList("KRŻ", "MŁY", "WIE"));
        stationsMap.addStationToList(new Station("Krzyż", "KRŻ"), Arrays.asList("DWM", "POD", "NWD"));
        stationsMap.addStationToList(new Station("Podlesiec", "POD"), Arrays.asList("KRŻ", "DGW"));
        stationsMap.addStationToList(new Station("Dobiegniew", "DGW"), Arrays.asList("POD"));
        stationsMap.addStationToList(new Station("Nowe Drezdenko", "NWD"), Arrays.asList("KRŻ"));
        stationsMap.addStationToList(new Station("Wieleń", "WIE"), Arrays.asList("DWM", "BIE"));
        stationsMap.addStationToList(new Station("Biernatowo", "BIE"), Arrays.asList("WIE", "TRZ"));
        stationsMap.addStationToList(new Station("Trzcianka", "TRZ"), Arrays.asList("BIE"));
        stationsMap.addStationToList(new Station("Piła główna", "PŁG"), Arrays.asList("TRZ", "CHD"));
        stationsMap.addStationToList(new Station("Międzychód", "MDZ"), Arrays.asList("SZO", "ZBĄ"));
        stationsMap.addStationToList(new Station("Zbąszyń", "ZBĄ"), Arrays.asList("MDZ", "ZBN"));
        stationsMap.addStationToList(new Station("Zbąszynek", "ZBN"), Arrays.asList("ZBĄ", "NWT"));
        stationsMap.addStationToList(new Station("Nowy Tomyśł", "NWT"), Arrays.asList("ZBN", "BUK"));
        stationsMap.addStationToList(new Station("Buk", "BUK"), Arrays.asList("NWT", "PDZ"));
        stationsMap.addStationToList(new Station("Palędzie", "PDZ"), Arrays.asList("BUK", "POZ"));
        stationsMap.addStationToList(new Station("Wierzbno", "WBN"), Arrays.asList("MDZ"));
        stationsMap.addStationToList(new Station("Lutol Suchy", "LUT"), Arrays.asList("ZBŃ"));
        stationsMap.addStationToList(new Station("Międzyrzecz", "MRZ"), Arrays.asList("LUT", "WBN"));
        stationsMap.addStationToList(new Station("Skwierzyna", "SKW"), Arrays.asList("WBN", "MRZ"));
        stationsMap.addStationToList(new Station("Górki Noteckie", "GNK"), Arrays.asList("NWD"));
        stationsMap.addStationToList(new Station("Gorzów Wielkopolski", "GWI"), Arrays.asList("SKW", "GNK"));
        stationsMap.addStationToList(new Station("Luboń", "LUB"), Arrays.asList("POZ"));
        stationsMap.addStationToList(new Station("Stęszew", "STZ"), Arrays.asList("LUB"));
        stationsMap.addStationToList(new Station("Granowo", "GRN"), Arrays.asList("STZ"));
        stationsMap.addStationToList(new Station("Grodzisk Wielkopolski", "GRW"), Arrays.asList("GRN"));
        stationsMap.addStationToList(new Station("Rakoniewice", "RAK"), Arrays.asList("GRW"));
        stationsMap.addStationToList(new Station("Wolsztyn", "WOL"), Arrays.asList("RAK"));
        stationsMap.addStationToList(new Station("Stefanowo", "STF"), Arrays.asList("WOL", "ZBĄ"));
        stationsMap.addStationToList(new Station("Nowawieś Mochy", "NMO"), Arrays.asList("WOL"));
        stationsMap.addStationToList(new Station("Włoszakowice", "WŁO"), Arrays.asList("NMO"));
        stationsMap.addStationToList(new Station("Leszno", "LSZ"), Arrays.asList("WŁO"));
        stationsMap.addStationToList(new Station("Stare Bojanowo", "SBO"), Arrays.asList("LSZ"));
        stationsMap.addStationToList(new Station("Kościan", "KOŚ"), Arrays.asList("SBO"));
        stationsMap.addStationToList(new Station("Czempiń", "CZE"), Arrays.asList("KOŚ"));
        stationsMap.addStationToList(new Station("Mosina", "MOS"), Arrays.asList("CZE", "LUB"));
        stationsMap.addStationToList(new Station("Palędzie", "PDZ"), Arrays.asList("BUK", "POZ"));
        stationsMap.addStationToList(new Station("Kąkolewo", "KĄK"), Arrays.asList("LSZ"));
        stationsMap.addStationToList(new Station("Gostyń", "GOS"), Arrays.asList("KĄK"));
        stationsMap.addStationToList(new Station("Jarocin", "JAR"), Arrays.asList("GOS"));
        stationsMap.addStationToList(new Station("Poznań Krzesiny", "POK"), Arrays.asList("LUB"));
        stationsMap.addStationToList(new Station("Gądki", "GDK"), Arrays.asList("POK"));
        stationsMap.addStationToList(new Station("Środa Wielkopolska", "ŚWL"), Arrays.asList("GDK"));
        stationsMap.addStationToList(new Station("Sulęcinek", "SUL"), Arrays.asList("ŚWL"));
        stationsMap.addStationToList(new Station("Mieszków", "MIS"), Arrays.asList("SUL", "JAR"));
        stationsMap.addStationToList(new Station("Swarzędz", "SWA"), Arrays.asList("POK", "POZ"));
        stationsMap.addStationToList(new Station("Kostrzyn Wielkopolski", "KOS"), Arrays.asList("SWA"));
        stationsMap.addStationToList(new Station("Podstolice", "PDS"), Arrays.asList("KOS"));
        stationsMap.addStationToList(new Station("Września", "WRZ"), Arrays.asList("PDS"));
        stationsMap.addStationToList(new Station("Miłosław", "MIŁ"), Arrays.asList("WRZ"));
        stationsMap.addStationToList(new Station("Orzechowo", "ORZ"), Arrays.asList("MIŁ"));
        stationsMap.addStationToList(new Station("Żerków", "ŻER"), Arrays.asList("ORZ", "JAR"));
        stationsMap.addStationToList(new Station("Kobylnica", "KOB"), Arrays.asList("SWA", "POZ"));
        stationsMap.addStationToList(new Station("Pobiedziska", "POB"), Arrays.asList("KOB"));
        stationsMap.addStationToList(new Station("Pierzyska", "PIE"), Arrays.asList("POB"));
        stationsMap.addStationToList(new Station("Czerniejewo", "CZR"), Arrays.asList("WRZ"));
        stationsMap.addStationToList(new Station("Gniezno", "GNI"), Arrays.asList("PIE", "CZR"));
        stationsMap.addStationToList(new Station("Koziegłowy", "KOZ"), Arrays.asList("POZ", "KOB"));
        stationsMap.addStationToList(new Station("Bolechowo", "BOL"), Arrays.asList("KOZ"));
        stationsMap.addStationToList(new Station("Murowana Goślina", "MUG"), Arrays.asList("BOL"));
        stationsMap.addStationToList(new Station("Sława Wielkopolska", "SŁW"), Arrays.asList("MUG"));
        stationsMap.addStationToList(new Station("Stawiany", "STA"), Arrays.asList("SŁW", "GNI"));
        stationsMap.addStationToList(new Station("Wągrowiec", "WĄG"), Arrays.asList("SŁW", "CHD", "OBK"));
        stationsMap.addStationToList(new Station("Kcynia", "KCY"), Arrays.asList("WĄG"));
        stationsMap.addStationToList(new Station("Nakło nad Notecią", "NNN"), Arrays.asList("KCY"));
        stationsMap.addStationToList(new Station("Wrzysk Osiek", "WOS"), Arrays.asList("NNN"));
        stationsMap.addStationToList(new Station("Białośliwie", "BIA"), Arrays.asList("WOS"));
        stationsMap.addStationToList(new Station("Kaczory", "KAC"), Arrays.asList("BIA", "PŁG"));
        stationsMap.addStationToList(new Station("Damasławek", "DAM"), Arrays.asList("KCY"));
        stationsMap.addStationToList(new Station("Janowiec Wielkopolski", "JAN"), Arrays.asList("DAM", "GNI"));
        stationsMap.addStationToList(new Station("Strzałkowo", "STR"), Arrays.asList("WRZ"));
        stationsMap.addStationToList(new Station("Konin", "KON"), Arrays.asList("STR"));
        stationsMap.addStationToList(new Station("Kramsk", "KRS"), Arrays.asList("KON"));
        stationsMap.addStationToList(new Station("Koło", "KOŁ"), Arrays.asList("KRS"));
        stationsMap.addStationToList(new Station("Barłogi", "BAR"), Arrays.asList("KOŁ"));
        stationsMap.addStationToList(new Station("Lipie Góry", "LIP"), Arrays.asList("BAR"));
        stationsMap.addStationToList(new Station("Babiak", "BAB"), Arrays.asList("LIP"));
        stationsMap.addStationToList(new Station("Zaryń", "ZAR"), Arrays.asList("BAB"));
        stationsMap.addStationToList(new Station("Piotrków Kujawski", "PIK"), Arrays.asList("ZAR"));
        stationsMap.addStationToList(new Station("Chełmce", "CHŁ"), Arrays.asList("PIK"));
        stationsMap.addStationToList(new Station("Karczyn", "KAR"), Arrays.asList("CHŁ"));
        stationsMap.addStationToList(new Station("Inowrocław", "INO"), Arrays.asList("KAR"));
        stationsMap.addStationToList(new Station("Trzemeszno", "TRE"), Arrays.asList("GNI"));
        stationsMap.addStationToList(new Station("Wydartowo", "WYD"), Arrays.asList("TRE"));
        stationsMap.addStationToList(new Station("Mogilno", "MOG"), Arrays.asList("WYD"));
        stationsMap.addStationToList(new Station("Kołodziejewo", "KDZ"), Arrays.asList("MOG"));
        stationsMap.addStationToList(new Station("Janikowo", "JWO"), Arrays.asList("KDZ", "INO"));
        stationsMap.addStationToList(new Station("Zielonczyn", "ZIE"), Arrays.asList("NNN"));
        stationsMap.addStationToList(new Station("Prady", "PRD"), Arrays.asList("ZIE"));
        stationsMap.addStationToList(new Station("Bydgoszcz Główna", "BYD"), Arrays.asList("PRD", "KCY"));
        stationsMap.addStationToList(new Station("Bydgoszcz Wschód", "BWS"), Arrays.asList("BYD"));


        /**
         * Wagony
         */
        List<BaseCar> premadeCarsPrototypes = new ArrayList<>();
        premadeCarsPrototypes.add(new PassengerCar(1000, 3000, true, false, 50, PassengerCarClass.SECOND));
        premadeCarsPrototypes.add(new PassengerCar(1300, 3200, true, true, 30, PassengerCarClass.FIRST));

        premadeCarsPrototypes.add(new BuffetCar(2000, 4500, CuisineType.INTERNATIONAL, false, true));
        premadeCarsPrototypes.add(new BuffetCar(2000, 4600, CuisineType.CHINESE, true, false));

        premadeCarsPrototypes.add(new RefrigeratorCar(1300, 6000, WayToLoadCargo.SIDE_LOAD, "Lody", true, -20));
        premadeCarsPrototypes.add(new RefrigeratorCar(1280, 5500, WayToLoadCargo.SIDE_LOAD, "Mrożone jedzenie", true, -10));

        premadeCarsPrototypes.add(new FreightCar(1200, 4600, WayToLoadCargo.TOP_LOAD, "Części samochodowe"));
        premadeCarsPrototypes.add(new FreightCar(1200, 4800, WayToLoadCargo.SIDE_LOAD, "Zwierzęta"));

        premadeCarsPrototypes.add(new HeavyFreightCar(2000, 8000, WayToLoadCargo.TOP_LOAD, "Węgiel", new HashSet<>(Arrays.asList(CargoProtection.SAFETY_NET))));
        premadeCarsPrototypes.add(new HeavyFreightCar(2300, 9000, WayToLoadCargo.SIDE_LOAD, "Słonie", new HashSet<>(Arrays.asList(CargoProtection.SECURITY_BARS))));

        premadeCarsPrototypes.add(new PostCar(900, 3000, 20, new HashSet<>(Arrays.asList(ParcelsTypes.ENVELOPE, ParcelsTypes.MEDIUM, ParcelsTypes.SMALL))));

        premadeCarsPrototypes.add(new LuggagePostCar(1100, 3200, 40, new HashSet<>(Arrays.asList(ParcelsTypes.LARGE)), new HashSet<>(Arrays.asList(LuggageTypes.BIKE, LuggageTypes.FURNITURE))));

        premadeCarsPrototypes.add(new GasCar(700, 1000, WayToLoadCargo.TOP_LOAD, "LPG", 100.20, true));

        premadeCarsPrototypes.add(new FluidsCar(700, 3000, WayToLoadCargo.SIDE_LOAD, "Woda", 50.12, true));
        premadeCarsPrototypes.add(new FluidsCar(700, 3000, WayToLoadCargo.SIDE_LOAD, "Mleko", 70.25, false));

        premadeCarsPrototypes.add(new ExplosivesCar(800, 3100, WayToLoadCargo.SIDE_LOAD, "TNT", new HashSet<>(Arrays.asList(CargoProtection.SAFETY_ROPES)), PotentialExplosionPower.MEDIUM));

        premadeCarsPrototypes.add(new ToxicCargoCar(700, 3000, WayToLoadCargo.TOP_LOAD, "Grzyby", new HashSet<>(), PollutionLevel.LOW, "Spalanie"));

        premadeCarsPrototypes.add(new FluidToxicCargoCar(700, 3200, WayToLoadCargo.TOP_LOAD, "Ropa", new HashSet<>(Arrays.asList(CargoProtection.SAFETY_NET)), PollutionLevel.HIGH, "Należy dostarczyć na wylewisko", 200, true));

        /**
         * Pociagi
         */

        TrainsCollection trainsCollection = app.getTrainsCollection();

        try {
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("POZ"), stationsMap.searchStationByCode("BYD"), "Polonia", 250000.00, 15, 5));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("GWI"), stationsMap.searchStationByCode("KRŻ"), "Krzyżak", 220000.00, 12, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("BUK"), stationsMap.searchStationByCode("OBK"), "Obornik", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("OBK"), stationsMap.searchStationByCode("BUK"), "Obornik II", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("ROK"), stationsMap.searchStationByCode("KOŁ"), "Rokitnik", 195000.75, 14, 7));

            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("JAR"), stationsMap.searchStationByCode("KOŚ"), "Mieszko", 250000.00, 15, 5));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("BAR"), stationsMap.searchStationByCode("WŁO"), "Chrobry", 220000.00, 12, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("MOG"), stationsMap.searchStationByCode("ZŁT"), "Łokietek", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("GRN"), stationsMap.searchStationByCode("LUB"), "Mieszko II", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("SŁW"), stationsMap.searchStationByCode("STF"), "Karol", 195000.75, 14, 7));

            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("BWS"), stationsMap.searchStationByCode("ORZ"), "Lubczyk", 250000.00, 15, 5));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("CHŁ"), stationsMap.searchStationByCode("WOL"), "Czosnek", 220000.00, 12, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("KOŁ"), stationsMap.searchStationByCode("SUL"), "Kurkuma", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("CZE"), stationsMap.searchStationByCode("STZ"), "Kminek", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("SKW"), stationsMap.searchStationByCode("KRZ"), "Szafran", 195000.75, 14, 7));

            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("GDK"), stationsMap.searchStationByCode("NWT"), "Hermes", 250000.00, 15, 5));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("KCY"), stationsMap.searchStationByCode("ORZ"), "Hefajstos", 220000.00, 12, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("RAK"), stationsMap.searchStationByCode("JAR"), "Posejdon", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("SZO"), stationsMap.searchStationByCode("KĄK"), "Zeus", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("WRO"), stationsMap.searchStationByCode("BIE"), "Prometeusz", 195000.75, 14, 7));

            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("ORZ"), stationsMap.searchStationByCode("WRO"), "Rakieta", 250000.00, 15, 5));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("ORZ"), stationsMap.searchStationByCode("BAR"), "Tesla", 220000.00, 12, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("KOŁ"), stationsMap.searchStationByCode("GWI"), "Ferrari", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("SUL"), stationsMap.searchStationByCode("MOG"), "Lamborghini", 225000.25, 15, 7));
            trainsCollection.addItem(new Train(stationsMap.searchStationByCode("BUK"), stationsMap.searchStationByCode("GWI"), "Bentley", 195000.75, 14, 7));

        } catch (Exception e) {
            System.out.println("Błąd w konfiguracji pociągów");
            System.out.println(e);
        }
        /**
         * Pociągi i wagony
         */
        CarsCollection carsCollection = app.getCarsCollection();

        for (Train t : trainsCollection.getMap().values()) {
            TrainCarsMap.getInstance().addTrain(t);
            Random random = new Random();
            int carsCount = random.nextInt(6) + 5;

            while (t.getCars().size() < carsCount) {
                int randomCar = random.nextInt(premadeCarsPrototypes.size());
                BaseCar newCar = premadeCarsPrototypes.get(randomCar).clone();
                carsCollection.addItem(newCar);
                try {
                    t.attachCar(newCar);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
