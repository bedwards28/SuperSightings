/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperBeing;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author blake
 */
public interface SuperBeingDao {
    
    // super_being methods
    public void addSuperBeing(SuperBeing being);
    
    public SuperBeing deleteSuperBeing(int superId);
    
    public SuperBeing updateSuperBeing(SuperBeing being);
    
    public SuperBeing getSuperBeingById(int id);
    
    public List<SuperBeing> getAllSuperBeings();
    
    public List<SuperBeing> getAllOrganizationMembers(int orgId);
    
    // power methods
    public Power addPower(Power power);
    
    public void deletePower(int powerId);
    
    public Power updatePower(Power power);
    
    public Power getPowerById(int powerId);
    
    public Power getPowerByDescription(String description);
    
    public List<Power> getAllPowers();
    
    // location methods
    public void addLocation(Location location);
    
    public void deleteLocationById(int locationId);
    
    public void updateLocation(Location location);
    
    public Location getLocationById(int locationId);
    
    public List<Location> getAllLocations();
    
    public List<Location> getAllLocationsBySuperId(int superId);
    
    // sighting methods
    public void addSighting(Sighting sighting);
    
    public void deleteSighting(int sightingId);
    
    public void updateSighting(Sighting sighting);
    
    public Sighting getSightingById(int sightingId);

    public List<Sighting> getAllSightings();
    
    public List<Sighting> getAllSightingsByDate(LocalDate date);
    
    // organization methods
    public void addOrganization(Organization org);
    
    public void deleteOrganization(int orgId);
    
    public void updateOrganization(Organization org);
    
    public Organization getOrganizationById(int orgId);
    
    public List<Organization> getAllOrganizations();
    
    public List<Organization> getAllOrganizationsBySuperId(int superId);
    
}
