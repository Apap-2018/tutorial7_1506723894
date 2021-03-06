package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private RestTemplate restTemplateFlight;

    @Bean
    public RestTemplate restFlight(){
        return new RestTemplate();
    }

    @GetMapping(value = "/airports")
    public String getStatus(@RequestParam("kodeKota") String kodeKota) throws Exception{
        String path = Setting.airPortUrl + "term=" + kodeKota + "&country=ID";
        return restTemplateFlight.getForEntity(path, String.class).getBody();
    }


    @PostMapping(value = "/add")
    private FlightModel addFlightSubmit(@RequestBody FlightModel flight) {
        return flightService.addFlight(flight);
    }

    @GetMapping(value = "/view/{id}")
    private FlightModel getDetailFlightByFlightNumber(@PathVariable(value = "id") Long id) {
        return flightService.getFlighByFlightId(id);
    }

    @DeleteMapping(value = "/delete")
    private String delete(@RequestParam(value = "id") Long id) {
        FlightModel flight = flightService.getFlighByFlightId(id);
        flightService.deleteFlight(flight);
        return "flight has been deleted";
    }

    @PutMapping(value = "/update/{id}")
    private String updateFlightSubmit(@PathVariable(value = "id") Long id,
                                      @RequestParam("destination") String destination,
                                      @RequestParam("origin") String origin,
                                      @RequestParam("time") Date time) {
        FlightModel flightModel = flightService.getFlighByFlightId(id);
        flightModel.setDestination(destination);
        flightModel.setOrigin(origin);
        flightModel.setTime(time);
        flightService.updateFlight(flightModel);
        return "flight update success";
    }

    @GetMapping(value = "/all")
    private List<FlightModel> getAllFlight() {
        return flightService.getAllFlight();
    }

}
