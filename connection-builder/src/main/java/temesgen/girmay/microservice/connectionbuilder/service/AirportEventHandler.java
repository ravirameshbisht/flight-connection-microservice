package temesgen.girmay.microservice.connectionbuilder.service;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.connectionbuilder.domain.Airport;

import java.util.Map;

@Service
public class AirportEventHandler {
    public static final String AIRPORT_CREATED = "created";
    public static final String AIRPORT_DELETED = "deleted";
    public static final String AIRPORT_UPDATED = "updated";
    public static final String ENTITY = "entity";
    public static final String ACTION = "action";
    public static final String AIRPORT_ROUTING_KEY = "connection.flight.airports";
    private final AirportService airportService;

    public AirportEventHandler(AirportService airportService) {
        this.airportService = airportService;
    }

    //@RabbitListener(queues = "${amqp.queue.connection.flight}")
    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "${amqp.queue.airport}"),
            exchange = @Exchange(value = "${amqp.exchange.master.data}", type = ExchangeTypes.TOPIC),
            key = AIRPORT_ROUTING_KEY))
    void handleAirport(final Map<String, String> event){
        String operation = event.get("operation");
        switch (operation) {
            case AIRPORT_CREATED:
                handleAirportCreated(event);
                break;
            case AIRPORT_DELETED:
                handleAirportDeleted(event);
                break;
            case AIRPORT_UPDATED:
                handleAirportUpdated(event);
                break;
            default:
                System.out.println("Unknown operation: " + operation);
                break;
        }
    }
    void handleAirportCreated(Map<String, String> event){
        try {
            Airport airport = buildAirport(event);
            this.airportService.addAirport(airport);
        } catch (final Exception e) {
            //log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private Airport buildAirport(Map<String, String> event){
        //return new Airport(event.getId(), event.getAirportCode(), event.getAirportName(), event.getCityName());
        Long id = Long.parseLong(event.get("id"));
        return new Airport(id, event.get("airportCode"), event.get("airportName"), event.get("cityName"));
    }

    void handleAirportDeleted(Map<String, String> event){
        try {
            this.airportService.deleteAirport(Long.parseLong(event.get("id")));
        } catch (final Exception e) {
            //log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    void handleAirportUpdated(Map<String, String> event){
        try {

        } catch (final Exception e) {
            //log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
