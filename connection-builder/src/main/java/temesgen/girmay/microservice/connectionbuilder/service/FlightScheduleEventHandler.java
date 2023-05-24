package temesgen.girmay.microservice.connectionbuilder.service;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import temesgen.girmay.microservice.connectionbuilder.domain.Airport;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightSchedule;

import java.time.LocalTime;
import java.util.Map;

@Service
public class FlightScheduleEventHandler {

    public static final String FLIGHT_SCHEDULE_ROUTING_KEY = "connection.flight.schedules";
    public static final String FLIGHT_SCHEDULE_CREATED = "created";
    public static final String FLIGHT_SCHEDULE_DELETED = "deleted";
    public static final String FLIGHT_SCHEDULE_UPDATED = "updated";
    public static final String ENTITY = "entity";
    public static final String ACTION = "action";
    public static final String ID = "id";
    public static final String OPERATION = "operation";
    public static final String FLIGHT_NUMBER = "flightNumber";
    public static final String DEPARTURE_AIRPORT_ID = "departureAirportId";
    public static final String ARRIVAL_AIRPORT_ID = "arrivalAirportId";
    public static final String DEPARTURE_TIME = "departureTime";
    public static final String ARRIVAL_TIME = "arrivalTime";
    private final FlightScheduleService flightScheduleService;
    private final AirportService airportService;

    public FlightScheduleEventHandler(final FlightScheduleService flightScheduleService,
                                      final AirportService airportService) {
        this.flightScheduleService = flightScheduleService;
        this.airportService = airportService;
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "${amqp.queue.schedule}"),
            exchange = @Exchange(value = "${amqp.exchange.master.data}", type = ExchangeTypes.TOPIC),
            key = FLIGHT_SCHEDULE_ROUTING_KEY))
    void handleFlightSchedule(final Map<String, String> event){
        String operation = event.get(OPERATION);
        switch (operation) {
            case FLIGHT_SCHEDULE_CREATED:
                handleFlightScheduleCreated(event);
                break;
            case FLIGHT_SCHEDULE_DELETED:
                handleFlightScheduleDeleted(event);
                break;
            case FLIGHT_SCHEDULE_UPDATED:
                handleFlightScheduleUpdated(event);
                break;
            default:
                System.out.println("Unknown operation: " + operation);
                break;
        }
    }
    void handleFlightScheduleCreated(Map<String, String> event){
        try {
            FlightSchedule flightSchedule = buildAirport(event);
            this.flightScheduleService.addFlightSchedule(flightSchedule);
        } catch (final Exception e) {
            //log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    private FlightSchedule buildAirport(Map<String, String> event){
        Long id = Long.parseLong(event.get(ID));
        String flightNumber = event.get(FLIGHT_NUMBER);
        Airport departureAirport = this.airportService.findAirportById(Long.parseLong(event.get(DEPARTURE_AIRPORT_ID)));
        Airport arrivalAirport = this.airportService.findAirportById(Long.parseLong(event.get(ARRIVAL_AIRPORT_ID)));
        LocalTime departureTime = LocalTime.parse(event.get(DEPARTURE_TIME));
        LocalTime arrivalTime = LocalTime.parse(event.get(ARRIVAL_TIME));
        return FlightSchedule.build(id, flightNumber, departureAirport, arrivalAirport, departureTime, arrivalTime);
    }

    void handleFlightScheduleDeleted(Map<String, String> event){
        try {
            this.flightScheduleService.deleteFlightSchedule(Long.parseLong(event.get(ID)));
        } catch (final Exception e) {
            //log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }

    void handleFlightScheduleUpdated(Map<String, String> event){
        try {
            FlightSchedule flightSchedule = buildAirport(event);
            this.flightScheduleService.updateFlightSchedule(flightSchedule);
        } catch (final Exception e) {
            //log.error("Error when trying to process ChallengeSolvedEvent", e);
            // Avoids the event to be re-queued and reprocessed.
            throw new AmqpRejectAndDontRequeueException(e);
        }
    }
}
