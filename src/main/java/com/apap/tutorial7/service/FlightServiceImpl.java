package com.apap.tutorial7.service;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.repository.FlightDb;
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
    public FlightModel addFlight(FlightModel flight) {
        flightDb.save(flight);
        return flight;
    }

    @Override
    public void deleteFlight(FlightModel flight) {
        flightDb.delete(flight);
    }

    @Override
    public FlightModel getFlighByFlightNumber(String flightNumber) {
        return flightDb.findByFlightNumber(flightNumber);
    }

    @Override
    public List<FlightModel> getFlightByPilotLicenseNumber(String licenseNumber) {
        return flightDb.findByPilotLicenseNumber(licenseNumber);
    }

    @Override
    public void updateFlight(FlightModel flight) {
        FlightModel flightData = flightDb.findFlightModelById(flight.getId());
        flightData.setOrigin(flight.getOrigin());
        flightData.setDestination(flight.getDestination());
        flightData.setTime(flight.getTime());
    }

    @Override
    public List<FlightModel> getAllFlight(){
        return flightDb.findAll();
    }

    @Override
    public FlightModel getFlighByFlightId(long id){
        return flightDb.findFlightModelById(id);
    }
}
