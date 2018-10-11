package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements FlightService {
    @Autowired
    private FlightDb flightDb;

    @Override
    public void addFlight(FlightModel flight){
        flightDb.save(flight);
    }

    @Override
    public void deleteFlight(FlightModel flight){
        flightDb.delete(flight);
    }

    @Override
    public FlightModel getFlighByFlightNumber(String flightNumber){
        return flightDb.findByFlightNumber(flightNumber);
    }

    @Override
    public List<FlightModel> getFlightByPilotLicenseNumber(String licenseNumber){
        return flightDb.findByPilotLicenseNumber(licenseNumber);
    }

    @Override
    public void updateFlight(String flightNumber, FlightModel flight){
        FlightModel flightData = flightDb.findByFlightNumber(flightNumber);
        flightData.setOrigin(flight.getOrigin());
        flightData.setDestination(flight.getDestination());
        flightData.setTime(flight.getTime());
    }
}
