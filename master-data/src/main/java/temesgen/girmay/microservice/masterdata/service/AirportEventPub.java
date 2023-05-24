package temesgen.girmay.microservice.masterdata.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.masterdata.domain.Airport;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class AirportEventPub {

    public static final String AIRPORT_CREATED = "created";
    public static final String AIRPORT_DELETED = "deleted";
    public static final String AIRPORT_UPDATED = "updated";
    public static final String ENTITY = "entity";
    public static final String AIRPORT_ROUTING_KEY = "connection.flight.airports";

    private final AmqpTemplate amqpTemplate;
    private final String masterDataTopicExchange;

    public AirportEventPub(AmqpTemplate amqpTemplate, @Value("${amqp.exchange.master.data}")
    final String masterDataTopicExchange) {
        this.amqpTemplate = amqpTemplate;
        this.masterDataTopicExchange = masterDataTopicExchange;
    }

    public void airportCreated(Airport airport){
        Map<String, String> airportEvent = buildEvent(airport, AIRPORT_CREATED);
        amqpTemplate.convertAndSend(masterDataTopicExchange,
                AIRPORT_ROUTING_KEY,
                airportEvent);
    }

    public void airportUpdated(Airport airport){
        Map<String, String> airportEvent = buildEvent(airport, AIRPORT_UPDATED);
        amqpTemplate.convertAndSend(masterDataTopicExchange,
                AIRPORT_ROUTING_KEY,
                airportEvent);
    }

    public void airportDeleted(Airport airport){
        Map<String, String> airportEvent = buildEvent(airport, AIRPORT_DELETED);
        amqpTemplate.convertAndSend(masterDataTopicExchange,
                AIRPORT_ROUTING_KEY,
                airportEvent);
    }

    private Map<String, String> buildEvent(Airport airport, String operation){
        Map<String, String> airportMap = new HashMap<>();
        airportMap.put("id", airport.getId().toString());
        airportMap.put("operation", operation);
        if(Objects.equals(operation, AIRPORT_CREATED) || Objects.equals(operation, AIRPORT_UPDATED)){
            airportMap.put("airportCode", airport.getAirportCode());
            airportMap.put("airportName", airport.getAirportName());
            airportMap.put("cityName", airport.getCityName());
        }
        return airportMap;
    }
}
