package com.apap.tutorial5.repository;

import com.apap.tutorial5.model.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightDb extends JpaRepository<FlightModel, Long> {
    FlightModel findByFlightNumber(String flightNumber);
    List<FlightModel> findByPilotLicenseNumber(String pilotLicenseNumber);
    void deleteByPilotLicenseNumber(String pilotLicenseNumber);
}
