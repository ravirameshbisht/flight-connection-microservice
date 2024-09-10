package temesgen.girmay.microservice.masterdata.domain;

import javax.persistence.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "flight_schedule")
public class FlightSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "flight_number", unique = true, nullable = false)
    private String flightNumber;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    @Column(name = "departure_time", nullable = false)
    private LocalTime departureTime;

    @Column(name = "arrival_time", nullable = false)
    private LocalTime arrivalTime;

    public FlightSchedule() {
    }

    public FlightSchedule(String flightNumber, Airport departureAirport, Airport arrivalAirport, LocalTime departureTime, LocalTime arrivalTime) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static FlightSchedule build(String flightNumber, Airport departureAirport, Airport arrivalAirport, LocalTime departureTime, LocalTime arrivalTime){
        return new FlightSchedule(flightNumber, departureAirport, arrivalAirport, departureTime, arrivalTime);
    }

    public FlightSchedule(Long id, String flightNumber, Airport departureAirport, Airport arrivalAirport, LocalTime departureTime, LocalTime arrivalTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        this.id = id;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }

    public static FlightSchedule build(Long id, FlightSchedule flightSchedule) {
        return new FlightSchedule(id, flightSchedule.flightNumber,
                flightSchedule.departureAirport,
                flightSchedule.arrivalAirport,
                flightSchedule.departureTime,
                flightSchedule.arrivalTime);
    }

    public Long getId() {
        return id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }
}
