package temesgen.girmay.microservice.masterdata.exception;

public class FlightScheduleNotFoundException  extends RuntimeException {

    public FlightScheduleNotFoundException(String message) {
        super(message);
    }
}
