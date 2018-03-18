/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.dao;

import com.sg.supersightings.model.Power;
import java.util.List;

/**
 *
 * @author blake
 */
public interface PowerDao {
    
    public Power addPower(Power power);
    
    public Power deletePower(int powerId);
    
    public Power updatePower(Power power);
    
    public Power getPowerById(int powerId);
    
    public Power getPowerByDescription(String description);
    
    public List<Power> getAllPowers();
    
}
