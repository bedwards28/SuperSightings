/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.service;

import com.sg.supersightings.dao.SuperBeingPersistenceException;
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
public interface SuperSightingsServiceLayer {
    
    // super_being methods
    public SuperBeing addSuperBeing(SuperBeing being) throws SuperBeingPersistenceException;
    
    public int deleteSuperBeing(int superId) throws SuperBeingPersistenceException;
    
    public SuperBeing updateSuperBeing(SuperBeing being) throws SuperBeingPersistenceException;
    
    public SuperBeing getSuperBeingById(int id) throws SuperBeingPersistenceException;
    
    public List<SuperBeing> getAllSuperBeings() throws SuperBeingPersistenceException;
    
    public List<SuperBeing> getAllOrganizationMembers(int orgId) throws SuperBeingPersistenceException;
    
    // power methods
    public Power addPower(Power power) throws SuperBeingPersistenceException;
    
    public int deletePower(int powerId) throws SuperBeingPersistenceException;
    
    public Power updatePower(Power power) throws SuperBeingPersistenceException;
    
    public Power getPowerById(int powerId) throws SuperBeingPersistenceException;
    
    public Power getPowerByDescription(String description) throws SuperBeingPersistenceException;
    
    public List<Power> getAllPowers() throws SuperBeingPersistenceException;
    
    // location methods
    public Location addLocation(Location location) throws SuperBeingPersistenceException;
    
    public int deleteLocationById(int locationId)throws SuperBeingPersistenceException;
    
    public Location updateLocation(Location location) throws SuperBeingPersistenceException;
    
    public Location getLocationById(int locationId) throws SuperBeingPersistenceException;
    
    public List<Location> getAllLocations() throws SuperBeingPersistenceException;
    
    public List<Location> getAllLocationsBySuperId(int superId) throws SuperBeingPersistenceException;
    
    // sighting methods
    public Sighting addSighting(Sighting sighting) throws SuperBeingPersistenceException;
    
    public int deleteSighting(int sightingId) throws SuperBeingPersistenceException;
    
    public Sighting updateSighting(Sighting sighting) throws SuperBeingPersistenceException;
    
    public Sighting getSightingById(int sightingId) throws SuperBeingPersistenceException;

    public List<Sighting> getAllSightings() throws SuperBeingPersistenceException;
    
    public List<Sighting> getAllSightingsByDate(LocalDate date) throws SuperBeingPersistenceException;
    
    // organization methods
    public Organization addOrganization(Organization org) throws SuperBeingPersistenceException;
    
    public int deleteOrganization(int orgId) throws SuperBeingPersistenceException;
    
    public Organization updateOrganization(Organization org) throws SuperBeingPersistenceException;
    
    public Organization getOrganizationById(int orgId) throws SuperBeingPersistenceException;
    
    public List<Organization> getAllOrganizations() throws SuperBeingPersistenceException;
    
    public List<Organization> getAllOrganizationsBySuperId(int superId) throws SuperBeingPersistenceException;
    
    public List<Sighting> getMostRecentSightings() throws SuperBeingPersistenceException;
    
}
