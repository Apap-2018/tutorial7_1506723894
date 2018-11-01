package com.apap.tutorial7.service;

import com.apap.tutorial7.model.PilotModel;
import com.apap.tutorial7.repository.PilotDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {
    @Autowired
    private PilotDb pilotDb;

    @Override
    public PilotModel getPilotDetailByLicenseNumber(String licenseNumber) {
        return pilotDb.findByLicenseNumber(licenseNumber);
    }

    @Override
    public PilotModel addPilot(PilotModel pilot) {
        pilotDb.save(pilot);
        return pilot;
    }

    @Override
    public void deletePilot(PilotModel pilot) {
        pilotDb.delete(pilot);
    }

    @Override
    public void updatePilot(String licenseNumber, PilotModel pilot) {
        PilotModel pilotData = pilotDb.findByLicenseNumber(licenseNumber);
        pilotData.setName(pilot.getName());
        pilotData.setFlyHour(pilot.getFlyHour());
    }
}
