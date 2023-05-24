package temesgen.girmay.microservice.connectionbuilder.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightSchedule;
import temesgen.girmay.microservice.connectionbuilder.domain.FlightScheduleRepository;
import temesgen.girmay.microservice.connectionbuilder.service.FlightScheduleService;

import java.util.List;

@RestController
@RequestMapping("/connection-builder-schedules")
public class FlightScheduleApiResources {

    private final FlightScheduleService flightScheduleService;

    public FlightScheduleApiResources(final FlightScheduleService flightScheduleService) {
        this.flightScheduleService = flightScheduleService;
    }

    @GetMapping("/flight-schedules")
    public ResponseEntity<List<FlightSchedule>> getFlightSchedules() {
        return ResponseEntity.ok(this.flightScheduleService.findAllSchedules());
    }

    @DeleteMapping("/flight-schedules/{id}")
    public ResponseEntity<Void> deleteFlightScheduleById(@PathVariable Long id) {
        this.flightScheduleService.deleteFlightSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
