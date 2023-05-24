package temesgen.girmay.microservice.masterdata.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import temesgen.girmay.microservice.masterdata.domain.Airport;
import temesgen.girmay.microservice.masterdata.domain.FlightSchedule;
import temesgen.girmay.microservice.masterdata.domain.FlightScheduleDTO;
import temesgen.girmay.microservice.masterdata.service.AirportService;
import temesgen.girmay.microservice.masterdata.service.FlightScheduleService;

import java.util.List;


@RestController
@RequestMapping("/master-data")
public class FlightScheduleApiResources {

    private final FlightScheduleService flightScheduleService;

    public FlightScheduleApiResources(final FlightScheduleService flightScheduleService) {
        this.flightScheduleService = flightScheduleService;
    }

    @PostMapping("/flight-schedules")
    public ResponseEntity<FlightSchedule> createFlightSchedule(@RequestBody FlightScheduleDTO flightScheduleDTO) {
        return ResponseEntity.ok(this.flightScheduleService.addFlightSchedule(flightScheduleDTO));
    }

    @GetMapping("/flight-schedules")
    public ResponseEntity<List<FlightSchedule>> getFlightSchedules() {
        return ResponseEntity.ok(this.flightScheduleService.getFlightSchedules());
    }

    @GetMapping("/flight-schedules/{id}")
    public ResponseEntity<FlightSchedule> getFlightScheduleById(@PathVariable Long id) {
        return ResponseEntity.ok(this.flightScheduleService.getFlightScheduleById(id));
    }

    @DeleteMapping("/flight-schedules/{id}")
    public ResponseEntity<Void> deleteFlightScheduleById(@PathVariable Long id) {
        this.flightScheduleService.deleteFlightSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/flight-schedules/{id}")
    public ResponseEntity<FlightSchedule> updateUserById(@PathVariable Long id, @RequestBody FlightSchedule flightSchedule) {
        return ResponseEntity.ok(this.flightScheduleService.updateFlightSchedule(flightSchedule));
    }

}
