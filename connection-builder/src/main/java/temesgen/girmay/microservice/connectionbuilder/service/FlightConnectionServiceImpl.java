package temesgen.girmay.microservice.connectionbuilder.service;

import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.connectionbuilder.domain.*;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightConnectionServiceImpl implements FlightConnectionService {

    private final FlightScheduleRepository flightScheduleRepository;
    private final AirportRepository airportRepository;

    public FlightConnectionServiceImpl(final FlightScheduleRepository flightScheduleRepository, final AirportRepository airportRepository) {
        this.flightScheduleRepository = flightScheduleRepository;
        this.airportRepository = airportRepository;
    }

    @Override
    public List<FlightConnectionDTO> buildFlightConnections(AirportCodesDTO airportCodesDTO) {

        List<FlightSchedule> departureFlightSchedules = this.flightScheduleRepository.findDepartureFlightSchedules(airportCodesDTO.getDepartureAirportCode());
        List<FlightSchedule> arrivalFlightSchedules = this.flightScheduleRepository.findArrivalFlightSchedules(airportCodesDTO.getArrivalAirportCode());

        List<FlightConnectionDTO> flightConnectionDTOs = departureFlightSchedules.stream()
                .filter(departure -> arrivalFlightSchedules.stream()
                        .anyMatch(arrival -> arrival.getDepartureAirport().getId().equals(departure.getArrivalAirport().getId())))
                .flatMap(departure -> arrivalFlightSchedules.stream()
                        .filter(arrival -> arrival.getDepartureAirport().getId().equals(departure.getArrivalAirport().getId()))
                        .filter(arrival -> {
                            LocalTime onwardArrivalTime = departure.getArrivalTime();
                            LocalTime connectionDepartureTime = arrival.getDepartureTime();
                            Duration duration = Duration.between(onwardArrivalTime, connectionDepartureTime);
                            long durationInMinutes = duration.toMinutes();
                            if (onwardArrivalTime.isAfter(connectionDepartureTime)) {
                                durationInMinutes = 24*60 - Duration.between(LocalTime.MIN, onwardArrivalTime).toMinutes() + Duration.between(LocalTime.MIN, connectionDepartureTime).toMinutes();
                            }

                            return durationInMinutes >= 120 && durationInMinutes <= 480;
                        })
                        .map(arrival -> new FlightConnectionDTO(
                                departure.getFlightNumber(),
                                departure.getDepartureAirport().getAirportCode(),
                                departure.getArrivalAirport().getAirportCode(),
                                departure.getDepartureTime(),
                                departure.getArrivalTime(),
                                arrival.getFlightNumber(),
                                arrival.getDepartureAirport().getAirportCode(),
                                arrival.getArrivalAirport().getAirportCode(),
                                arrival.getDepartureTime(),
                                arrival.getArrivalTime()
                        ))
                )
                .collect(Collectors.toList());

        return flightConnectionDTOs;
    }
}
