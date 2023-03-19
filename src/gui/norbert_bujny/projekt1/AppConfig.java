package gui.norbert_bujny.projekt1;

import java.util.*;

public class AppConfig {
    private Map<String, ModuleConfig> configs = new HashMap<>();

    public void readConfig () {
        Map<String, String> fileToFieldMap = new HashMap<>() {{
            put("resources/map.txt", "map");
        }};

        for ( String file : fileToFieldMap.keySet() ) {
            this.configs.put(fileToFieldMap.get(file), new ModuleConfig(file));
        }
    }

    public ModuleConfig findModuleConfig (String key) {
        return this.configs.get(key);
    }
}
