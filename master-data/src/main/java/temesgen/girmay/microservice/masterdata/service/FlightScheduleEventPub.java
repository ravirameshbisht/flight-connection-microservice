package temesgen.girmay.microservice.masterdata.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.masterdata.domain.Airport;
import temesgen.girmay.microservice.masterdata.domain.FlightSchedule;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class FlightScheduleEventPub {
    public static final String FLIGHT_SCHEDULE_ROUTING_KEY = "connection.flight.schedules";
    public static final String FLIGHT_SCHEDULE_CREATED = "created";
    public static final String FLIGHT_SCHEDULE_DELETED = "deleted";
    public static final String FLIGHT_SCHEDULE_UPDATED = "updated";
    public static final String ID = "id";
    public static final String ENTITY = "entity";
    public static final String OPERATION = "operation";
    public static final String FLIGHT_NUMBER = "flightNumber";
    public static final String DEPARTURE_AIRPORT_ID = "departureAirportId";
    public static final String ARRIVAL_AIRPORT_ID = "arrivalAirportId";
    public static final String DEPARTURE_TIME = "departureTime";
    public static final String ARRIVAL_TIME = "arrivalTime";

    private final AmqpTemplate amqpTemplate;
    private final String masterDataTopicExchange;

    public FlightScheduleEventPub(AmqpTemplate amqpTemplate, @Value("${amqp.exchange.master.data}")
    final String masterDataTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.masterDataTopicExchange = masterDataTopicExchange;
    }

    public void flightScheduleCreated(FlightSchedule flightSchedule){
        Map<String, String> flightScheduleEvent = buildEvent(flightSchedule, FLIGHT_SCHEDULE_CREATED);
        amqpTemplate.convertAndSend(masterDataTopicExchange,
                FLIGHT_SCHEDULE_ROUTING_KEY,
                flightScheduleEvent);
    }

    public void flightScheduleUpdated(FlightSchedule flightSchedule){
        Map<String, String> flightScheduleEvent = buildEvent(flightSchedule, FLIGHT_SCHEDULE_UPDATED);
        amqpTemplate.convertAndSend(masterDataTopicExchange,
                FLIGHT_SCHEDULE_ROUTING_KEY,
                flightScheduleEvent);
    }

    public void flightScheduleDeleted(FlightSchedule flightSchedule){
        Map<String, String> flightScheduleEvent = buildEvent(flightSchedule, FLIGHT_SCHEDULE_DELETED);
        amqpTemplate.convertAndSend(masterDataTopicExchange,
                FLIGHT_SCHEDULE_ROUTING_KEY,
                flightScheduleEvent);
    }

    private Map<String, String> buildEvent(FlightSchedule flightSchedule, String operation){
        Map<String, String> airportMap = new HashMap<>();
        airportMap.put(ID, flightSchedule.getId().toString());
        airportMap.put(OPERATION, operation);
        if(Objects.equals(operation, FLIGHT_SCHEDULE_CREATED) || Objects.equals(operation, FLIGHT_SCHEDULE_UPDATED) ){
            airportMap.put(FLIGHT_NUMBER, flightSchedule.getFlightNumber());
            airportMap.put(DEPARTURE_AIRPORT_ID, flightSchedule.getDepartureAirport().getId().toString());
            airportMap.put(ARRIVAL_AIRPORT_ID, flightSchedule.getArrivalAirport().getId().toString());
            airportMap.put(DEPARTURE_TIME, flightSchedule.getDepartureTime().toString());
            airportMap.put(ARRIVAL_TIME, flightSchedule.getArrivalTime().toString());
        }
        return airportMap;
    }
}
