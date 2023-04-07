package gui.norbert_bujny.projekt1;

public class TrainsCollection extends MapWrapper<Train> {
    private TrainsReportGenerator trainsReportGenerator;
    TrainsCollection() {
        super();
        this.trainsReportGenerator = new TrainsReportGenerator(this);
    }
}
