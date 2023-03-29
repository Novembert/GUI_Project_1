package gui.norbert_bujny.projekt1;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class MapWrapper<T extends IdRepresentedItem> {
    protected Map<String, T> map;

    public MapWrapper() {
        this.map = new HashMap<>();
    }

    public void addItem(T item) {
        this.map.put(item.getID(), item);
    }

    public String getItemsList() {
        return this.map.values().stream().map(c -> c.toString()).collect(Collectors.joining("\n===\n"));
    }

    public T getItem(String ID) {
        return this.map.get(ID);
    }

    public void deleteItem(String ID) {
        this.map.remove(ID);
    }

    public void setMap(Map<String, T> map) {
        this.map = map;
    }

    public Map<String, T> getMap() {
        return this.map;
    }
}