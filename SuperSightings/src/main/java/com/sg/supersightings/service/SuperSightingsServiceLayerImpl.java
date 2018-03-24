/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.service;

import com.sg.supersightings.dao.SuperBeingDao;
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
@Component
public class SuperSightingsServiceLayerImpl implements SuperSightingsServiceLayer {
    
    @Inject
    private SuperBeingDao dao;

    @Override
    public void addSuperBeing(SuperBeing being) {
        dao.addSuperBeing(being);
    }

    @Override
    public SuperBeing deleteSuperBeing(int superId) {
        return dao.deleteSuperBeing(superId);
    }

    @Override
    public SuperBeing updateSuperBeing(SuperBeing being) {
        return dao.updateSuperBeing(being);
    }

    @Override
    public SuperBeing getSuperBeingById(int id) {
        return dao.getSuperBeingById(id);
    }

    @Override
    public List<SuperBeing> getAllSuperBeings() {
        return dao.getAllSuperBeings();
    }

    @Override
    public List<SuperBeing> getAllOrganizationMembers(int orgId) {
        return dao.getAllOrganizationMembers(orgId);
    }

    @Override
    public Power addPower(Power power) {
        return dao.addPower(power);
    }

    @Override
    public void deletePower(int powerId) {
        dao.deletePower(powerId);
    }

    @Override
    public Power updatePower(Power power) {
        return dao.updatePower(power);
    }

    @Override
    public Power getPowerById(int powerId) {
        return dao.getPowerById(powerId);
    }

    @Override
    public Power getPowerByDescription(String description) {
        return dao.getPowerByDescription(description);
    }

    @Override
    public List<Power> getAllPowers() {
        return dao.getAllPowers();
    }

    @Override
    public void addLocation(Location location) {
        dao.addLocation(location);
    }

    @Override
    public void deleteLocationById(int locationId) {
        dao.deleteLocationById(locationId);
    }

    @Override
    public void updateLocation(Location location) {
        dao.updateLocation(location);
    }

    @Override
    public Location getLocationById(int locationId) {
        return dao.getLocationById(locationId);
    }

    @Override
    public List<Location> getAllLocations() {
        return dao.getAllLocations();
    }

    @Override
    public List<Location> getAllLocationsBySuperId(int superId) {
        return dao.getAllLocationsBySuperId(superId);
    }

    @Override
    public void addSighting(Sighting sighting) {
        dao.addSighting(sighting);
    }

    @Override
    public void deleteSighting(int sightingId) {
        dao.deleteSighting(sightingId);
    }

    @Override
    public void updateSighting(Sighting sighting) {
        dao.updateSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int sightingId) {
        return dao.getSightingById(sightingId);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return dao.getAllSightings();
    }

    @Override
    public List<Sighting> getAllSightingsByDate(LocalDate date) {
        return dao.getAllSightingsByDate(date);
    }

    @Override
    public void addOrganization(Organization org) {
        dao.addOrganization(org);
    }

    @Override
    public void deleteOrganization(int orgId) {
        dao.deleteOrganization(orgId);
    }

    @Override
    public void updateOrganization(Organization org) {
        dao.updateOrganization(org);
    }

    @Override
    public Organization getOrganizationById(int orgId) {
        return dao.getOrganizationById(orgId);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return dao.getAllOrganizations();
    }

    @Override
    public List<Organization> getAllOrganizationsBySuperId(int superId) {
        return dao.getAllOrganizationsBySuperId(superId);
    }
    
}
