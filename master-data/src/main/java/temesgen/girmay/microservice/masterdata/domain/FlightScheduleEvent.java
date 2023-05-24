package temesgen.girmay.microservice.masterdata.domain;

public class FlightScheduleEvent {

    private String operation;
    FlightSchedule flightSchedule;

    public FlightScheduleEvent(String operation, FlightSchedule flightSchedule) {
        this.operation = operation;
        this.flightSchedule = flightSchedule;
    }
}
