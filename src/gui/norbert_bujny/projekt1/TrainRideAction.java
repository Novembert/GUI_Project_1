package gui.norbert_bujny.projekt1;

import java.util.Random;
import java.util.concurrent.Callable;

public class TrainRideAction implements Callable<Boolean> {
    Train threadTrain;
    Connection threadUsedConnection;

    public TrainRideAction(Train t, Connection usedConnection) {
        this.threadTrain = t;
        this.threadUsedConnection = usedConnection;
    }

    @Override
    public Boolean call() {
        Random random = new Random();
        threadTrain.setTrainRideState(TrainRideState.RUNNING);

        Station targetStation = threadUsedConnection.getTargetStation(threadTrain.getCurrentStation());
        threadTrain.setCoveredCurrentPathDistance(0);
        threadTrain.setCurrentPathDistance(threadUsedConnection.getDistance());

        while (threadTrain.getCoveredCurrentPathDistance() < threadTrain.getCurrentPathDistance()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Błąd krytyczny");
                throw new RuntimeException(e);
            }

            double diff = threadTrain.getKMhToKMsMultiplier() * threadTrain.getSpeed();
            threadTrain.setCoveredCurrentPathDistance(threadTrain.getCoveredCurrentPathDistance() + diff);
            threadTrain.setCoveredWholeTravelDistance(threadTrain.getCoveredWholeTravelDistance() + diff);


            try {
                threadTrain.setSpeed(random.nextBoolean() ? threadTrain.getSpeed() - threadTrain.getSpeed() * 0.03 : threadTrain.getSpeed() + threadTrain.getSpeed() * 0.03);

                if (threadTrain.getSpeed() > 200) {
                    throw new RailroadHazard(threadTrain);
                }
            } catch (RailroadHazard rh) {
                System.out.println(rh.getMessage());
            }


        }

        threadTrain.setCurrentStation(targetStation);
        threadTrain.setSpeed(100);
        threadUsedConnection.setIsUsed(null);

        if (targetStation.equals(threadTrain.getTargetStation()) || targetStation.equals(threadTrain.getHomeStation())) {
            threadTrain.setTrainRideState(TrainRideState.ARRIVED_TO_FINAL_STATION);
        } else {
            threadTrain.setTrainRideState(TrainRideState.ARRIVED_TO_STATION);
        }
        return true;
    }
}
