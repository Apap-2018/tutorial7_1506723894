package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;

import java.util.List;

public interface FlightService {

    void addFlight(FlightModel flight);

    void deleteFlight(FlightModel flight);

    FlightModel getFlighByFlightNumber(String flightNumber);

    List<FlightModel> getFlightByPilotLicenseNumber(String licenseNumber);

    void updateFlight(String flightNumber, FlightModel flight);
}
