package gui.norbert_bujny.projekt1;

import java.util.Random;

public class TrainRideAction extends Thread {
    Train threadTrain;
    Connection threadUsedConnection;

    public TrainRideAction(Train t, Connection usedConnection) {
        this.threadTrain = t;
        this.threadUsedConnection = usedConnection;
    }

    @Override
    public void run() {
        Random random = new Random();
        threadTrain.setTrainRideState(TrainRideState.RUNNING);

        Station targetStation = threadUsedConnection.getTargetStation(threadTrain.getCurrentStation());
        threadUsedConnection.setIsUsed(true);
        threadTrain.setCoveredCurrentPathDistance(0);
        threadTrain.setCurrentPathDistance(threadUsedConnection.getDistance());

        while (threadTrain.getCoveredCurrentPathDistance() < threadTrain.getCurrentPathDistance()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            double diff = threadTrain.getKMhToKMsMultiplier() * threadTrain.getSpeed();
            threadTrain.setCoveredCurrentPathDistance(threadTrain.getCoveredCurrentPathDistance() + diff);
            threadTrain.setCoveredWholeTravelDistance(threadTrain.getCoveredWholeTravelDistance() + diff);

            threadTrain.setSpeed(random.nextBoolean() ? threadTrain.getSpeed() - threadTrain.getSpeed() * 0.03 : threadTrain.getSpeed() + threadTrain.getSpeed() * 0.03);
        }

        threadTrain.setCurrentStation(targetStation);
        threadUsedConnection.setIsUsed(false);
        threadTrain.setSpeed(100);

        threadTrain.executeQueuedActions();
        if (targetStation.equals(threadTrain.getTargetStation()) || targetStation.equals(threadTrain.getHomeStation())) {
            threadTrain.setTrainRideState(TrainRideState.ARRIVED_TO_FINAL_STATION);
        } else {
            threadTrain.setTrainRideState(TrainRideState.ARRIVED_TO_STATION);
        }
    }
}
