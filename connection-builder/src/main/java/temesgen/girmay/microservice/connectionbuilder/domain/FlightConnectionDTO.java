package temesgen.girmay.microservice.connectionbuilder.domain;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FlightConnectionDTO {
    private final String onwardFlightNumber;
    private final String onwardDepartureAirportCode;
    private final String onwardArrivalAirportCode;
    private final String onwardDepartureTime;
    private final String onwardArrivalTime;
    private final String connectionFlightNumber;
    private final String connectionDepartureAirportCode;
    private final String connectionArrivalAirportCode;
    private final String connectionDepartureTime;
    private final String connArrivalTime;

    public FlightConnectionDTO(String onwardFlightNumber, String onwardDepartureAirportCode, String onwardArrivalAirportCode, LocalTime onwardDepartureTime, LocalTime onwardArrivalTime, String connectionFlightNumber, String connectionDepartureAirportCode, String connectionArrivalAirportCode, LocalTime connectionDepartureTime, LocalTime connArrivalTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.onwardFlightNumber = onwardFlightNumber;
        this.onwardDepartureAirportCode = onwardDepartureAirportCode;
        this.onwardArrivalAirportCode = onwardArrivalAirportCode;
        this.onwardDepartureTime = onwardDepartureTime.format(formatter);
        this.onwardArrivalTime = onwardArrivalTime.format(formatter);
        this.connectionFlightNumber = connectionFlightNumber;
        this.connectionDepartureAirportCode = connectionDepartureAirportCode;
        this.connectionArrivalAirportCode = connectionArrivalAirportCode;
        this.connectionDepartureTime = connectionDepartureTime.format(formatter);
        this.connArrivalTime = connArrivalTime.format(formatter);
    }

    public String getOnwardFlightNumber() {
        return onwardFlightNumber;
    }

    public String getOnwardDepartureAirportCode() {
        return onwardDepartureAirportCode;
    }

    public String getOnwardArrivalAirportCode() {
        return onwardArrivalAirportCode;
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

    public String getConnectionDepartureAirportCode() {
        return connectionDepartureAirportCode;
    }

    public String getConnectionArrivalAirportCode() {
        return connectionArrivalAirportCode;
    }

    public String getConnectionDepartureTime() {
        return connectionDepartureTime;
    }

    public String getConnArrivalTime() {
        return connArrivalTime;
    }
}
