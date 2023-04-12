package gui.norbert_bujny.projekt1;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TrainsCollection extends MapWrapper<Train> {
    private TrainsReportGenerator trainsReportGenerator;

    TrainsCollection() {
        super();
        this.trainsReportGenerator = new TrainsReportGenerator(this);

        ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
        ScheduledFuture<?> schedule = executor.scheduleAtFixedRate(this.trainsReportGenerator, 5, 5, TimeUnit.SECONDS);
    }
}
