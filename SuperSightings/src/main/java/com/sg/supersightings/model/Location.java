/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.supersightings.model;

import java.util.Objects;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author blake
 */
public class Location {
    
    private int locationId;
    @NotEmpty(message = "Please enter a name for this location.")
    @Length(max = 45, message = "The location name cannot exceed 45 characters.")
    private String name;
    @NotEmpty(message = "Please enter a description for this location.")
    @Length(max = 100, message = "The description cannot exceed 100 characters.")
    private String description;
    @NotEmpty(message = "Please enter an address for this location.")
    @Length(max = 45, message = "Address Line 1 cannot exceed 45 characters.")
    private String addressLine1;
    @Length(max = 45, message = "Address Line 2 cannot exceed 45 characters.")
    private String addressLine2;
    @NotEmpty(message = "Please enter a city for this location.")
    @Length(max = 45, message = "The city cannot exceed 45 characters.")
    private String city;
    @NotEmpty(message = "Please enter a state/region for this location.")
    @Length(max = 25, message = "The region cannot exceed 25 characters.")
    private String region;
    @NotEmpty(message = "Please enter a postal code for this location.")
    @Length(max = 20, message = "The postal code cannot exceed 20 characters.")
    private String postalCode;
    @NotEmpty(message = "Please enter a country for this location.")
    @Length(max = 45, message = "The country cannot exceed 45 characters.")
    private String country;
//    @NotEmpty(message = "Please enter the latitude for this location.")
//    @Length(max = 9, message = "Latitude cannot exceed 9 characters.")
    private double latitude;
//    @NotEmpty(message = "Please enter the longitude for this location.")
//    @Length(max = 9, message = "Longitude cannot exceed 9 characters.")
    private double longitude;

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int location_id) {
        this.locationId = location_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.locationId;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.description);
        hash = 37 * hash + Objects.hashCode(this.addressLine1);
        hash = 37 * hash + Objects.hashCode(this.addressLine2);
        hash = 37 * hash + Objects.hashCode(this.city);
        hash = 37 * hash + Objects.hashCode(this.region);
        hash = 37 * hash + Objects.hashCode(this.postalCode);
        hash = 37 * hash + Objects.hashCode(this.country);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
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
        final Location other = (Location) obj;
        if (this.locationId != other.locationId) {
            return false;
        }
        if (Double.doubleToLongBits(this.latitude) != Double.doubleToLongBits(other.latitude)) {
            return false;
        }
        if (Double.doubleToLongBits(this.longitude) != Double.doubleToLongBits(other.longitude)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.addressLine1, other.addressLine1)) {
            return false;
        }
        if (!Objects.equals(this.addressLine2, other.addressLine2)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
        if (!Objects.equals(this.postalCode, other.postalCode)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        return true;
    }
    
    
    
}
