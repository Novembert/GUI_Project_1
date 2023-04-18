package gui.norbert_bujny.projekt1;

public class TrainWaitAndSwitchStateAction extends Thread {
    Train threadTrain;
    int threadDelayInMs;
    TrainRideState newStateToSet;


    public TrainWaitAndSwitchStateAction(Train t, int timeInMs, TrainRideState newState) {
        this.threadTrain = t;
        this.threadDelayInMs = timeInMs;
        this.newStateToSet = newState;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(this.threadDelayInMs);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        threadTrain.setTrainRideState(this.newStateToSet);
    }
}
