package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.FlightModel;
import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.rest.PilotDetail;
import com.apap.tutorial7.rest.Setting;
import com.apap.tutorial7.service.FlightService;
import com.apap.tutorial7.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/pilot")
public class PilotController {
    @Autowired
    private PilotService pilotService;

    @Autowired
    private RestTemplate restTemplatePilot;

    @Bean
    public RestTemplate restPilot(){
        return new RestTemplate();
    }

    @GetMapping(value = "/status/{licenseNumber}")
    public String getStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception{
        String path = Setting.pilotUrl + "/pilot?licenseNumber=" + licenseNumber;
        return restTemplatePilot.getForEntity(path, String.class).getBody();
    }

    @GetMapping(value = "/full/{licenseNumber}")
    public PilotDetail postStatus(@PathVariable("licenseNumber") String licenseNumber) throws Exception{
        String path = Setting.pilotUrl + "/pilot";
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        PilotDetail detail = restTemplatePilot.postForObject(path, pilot, PilotDetail.class);
        return detail;
    }

    @PostMapping(value = "/add")
    private PilotModel addPilotSubmit(@RequestBody PilotModel pilot) {
        return pilotService.addPilot(pilot);
    }

    @GetMapping(value = "/view/{licenseNumber}")
    private PilotModel getDetailPilotByLicenseNumber(@PathVariable("licenseNumber") String licenseNumber) {
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        return pilot;
    }

    @DeleteMapping(value = "/delete")
    private String delete(@RequestParam(value = "licenseNumber") String licenseNumber) {
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        pilotService.deletePilot(pilot);
        return "success";
    }

    @PutMapping(value = "/update/{licenseNumber}")
    private String updatePilotSubmit(@PathVariable(value = "licenseNumber") String licenseNumber,
                                     @RequestParam("name") String name,
                                     @RequestParam("flyHour") int flyHour) {

        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        if(pilot.equals(null)){
            return "couldn't find your pilot";
        }
        pilot.setName(name);
        pilot.setFlyHour(flyHour);
        pilotService.updatePilot(licenseNumber, pilot);
        return "update";
    }
}
