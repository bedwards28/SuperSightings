/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.service;

import com.sg.supersightings.dao.SuperBeingDao;
import com.sg.supersightings.dao.SuperBeingPersistenceException;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperBeing;
import java.time.LocalDate;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Component;

/**
 *
 * @author blake
 */
public class SuperSightingsServiceLayerImpl implements SuperSightingsServiceLayer {
    
    private SuperBeingDao dao;

    public void setSuperBeingDao(SuperBeingDao dao) throws SuperBeingPersistenceException {
        this.dao = dao;
    }

    @Override
    public SuperBeing addSuperBeing(SuperBeing being) throws SuperBeingPersistenceException {
        return dao.addSuperBeing(being);
    }

    @Override
    public int deleteSuperBeing(int superId) throws SuperBeingPersistenceException {
        return dao.deleteSuperBeing(superId);
    }

    @Override
    public SuperBeing updateSuperBeing(SuperBeing being) throws SuperBeingPersistenceException {
        return dao.updateSuperBeing(being);
    }

    @Override
    public SuperBeing getSuperBeingById(int id) throws SuperBeingPersistenceException {
        return dao.getSuperBeingById(id);
    }

    @Override
    public List<SuperBeing> getAllSuperBeings() throws SuperBeingPersistenceException {
        return dao.getAllSuperBeings();
    }

    @Override
    public List<SuperBeing> getAllOrganizationMembers(int orgId) throws SuperBeingPersistenceException {
        return dao.getAllOrganizationMembers(orgId);
    }

    @Override
    public Power addPower(Power power) throws SuperBeingPersistenceException {
        return dao.addPower(power);
    }

    @Override
    public int deletePower(int powerId) throws SuperBeingPersistenceException {
        return dao.deletePower(powerId);
    }

    @Override
    public Power updatePower(Power power) throws SuperBeingPersistenceException {
        return dao.updatePower(power);
    }

    @Override
    public Power getPowerById(int powerId) throws SuperBeingPersistenceException {
        return dao.getPowerById(powerId);
    }

    @Override
    public Power getPowerByDescription(String description) throws SuperBeingPersistenceException {
        return dao.getPowerByDescription(description);
    }

    @Override
    public List<Power> getAllPowers() throws SuperBeingPersistenceException {
        return dao.getAllPowers();
    }

    @Override
    public Location addLocation(Location location) throws SuperBeingPersistenceException {
        return dao.addLocation(location);
    }

    @Override
    public int deleteLocationById(int locationId) throws SuperBeingPersistenceException {
        return dao.deleteLocationById(locationId);
    }

    @Override
    public Location updateLocation(Location location) throws SuperBeingPersistenceException {
        return dao.updateLocation(location);
    }

    @Override
    public Location getLocationById(int locationId) throws SuperBeingPersistenceException {
        return dao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations() throws SuperBeingPersistenceException {
        return dao.getAllLocations();
    }

    @Override
    public List<Location> getAllLocationsBySuperId(int superId) throws SuperBeingPersistenceException {
        return dao.getAllLocationsBySuperId(superId);
    }

    @Override
    public Sighting addSighting(Sighting sighting) throws SuperBeingPersistenceException {
        return dao.addSighting(sighting);
    }

    @Override
    public int deleteSighting(int sightingId) throws SuperBeingPersistenceException {
        return dao.deleteSighting(sightingId);
    }

    @Override
    public Sighting updateSighting(Sighting sighting) throws SuperBeingPersistenceException {
        return dao.updateSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int sightingId) throws SuperBeingPersistenceException {
        return dao.getSightingById(sightingId);
    }

    @Override
    public List<Sighting> getAllSightings() throws SuperBeingPersistenceException {
        return dao.getAllSightings();
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate date) throws SuperBeingPersistenceException {
        return dao.getAllSightingsByDate(date);
    }

    @Override
    public Organization addOrganization(Organization org) throws SuperBeingPersistenceException {
        return dao.addOrganization(org);
    }

    @Override
    public int deleteOrganization(int orgId) throws SuperBeingPersistenceException {
        return dao.deleteOrganization(orgId);
    }

    @Override
    public Organization updateOrganization(Organization org) throws SuperBeingPersistenceException {
        return dao.updateOrganization(org);
    }

    @Override
    public Organization getOrganizationById(int orgId) throws SuperBeingPersistenceException {
        return dao.getOrganizationById(orgId);
    }

    @Override
    public List<Organization> getAllOrganizations() throws SuperBeingPersistenceException {
        return dao.getAllOrganizations();
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperId(int superId) throws SuperBeingPersistenceException {
        return dao.getAllOrganizationsBySuperId(superId);
    }

    @Override
    public List<Sighting> getMostRecentSightings() throws SuperBeingPersistenceException {
        return dao.getMostRecentSightings();
    }
    
}
