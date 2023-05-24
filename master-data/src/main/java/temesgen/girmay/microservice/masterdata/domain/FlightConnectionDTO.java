package temesgen.girmay.microservice.masterdata.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FlightConnectionDTO {
    private final String onwardFlighttNumber;
    private final String onwardDepartureAirport;
    private final String onwardArrivalAirport;
    private final String onwardDepartureTime;
    private final String onwardArrivalTime;
    private final String connectionFlightNumber;
    private final String connectionDepartureAirport;
    private final String connectionArrivalAirport;
    private final String connectionDepartureTime;
    private final String connArrivalTime;

    public FlightConnectionDTO(String onwardFlightNumber, String onwardDepartureAirport, String onwardArrivalAirport, LocalTime onwardDepartureTime, LocalTime onwardArrivalTime, String connectionFlightNumber, String connectionDepartureAirport, String connectionArrivalAirport, LocalTime connectionDepartureTime, LocalTime connArrivalTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.onwardFlighttNumber = onwardFlightNumber;
        this.onwardDepartureAirport = onwardDepartureAirport;
        this.onwardArrivalAirport = onwardArrivalAirport;
        this.onwardDepartureTime = onwardDepartureTime.format(formatter);
        this.onwardArrivalTime = onwardArrivalTime.format(formatter);
        this.connectionFlightNumber = connectionFlightNumber;
        this.connectionDepartureAirport = connectionDepartureAirport;
        this.connectionArrivalAirport = connectionArrivalAirport;
        this.connectionDepartureTime = connectionDepartureTime.format(formatter);
        this.connArrivalTime = connArrivalTime.format(formatter);
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    public String getOnwardFlighttNumber() {
        return onwardFlighttNumber;
    }

    public String getOnwardDepartureAirport() {
        return onwardDepartureAirport;
    }

    public String getOnwardArrivalAirport() {
        return onwardArrivalAirport;
    }

    public String getOnwardDepartureTime() {
        return onwardDepartureTime;
    }

    public String getOnwardArrivalTime() {
        return onwardArrivalTime;
    }

    public String getConnectionFlightNumber() {
        return connectionFlightNumber;
    }

    public String getConnectionDepartureAirport() {
        return connectionDepartureAirport;
    }

    public String getConnectionArrivalAirport() {
        return connectionArrivalAirport;
    }

    public String getConnectionDepartureTime() {
        return connectionDepartureTime;
    }

    public String getConnArrivalTime() {
        return connArrivalTime;
    }
}
