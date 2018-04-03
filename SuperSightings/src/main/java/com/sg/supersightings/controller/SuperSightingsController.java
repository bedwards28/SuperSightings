package com.sg.supersightings.controller;

import com.sg.supersightings.dao.SuperBeingPersistenceException;
import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperBeing;
import com.sg.supersightings.service.SuperSightingsServiceLayer;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SuperSightingsController {

    @Inject
    private SuperSightingsServiceLayer service;

    public SuperSightingsController() {
    }

    @GetMapping("/")
    public String displayHomePage(Model model) {
        // add newsfeed items to model
        return "redirect:index";
    }
    
    @GetMapping("/recentsightings")
    @ResponseBody
    public List<Sighting> getRecentSightings() {
        try {
            return service.getMostRecentSightings();
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to load news feed.");
        }
        
        return null;
    }
    
    @GetMapping("/index")
    public String displayIndex(Model model) {
        List<Sighting> sightingList = new ArrayList<>();
        
        try {
            sightingList = service.getMostRecentSightings();
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to load news feed.");
        }
        
        model.addAttribute("sightingList", sightingList);
        return "index";
    }

    @GetMapping("/supers")
    public String displaySuperBeingsPage(Model model) {
        List<SuperBeing> superList;
        List<Power> powerList;
        try {
            superList = service.getAllSuperBeings();
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to load supers list.");
            superList = new ArrayList<>();
        }
        
        try {
            powerList = service.getAllPowers();
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to load powers list.");
            powerList = new ArrayList<>();
        }
        
        SuperBeing sb = new SuperBeing();
        model.addAttribute("superBeing", sb);
        model.addAttribute("superList", superList);
        model.addAttribute("powerList", powerList);
        return "superBeings";
    }

    @GetMapping("/superDetails")
    public String displaySuperBeingDetails(
            HttpServletRequest request, Model model) {

        String superIdParameter = request.getParameter("superId");
        int superId = Integer.parseInt(superIdParameter);

        SuperBeing sb = new SuperBeing();
        try {
            sb = service.getSuperBeingById(superId);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to load super being.");
        }

        model.addAttribute("superBeing", sb);

        return "superBeingDetails";
    }

    @PostMapping("/createSuperBeing")
    public String createSuperBeing(
            @Valid @ModelAttribute("superBeing") SuperBeing sb,
            BindingResult result,
            Model model) {

        List<Power> powers = sb.getPowers();
        List<Power> newPowers = new ArrayList<>();

        try {
            for (Power currentPower : powers) {
                Power newPower
                        = service.getPowerByDescription(
                                currentPower.getDescription());

                newPowers.add(newPower);
            }

            sb.setPowers(newPowers);
        } catch (SuperBeingPersistenceException e) {
            
        } catch (NullPointerException e ) {
            // do nothing, hasErrors will return errors
        }
        try {
            if (result.hasErrors()) {
                model.addAttribute("powerList", service.getAllPowers());
                model.addAttribute("superList", service.getAllSuperBeings());
                model.addAttribute("superBeing", sb);
                return "superBeings";
            }

            service.addSuperBeing(sb);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to create super being.");
        }
        
        return "redirect:supers";
    }

    @GetMapping("/deleteSuperBeing")
    public String deleteSuperBeing(@RequestParam("superId") int superId) {
        try {
            int success = service.deleteSuperBeing(superId);
            
            if (success == 0) {
                JOptionPane.showMessageDialog(
                        null, "Unable to delete super being due to relationship with "
                                + "organizations and sightings.");
            }
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(
                        null, "Unable to delete super being due to relationship with "
                                + "organizations and sightings.");
        }
        
        return "redirect:supers";
    }

    @GetMapping("/editSuperBeingForm")
    public String displayEditSuperBeingForm(
            @RequestParam("superId") int superId, Model model) {

        SuperBeing sb = new SuperBeing();
        List<Power> powerList = new ArrayList<>();
        
        try {
            sb = service.getSuperBeingById(superId);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to edit super being.");
        }
        
        try {
            powerList = service.getAllPowers();
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to get power list.");
        }
        
        model.addAttribute("superBeing", sb);
        model.addAttribute("powerList", powerList);
        return "editSuperBeingForm";
    }

    @PostMapping("/editSuperBeing")
    public String editSuperBeing(
            @Valid @ModelAttribute("superBeing") SuperBeing sb,
            BindingResult result,
            Model model) {

        List<Power> powers = sb.getPowers();
        List<Power> newPowers = new ArrayList<>();

        try {
            if (powers.size() > 0) {
                for (Power currentPower : powers) {
                    Power newPower
                            = service.getPowerByDescription(currentPower.getDescription());

                    newPowers.add(newPower);
                }

                sb.setPowers(newPowers);
            }
        } catch (Exception e) {
            
        }

        List<Power> powerList = new ArrayList<>();
        
        try {
           powerList = service.getAllPowers();
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to get power list.");
        }

        if (result.hasErrors()) {
            model.addAttribute("powerList", powerList);
            model.addAttribute("superBeing", sb);
            return "editSuperBeingForm";
        }

        try {
            service.updateSuperBeing(sb);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Unable to update super being.");
        }

        return "redirect:supers";
    }

    @GetMapping("/organizations")
    public String displayOrganizationsPage(Model model) {
        List<Organization> orgList = new ArrayList<>();
        List<Location> locationList = new ArrayList<>();
        List<SuperBeing> superList = new ArrayList<>();
        Organization organization = new Organization();
        
        try {
            orgList = service.getAllOrganizations();
            locationList = service.getAllLocations();
            superList = service.getAllSuperBeings();
            
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error loading organization page.");
        }
        
        model.addAttribute("orgList", orgList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superList", superList);
        model.addAttribute("organization", organization);
        
        return "organizations";
    }

    @GetMapping("/organizationDetails")
    public String displayOrganizationDetails(
            @RequestParam("organizationId") int organizationId, Model model) {

        Organization org = new Organization();
        
        try {
            org = service.getOrganizationById(organizationId);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error loading organization details.");
        }
        
        model.addAttribute("organization", org);

        return "organizationDetails";
    }

    @PostMapping("/createOrganization")
    public String createOrganization(
            @Valid @ModelAttribute("organization") Organization organization,
            BindingResult result,
            @RequestParam("select-location") int locationId,
            @RequestParam(value = "memberIds", required = false) List<String> memberIdList,
            Model model) {

        // Insert location into organization
        Location location = new Location();
        try {
            location = service.getLocationById(locationId);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error creating organization.");
        }
        
        organization.setLocation(location);

        // Insert members into organization
        List<SuperBeing> members = new ArrayList<>();
        SuperBeing sb;

        if (memberIdList != null) {
            for (String currentIdString : memberIdList) {
                int currentId = Integer.parseInt(currentIdString);
                try {
                    sb = service.getSuperBeingById(currentId);
                    members.add(sb);
                } catch (SuperBeingPersistenceException e) {
                    JOptionPane.showMessageDialog(null, "Error creating organization.");
                }
                
            }
        }

        organization.setMembers(members);

        try {
            if (result.hasErrors()) {
                List<Organization> orgList = service.getAllOrganizations();
                List<Location> locationList = service.getAllLocations();
                List<SuperBeing> superList = service.getAllSuperBeings();

                model.addAttribute("orgList", orgList);
                model.addAttribute("locationList", locationList);
                model.addAttribute("superList", superList);
                model.addAttribute("organization", organization);
                return "organizations";
            }

            service.addOrganization(organization);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error creating organization members.");
        }
        

        return "redirect:organizations";
    }

    @GetMapping("/deleteOrganization")
    public String deleteOrganization(@RequestParam("organizationId") int organizationId) {
        try {
            service.deleteOrganization(organizationId);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error deleting organization.");
        }
        
        return "redirect:organizations";
    }

    @GetMapping("/editOrganizationForm")
    public String displayEditOrganizationForm(
            HttpServletRequest request, Model model) {
        
        try {
            String organizationIdParamter = request.getParameter("organizationId");
            int organizationId = Integer.parseInt(organizationIdParamter);
            Organization organization = service.getOrganizationById(organizationId);

            List<Location> locationList = service.getAllLocations();
            List<SuperBeing> superList = service.getAllSuperBeings();

            model.addAttribute("superList", superList);
            model.addAttribute("locationList", locationList);
            model.addAttribute("organization", organization);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error displayEditOrganizationForm.");
        }
        
        return "editOrganizationForm";
    }
    
    @PostMapping("/editOrganization")
    public String editOrganization(
            @Valid @ModelAttribute("organization") Organization organization, 
            BindingResult result, 
            @RequestParam("select-location") int locationId,
            @RequestParam(value = "memberIds", required = false) List<String> memberIdList,
            Model model) {
        
        try {
            // Insert location into organization
            Location location = service.getLocationById(locationId);
            organization.setLocation(location);

            // Insert members into organization
            List<SuperBeing> members = new ArrayList<>();

            if (memberIdList != null) {
                for (String currentIdString : memberIdList) {
                    int currentId = Integer.parseInt(currentIdString);
                    SuperBeing sb = service.getSuperBeingById(currentId);
                    members.add(sb);
                }
            }

            organization.setMembers(members);

            if (result.hasErrors()) {
                List<Organization> orgList = service.getAllOrganizations();
                List<Location> locationList = service.getAllLocations();
                List<SuperBeing> superList = service.getAllSuperBeings();

                model.addAttribute("orgList", orgList);
                model.addAttribute("locationList", locationList);
                model.addAttribute("superList", superList);
                model.addAttribute("organization", organization);
                return "editOrganizationForm";
            }

            service.updateOrganization(organization);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error editOrganization.");
        }
        
        return "redirect:organizations";
    }

    @GetMapping("/locations")
    public String displayLocationsPage(Model model) {
        try {
            List<Location> locationList = service.getAllLocations();
            Location loc = new Location();
            model.addAttribute("locationList", locationList);
            model.addAttribute(loc);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error displayLocationsPage.");
        }
        
        return "locations";
    }

    @GetMapping("/locationDetails")
    public String displayLocationDetails(
            HttpServletRequest request, Model model) {

        try {
            String locationIdParameter = request.getParameter("locationId");
            int locationId = Integer.parseInt(locationIdParameter);

            Location location = service.getLocationById(locationId);

            model.addAttribute("location", location);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error displayLocationDetails.");
        }   

        return "locationDetails";
    }

    @PostMapping("/createLocation")
    public String createLocation(
            @Valid @ModelAttribute("location") Location location,
            BindingResult result,
            Model model) {

        try {
            if (result.hasErrors()) {
                model.addAttribute("locationList", service.getAllLocations());
                return "locations";
            }

            service.addLocation(location);

        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error createLocation.");
        }
        
        return "redirect:locations";
    }

    @GetMapping("/deleteLocation")
    public String deleteLocation(@RequestParam("locationId") int locationId) {
        try {
            service.deleteLocationById(locationId);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Location is tied to an organiztion.\n"
                    + "Unable to delete.");
        }
        
        return "redirect:locations";
    }

    @GetMapping("/editLocationForm")
    public String displayEditLocationForm(
            @RequestParam("locationId") int locationId, Model model) {

        try {
            Location location = service.getLocationById(locationId);
            model.addAttribute("location", location);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error displayEditLocation.");
        }
        
        return "editLocationForm";
    }

    @PostMapping("/editLocation")
    public String editLocation(
            @Valid @ModelAttribute("location") Location location,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("location", location);
            return "editLocationForm";
        }

        try {
            service.updateLocation(location);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error editLocation.");
        }
        
        return "redirect:locations";
    }

    @GetMapping("/sightings")
    public String displaySightingsPage(Model model) {
        try {
            List<Sighting> sightingList = service.getAllSightings();
            List<SuperBeing> superList = service.getAllSuperBeings();
            List<Location> locationList = service.getAllLocations();
            model.addAttribute("sightingList", sightingList);
            model.addAttribute("superList", superList);
            model.addAttribute("locationList", locationList);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error displaySightingsPage.");
        }
        
        return "sightings";
    }

    @GetMapping("/sightingDetails")
    public String displaySightingDetails(
            @RequestParam("sightingId") int sightingId, 
            Model model) {
        try {
            Sighting sighting = service.getSightingById(sightingId);
            model.addAttribute("sighting", sighting);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error displaySightingDetails.");
        }
        
        return "sightingDetails";
    }
    
    @PostMapping("/createSighting")
    public String createSighting(
            @RequestParam("select-location") int locationId,
            @RequestParam(value = "superIds", required = false) List<String> superIdList,
            @RequestParam("date") String date,
            Model model) {
        
        try {
            Sighting sighting = new Sighting();
        
            // Insert location into sighting
            Location location = service.getLocationById(locationId);
            sighting.setLocation(location);

            // Insert members into organization
            List<SuperBeing> supers = new ArrayList<>();

            if (superIdList != null) {
                for (String currentIdString : superIdList) {
                    int currentId = Integer.parseInt(currentIdString);
                    SuperBeing sb = service.getSuperBeingById(currentId);
                    supers.add(sb);
                }
            }

            sighting.setSuperBeings(supers);

            LocalDate newDate = LocalDate.parse(date);

            sighting.setDate(newDate);

            service.addSighting(sighting);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error createSighting.");
        }
        
        return "redirect:sightings";
    }
    
    @GetMapping("/deleteSighting")
    public String deleteSighting(@RequestParam("sightingId") int id) {
        try {
            service.deleteSighting(id);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error deleteSighting.");
        }
        return "redirect:sightings";
    }
    
    @GetMapping("/editSightingForm")
    public String displayEditSightingForm(
            @RequestParam("sightingId") int id, 
            Model model) {
        
        try {
            Sighting sighting = service.getSightingById(id);
            List<Location> locationList = service.getAllLocations();
            List<SuperBeing> superList = service.getAllSuperBeings();
            model.addAttribute("sighting", sighting);
            model.addAttribute("locationList", locationList);
            model.addAttribute("superList", superList);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error displaySightingForm.");
        }
        
        return "editSightingForm";
    }
    
    @PostMapping("/editSighting")
    public String editSighting(
            @RequestParam("sightingId") int sightingId,
            @RequestParam("select-location") int locationId,
            @RequestParam(value = "superIds", required = false) List<String> superIdList,
            @RequestParam("date") String date,
            Model model) {
        
        try {
            Sighting sighting = new Sighting();
        
            sighting.setSightingId(sightingId);

            Location location = service.getLocationById(locationId);
            sighting.setLocation(location);

            List<SuperBeing> supers = new ArrayList<>();
            if (superIdList != null) {
                for (String currentIdString : superIdList) {
                    int currentId = Integer.parseInt(currentIdString);
                    SuperBeing sb = service.getSuperBeingById(currentId);
                    supers.add(sb);
                }
            }
            sighting.setSuperBeings(supers);

            LocalDate newDate = LocalDate.parse(date);
            sighting.setDate(newDate);

            service.updateSighting(sighting);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error editSighting.");
        }
        
        
        return "redirect:sightings";
    }

    @PostMapping("/createPower")
    public String createPower(
            @RequestParam("power-description") String description) {
        
        try {
            Power power = new Power(description);
            service.addPower(power);
        } catch (SuperBeingPersistenceException e) {
            JOptionPane.showMessageDialog(null, "Error createPower.");
        }
        
        return "redirect:supers";
    }

}
