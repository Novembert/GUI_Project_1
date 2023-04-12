package gui.norbert_bujny.projekt1;

import java.util.stream.Collectors;

public class CarsCollection extends MapWrapper<BaseCar> {

    CarsCollection() {
        super();
    }

    public BaseCar getNonAttachedCarWithPrompt(String prompt) throws ItemNotFoundException {
        BaseCar item = null;
        int retry = 0;
        int MAX_RETRY = 3;
        do {
            try {
                System.out.println(this.getNonAttachedCarsList());
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

    public Loadable getLoadableCarWithPrompt(String prompt) throws ItemNotFoundException {
        Loadable item = null;
        int retry = 0;
        int MAX_RETRY = 3;
        do {
            try {
                System.out.println(this.getLoadableCarsList());
                item = (Loadable) this.getItem(Utilities.handleUserRequiredInput(prompt));
            } catch (ItemNotFoundException e) {
                retry++;
                System.out.println(e.getMessage());
                Utilities.printRetryInformation(retry, MAX_RETRY);
            }
        } while ((item == null || !(item instanceof Loadable)) && retry < MAX_RETRY);

        if (item == null) {
            throw new ItemNotFoundException();
        }

        return item;
    }

    private String getNonAttachedCarsList() {
        return this.map
                .values()
                .stream()
                .filter(c -> !TrainCarsMap.getInstance().getAllCars().contains(c))
                .map(c -> c.toString())
                .collect(Collectors.joining("\n===\n"));
    }

    public BaseCar getAttachedCarWithPrompt(String prompt) throws ItemNotFoundException {
        BaseCar item = null;
        int retry = 0;
        int MAX_RETRY = 3;
        do {
            try {
                System.out.println(this.getAttachedCarsList());
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

    private String getAttachedCarsList() {
        return this.map
                .values()
                .stream()
                .filter(c -> TrainCarsMap.getInstance().getAllCars().contains(c))
                .map(c -> c.toString())
                .collect(Collectors.joining("\n===\n"));
    }

    private String getLoadableCarsList() {
        return this.map
                .values()
                .stream()
                .filter(c -> c instanceof Loadable)
                .map(c -> c.toString())
                .collect(Collectors.joining("\n===\n"));
    }
}
