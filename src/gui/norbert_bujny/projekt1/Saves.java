package gui.norbert_bujny.projekt1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Saves {
    private Map<String, File> saves;
    private final File rootSavesDirectory;

    public Saves() {
        this.rootSavesDirectory = new File("saves");

        if (!this.rootSavesDirectory.exists()) {
            this.rootSavesDirectory.mkdir();
            this.saves = new HashMap<>();
        } else {
            this.saves = this.findExistingSaves(this.rootSavesDirectory);
        }
    }

    public Boolean findIfSaveExists(String saveName) {
        return saves.keySet().contains(saveName);
    }

    public void createNewSave(String saveName, StationsGraph stations, CarsCollection cars) {
        File saveDirectory = new File(this.rootSavesDirectory + "/" + saveName);
        if (saveDirectory.mkdir()) {
            try {
                FileOutputStream fos = new FileOutputStream(this.rootSavesDirectory + "/" + saveName + "/stations");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(stations);

//                  TODO
//                fos = new FileOutputStream(this.rootSavesDirectory + "/" + saveName + "/cars");
//                oos = new ObjectOutputStream(fos);
//                oos.writeObject(cars);
            } catch (Exception e) {
                System.out.println("Nie udało się zapisać stanu aplikacji.");
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Nie udało się zapisać stanu aplikacji.");
        }
    }

    public void readSave(String saveName) {
//        TODO odczyt
    }

    private Map<String, File> findExistingSaves(File rootDirectory) {
        File[] savesDirectories = rootDirectory.listFiles();

        Map<String, File> foundSaves = new HashMap<>();

        for (File file : savesDirectories) {
            if (file.isDirectory()) {
                foundSaves.put(file.getName(), file);
            }
        }

        return foundSaves;
    }

    public List<String> listExistingSaves() {
        return this.saves.keySet().stream().toList();
    }
}
