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

    @RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
    private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
        for (FlightModel flight : pilot.getPilotFlight()) {
            flightService.deleteFlight(flight);
        }
        model.addAttribute("pageTitle", "Delete");
        return "delete";
    }

    @GetMapping(value = "/view/{flightNumber}")
    private FlightModel getDetailFlightByFlightNumber(@PathVariable(value = "flightNumber") String flightNumber) {
        return flightService.getFlighByFlightNumber(flightNumber);
    }

    @DeleteMapping(value = "/delete")
    private String delete(@RequestParam(value = "flightNumber") String flightNumber) {
        FlightModel flight = flightService.getFlighByFlightNumber(flightNumber);
        flightService.deleteFlight(flight);
        return "flight has been deleted";
    }

    @PutMapping(value = "/update/{flightNumber}")
    private String updateFlightSubmit(@PathVariable(value = "flightNumber") String flightNumber,
                                      @RequestParam("destination") String destination,
                                      @RequestParam("origin") String origin,
                                      @RequestParam("time") Date time) {
        FlightModel flightModel = flightService.getFlighByFlightNumber(flightNumber);
        flightModel.setDestination(destination);
        flightModel.setOrigin(origin);
        flightModel.setTime(time);
        flightService.updateFlight(flightNumber, flightModel);
        return "flight update success";
    }

    @RequestMapping(value = "/flight/add/{licenseNumber}", params = {"addRow"}, method = RequestMethod.POST)
    public String addRow(@ModelAttribute PilotModel pilot, Model model) {
        pilot.getPilotFlight().add(new FlightModel());
        model.addAttribute("pilot", pilot);
        return "addFlight";
    }

    @GetMapping(value = "/all")
    private List<FlightModel> getDetailFlightByFlightNumber() {
        return flightService.getAllFlight();
    }

}
