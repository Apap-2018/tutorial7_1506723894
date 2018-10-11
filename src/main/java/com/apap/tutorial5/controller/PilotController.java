package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @Autowired
    private FlightService flightService;

    @RequestMapping("/")
    private String home(Model model){
        model.addAttribute("pageTitle", "Home");
        return "home";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
    private String add(Model model){
        model.addAttribute("pilot", new PilotModel());
        model.addAttribute("pageTitle", "Add Pilot");
        return "addPilot";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
    private String addPilotSubmit(@ModelAttribute PilotModel pilot, Model model){
        model.addAttribute("pageTitle", "Add");
        pilotService.addPilot(pilot);
        return "add";
    }

    @RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
    private String getDetailPilotByLicenseNumber(@RequestParam("licenseNumber") String licenseNumber, Model model){
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        List<FlightModel> listFlights = flightService.getFlightByPilotLicenseNumber(licenseNumber);

        model.addAttribute("pilot", pilot);
        model.addAttribute("listFlights", listFlights);
        model.addAttribute("pageTitle", "Detail Pilot");
        return "view-pilot";
    }

    @RequestMapping(value = "/pilot/delete/{licenseNumber}", method = RequestMethod.GET)
    private String delete(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        pilotService.deletePilot(pilot);
        model.addAttribute("pageTitle", "Delete");
        return "delete";
    }

    @RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.GET)
    private String update(@PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        model.addAttribute("pilot", pilot);
        model.addAttribute("pageTitle", "Update Pilot");
        return "updatePilot";
    }

    @RequestMapping(value = "/pilot/update/{licenseNumber}", method = RequestMethod.POST)
    private String updatePilotSubmit(@ModelAttribute PilotModel pilot, @PathVariable(value = "licenseNumber") String licenseNumber, Model model){
        System.out.println(pilot.getLicenseNumber());
        pilotService.updatePilot(licenseNumber, pilot);
        model.addAttribute("pageTitle", "Updates");
        return "update";
    }
}
