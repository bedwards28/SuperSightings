/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.SuperBeing;
import java.util.List;
import java.util.Map;

/**
 *
 * @author blake
 */
public interface SuperBeingDao {
    
    public SuperBeing addSuperBeing(SuperBeing being);
    
    public SuperBeing deleteSuperBeing(int superId);
    
    public SuperBeing updateSuperBeing(SuperBeing being);
    
    public SuperBeing getSuperBeingById(int id);
    
    public List<SuperBeing> getAllSuperBeings();
    
    public Power addPower(Power power);
    
    public Power deletePower(int powerId);
    
    public Power updatePower(Power power);
    
    public Power getPowerById(int powerId);
    
    public Power getPowerByDescription(String description);
    
    public List<Power> getAllPowers();
    
//    public void addSuperBeingPower(int superId, int powerId);
    
    public void addSuperBeingPowers(SuperBeing superBeing);
    
    public List<Power> getAllPowersBySuperId(int superId);
    
    public List<Integer> getAllSuperIdsWithPowers();
    
//    public void updateSuperBeingPowers(SuperBeing superBeing);
    
    public void deleteSuperBeingPowersBySuperId(int superId);
    
}
