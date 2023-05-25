package temesgen.girmay.microservice.connectionbuilder.domain;

import javax.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "airport_code", length = 3, unique = true, nullable = false)
    private String airportCode;

    @Column(name = "airport_name", unique = true, nullable = false)
    private String airportName;
    @Column(name = "city_name", unique = true, nullable = false)
    private String cityName;

    public Airport() {
    }

    public Airport(final String airportCode, final String airportName, final String cityName) {
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.cityName = cityName;
    }

    public Airport(final Long id, final String airportCode, final String airportName, final String cityName) {
        this.id = id;
        this.airportCode = airportCode;
        this.airportName = airportName;
        this.cityName = cityName;
    }

    public static Airport build(Long id, Airport airport) {
        return new Airport(id, airport.airportCode, airport.airportName, airport.cityName);
    }

    public Long getId() {
        return id;
    }

    public String getAirportCode() {
        return airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public String getCityName() {
        return cityName;
    }
}
