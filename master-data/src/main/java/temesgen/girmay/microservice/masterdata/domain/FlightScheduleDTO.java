package temesgen.girmay.microservice.masterdata.domain;

import java.time.LocalTime;

public class FlightScheduleDTO {

    private final String flightNumber;
    private final String departureAirportCode;
    private final String arrivalAirportCode;
    private final LocalTime departureTime;
    private final LocalTime arrivalTime;

    public FlightScheduleDTO(final String flightNumber, final String departureAirport,
                             final String arrivalAirport, final LocalTime departureTime,
                             final LocalTime arrivalTime) {
        this.flightNumber = flightNumber;
        this.departureAirportCode = departureAirport;
        this.arrivalAirportCode = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getDepartureAirportCode() {
        return departureAirportCode;
    }

    public String getArrivalAirportCode() {
        return arrivalAirportCode;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
}
