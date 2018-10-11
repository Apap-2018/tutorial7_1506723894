package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FlightController {
    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        List<FlightModel> flights = new ArrayList<>();
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        PilotModel newPilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        FlightModel flight1test = new FlightModel();
        flight1test.setPilot(pilot);
        flights.add(flight1test);
        newPilot.setPilotFlight(flights);

//        model.addAttribute("flights", flights);
        model.addAttribute("pilot", newPilot);
        model.addAttribute("pageTitle", "Add Flight");
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.POST)
    private String updateFlightSubmit(@ModelAttribute PilotModel pilot, @PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        System.out.println(pilot.getLicenseNumber());
        for (FlightModel flight : pilot.getPilotFlight()){
            System.out.println(flight.getFlightNumber());
            System.out.println(flight.getOrigin());
            System.out.println(flight.getDestination());
            System.out.println(flight.getTime());
        }
        PilotModel currentPilot = pilotService.getPilotDetailByLicenseNumber(pilot.getLicenseNumber());
        for (FlightModel flight : pilot.getPilotFlight()){
            flight.setPilot(currentPilot);
            flightService.addFlight(flight);
        }
        model.addAttribute("pageTitle", "Add");
        return "add";
    }

    @RequestMapping(value = "/flight/delete", method = RequestMethod.POST)
    private String deleteFlight(@ModelAttribute PilotModel pilot, Model model){
        for (FlightModel flight : pilot.getPilotFlight()){
            flightService.deleteFlight(flight);
        }
        model.addAttribute("pageTitle", "Delete");
        return "delete";
    }

    @RequestMapping(value = "/flight/view/{flightNumber}", method = RequestMethod.GET)
    private String getDetailFlightByFlightNumber(@PathVariable(value = "flightNumber") String flightNumber, Model model){
        FlightModel flight = flightService.getFlighByFlightNumber(flightNumber);
        System.out.println(flight.getFlightNumber());
        model.addAttribute("flight", flight);
        model.addAttribute("pageTitle", "Detail Flight");
        return "view-flight";
    }

    @RequestMapping(value = "/flight/update/{flightNumber}", method = RequestMethod.GET)
    private String update(@PathVariable(value = "flightNumber") String flightNumber, Model model){
        FlightModel flight = flightService.getFlighByFlightNumber(flightNumber);
        model.addAttribute("flight", flight);
        model.addAttribute("pageTitle", "Update Flight");
        return "updateFlight";
    }

    @RequestMapping(value = "/flight/update/{flightNumber}", method = RequestMethod.POST)
    private String updateFlightSubmit(@ModelAttribute FlightModel flight, @PathVariable(value = "flightNumber") String flightNumber, Model model){
        System.out.println(flight.getFlightNumber());
        flightService.updateFlight(flightNumber, flight);
        model.addAttribute("pageTitle", "Update");
        return "update";
    }

    @RequestMapping(value = "/flight/add/{licenseNumber}", params = {"addRow"}, method = RequestMethod.POST)
    public String addRow(@ModelAttribute PilotModel pilot, Model model) {
        pilot.getPilotFlight().add(new FlightModel());
        model.addAttribute("pilot", pilot);
        return "addFlight";
    }
//
//    @RequestMapping(value = "/flight/add/{licenseNumber}", params = {"delete"}, method = RequestMethod.POST)
//    public String removeRow(@ModelAttribute PilotModel pilot, Model model, HttpServletRequest req) {
//        int index = Integer.parseInt(req.getParameter("delete"));
//        pilot.getPilotFlight().remove(index);
//        model.addAttribute("pilot", pilot);
//        return "addFlight";
//    }
}
