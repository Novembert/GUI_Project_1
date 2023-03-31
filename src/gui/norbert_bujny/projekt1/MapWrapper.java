package gui.norbert_bujny.projekt1;

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

    public T getItem(String ID) throws ItemNotFoundException {
        T item = this.map.get(ID);
        if (item == null) {
            throw new ItemNotFoundException("Nie znaleziono nic o takim identyfikatorze");
        }
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

    public T getItemWithPrompt(String prompt) throws ItemNotFoundException {
        T item = null;
        int retry = 0;
        int MAX_RETRY = 3;
        do {
            try {
                System.out.println(this.getItemsList());
                item = this.getItem(Utilities.handleUserRequiredInput(prompt));
            } catch (ItemNotFoundException e) {
                retry++;
                System.out.println(e.getMessage());
                Utilities.printRetryInformation(retry, MAX_RETRY);
            }
        } while (item == null && retry < MAX_RETRY);

        if (item == null) {
            throw new ItemNotFoundException();
        }

        return item;
    }
}