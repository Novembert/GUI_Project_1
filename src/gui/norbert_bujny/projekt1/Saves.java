package gui.norbert_bujny.projekt1;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Saves {
    private Map<String, File> saves;
    private final File rootSavesDirectory;
    private App appInstance;

    public Saves() {
        this.rootSavesDirectory = new File("saves");
        this.appInstance = App.getInstance();

        if (!this.rootSavesDirectory.exists()) {
            this.rootSavesDirectory.mkdir();
            this.saves = new HashMap<>();
        } else {
            this.saves = this.findExistingSaves(this.rootSavesDirectory);
        }
    }

    public Boolean findIfSaveExists(String saveName) {
        return saves.containsKey(saveName);
    }

    public void createNewSave(String saveName, StationsGraph stations, CarsCollection cars) {
        File saveDirectory = new File(this.rootSavesDirectory + "/" + saveName);
        try {
            saveDirectory.mkdir();

            FileOutputStream fos = new FileOutputStream(saveDirectory.getPath() + "/stations");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(stations.getStationsConnections());

            fos = new FileOutputStream(saveDirectory.getPath() + "/cars");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(cars.getMap());

            oos.close();
            fos.close();

            this.saves.put(saveName, saveDirectory);
        } catch (Exception e) {
            System.out.println("Nie udało się zapisać stanu aplikacji. 1");
            throw new RuntimeException(e);
        }
    }

    public void overrideSave(String saveName, StationsGraph stations, CarsCollection cars) {
        File saveDirectory = new File(this.rootSavesDirectory + "/" + saveName);
        this.deleteDir(saveDirectory);
        this.createNewSave(saveName, stations, cars);
    }

    public void readSave(String saveName) {
        File saveDirectory = this.saves.get(saveName);
        if (saveDirectory.exists()) {
            try {
                FileInputStream fis = new FileInputStream(saveDirectory.getPath() + "/cars");
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.appInstance.getCarsCollection().setMap((Map<String, BaseCar>) ois.readObject());

                fis = new FileInputStream(saveDirectory.getPath() + "/stations");
                ois = new ObjectInputStream(fis);
                this.appInstance.getStationsMap().setStationsConnections((Map<Station, List<Station>>) ois.readObject());

                fis.close();
                ois.close();
            } catch (Exception e) {
                System.out.println("Nie udało się odczytać stanu aplikacji.");
                throw new RuntimeException(e);
            }
        }
    }

    private void deleteDir(File file) {
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                deleteDir(f);
            }
        }
        file.delete();
    }

    private Map<String, File> findExistingSaves(File rootDirectory) {
        File[] savesDirectories = rootDirectory.listFiles();

        Map<String, File> foundSaves = new HashMap<>();

        if (savesDirectories != null) {
            for (File file : savesDirectories) {
                if (file.isDirectory()) {
                    foundSaves.put(file.getName(), file);
                }
            }
        }

        return foundSaves;
    }

    public List<String> listExistingSaves() {
        return this.saves.keySet().stream().toList();
    }
}
