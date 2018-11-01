package com.apap.tutorial7.service;

import com.apap.tutorial7.model.FlightModel;

import java.util.List;

public interface FlightService {

    FlightModel addFlight(FlightModel flight);

    void deleteFlight(FlightModel flight);

    FlightModel getFlighByFlightNumber(String flightNumber);

    List<FlightModel> getFlightByPilotLicenseNumber(String licenseNumber);

    void updateFlight(String flightNumber, FlightModel flight);

    List<FlightModel> getAllFlight();
}
