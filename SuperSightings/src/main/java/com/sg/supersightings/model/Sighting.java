/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author blake
 */
public class Sighting {
    
    private int sightingId;
    private Location location;
    private LocalDate date;
    List<SuperBeing> superBeings;
    
    public Sighting() {
        
    }
    
    public Sighting(Location location, LocalDate date, List<SuperBeing> supers) {
        this.location = location;
        this.date = date;
        this.superBeings = supers;
    }

    public int getSightingId() {
        return sightingId;
    }

    public void setSightingId(int sightingId) {
        this.sightingId = sightingId;
    }
    
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<SuperBeing> getSuperBeings() {
        return superBeings;
    }

    public void setSuperBeings(List<SuperBeing> superBeings) {
        this.superBeings = superBeings;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.sightingId;
        hash = 83 * hash + Objects.hashCode(this.location);
        hash = 83 * hash + Objects.hashCode(this.date);
        hash = 83 * hash + Objects.hashCode(this.superBeings);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.sightingId != other.sightingId) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.superBeings, other.superBeings)) {
            return false;
        }
        return true;
    }
    
    
    
}
