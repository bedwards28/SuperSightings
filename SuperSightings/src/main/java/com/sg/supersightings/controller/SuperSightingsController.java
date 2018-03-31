package com.sg.supersightings.controller;

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
        return service.getMostRecentSightings();
    }
    
    @GetMapping("/index")
    public String displayIndex(Model model) {
        List<Sighting> sightingList = service.getMostRecentSightings();
        model.addAttribute("sightingList", sightingList);
        return "index";
    }

    @GetMapping("/supers")
    public String displaySuperBeingsPage(Model model) {
        List<SuperBeing> superList = service.getAllSuperBeings();
        List<Power> powerList = service.getAllPowers();
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

        SuperBeing sb = service.getSuperBeingById(superId);

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
        } catch (NullPointerException e) {
            // do nothing, hasErrors will return errors
        }

        if (result.hasErrors()) {
            model.addAttribute("powerList", service.getAllPowers());
            model.addAttribute("superList", service.getAllSuperBeings());
            model.addAttribute("superBeing", sb);
            return "superBeings";
        }

        service.addSuperBeing(sb);

        return "redirect:supers";
    }

    @GetMapping("/deleteSuperBeing")
    public String deleteSuperBeing(HttpServletRequest request) {
        String superIdParameter = request.getParameter("superId");
        int superId = Integer.parseInt(superIdParameter);
        service.deleteSuperBeing(superId);
        return "redirect:supers";
    }

    @GetMapping("/editSuperBeingForm")
    public String displayEditSuperBeingForm(
            HttpServletRequest request, Model model) {

        String superIdParameter = request.getParameter("superId");
        int superId = Integer.parseInt(superIdParameter);
        SuperBeing sb = service.getSuperBeingById(superId);
        model.addAttribute("superBeing", sb);
        model.addAttribute("powerList", service.getAllPowers());
        return "editSuperBeingForm";
    }

    @PostMapping("/editSuperBeing")
    public String editSuperBeing(
            @Valid @ModelAttribute("superBeing") SuperBeing sb,
            BindingResult result,
            Model model) {

        List<Power> powers = sb.getPowers();
        List<Power> newPowers = new ArrayList<>();

        if (powers.size() > 0) {
            for (Power currentPower : powers) {
                Power newPower
                        = service.getPowerByDescription(currentPower.getDescription());

                newPowers.add(newPower);
            }

            sb.setPowers(newPowers);
        }

        if (result.hasErrors()) {
            model.addAttribute("powerList", service.getAllPowers());
            model.addAttribute("superBeing", sb);
            return "editSuperBeingForm";
        }

        service.updateSuperBeing(sb);

        return "redirect:supers";
    }

    @GetMapping("/organizations")
    public String displayOrganizationsPage(Model model) {
        List<Organization> orgList = service.getAllOrganizations();
        List<Location> locationList = service.getAllLocations();
        List<SuperBeing> superList = service.getAllSuperBeings();
        Organization organization = new Organization();

        model.addAttribute("orgList", orgList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superList", superList);
        model.addAttribute("organization", organization);
        return "organizations";
    }

    @GetMapping("/organizationDetails")
    public String displayOrganizationDetails(
            HttpServletRequest request, Model model) {

        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);

        Organization org = service.getOrganizationById(organizationId);

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
            return "organizations";
        }

        service.addOrganization(organization);

        return "redirect:organizations";
    }

    @GetMapping("/deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        service.deleteOrganization(organizationId);
        return "redirect:organizations";
    }

    @GetMapping("/editOrganizationForm")
    public String displayEditOrganizationForm(
            HttpServletRequest request, Model model) {
        
        String organizationIdParamter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParamter);
        Organization organization = service.getOrganizationById(organizationId);
        
        List<Location> locationList = service.getAllLocations();
        List<SuperBeing> superList = service.getAllSuperBeings();
        
        model.addAttribute("superList", superList);
        model.addAttribute("locationList", locationList);
        model.addAttribute("organization", organization);
        return "editOrganizationForm";
    }
    
    @PostMapping("/editOrganization")
    public String editOrganization(
            @Valid @ModelAttribute("organization") Organization organization, 
            BindingResult result, 
            @RequestParam("select-location") int locationId,
            @RequestParam(value = "memberIds", required = false) List<String> memberIdList,
            Model model) {
        
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
        
        return "redirect:organizations";
    }

    @GetMapping("/locations")
    public String displayLocationsPage(Model model) {
        List<Location> locationList = service.getAllLocations();
        Location loc = new Location();
        model.addAttribute("locationList", locationList);
        model.addAttribute(loc);
        return "locations";
    }

    @GetMapping("/locationDetails")
    public String displayLocationDetails(
            HttpServletRequest request, Model model) {

        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);

        Location location = service.getLocationById(locationId);

        model.addAttribute("location", location);

        return "locationDetails";
    }

    @PostMapping("/createLocation")
    public String createLocation(
            @Valid @ModelAttribute("location") Location location,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("locationList", service.getAllLocations());
            return "locations";
        }

        service.addLocation(location);

        return "redirect:locations";
    }

    @GetMapping("/deleteLocation")
    public String deleteLocation(HttpServletRequest request) {
        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        service.deleteLocationById(locationId);
        return "redirect:locations";
    }

    @GetMapping("/editLocationForm")
    public String displayEditLocationForm(
            HttpServletRequest request, Model model) {

        String locationIdParameter = request.getParameter("locationId");
        int locationId = Integer.parseInt(locationIdParameter);
        Location location = service.getLocationById(locationId);
        model.addAttribute("location", location);
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

        service.updateLocation(location);

        return "redirect:locations";
    }

    @GetMapping("/sightings")
    public String displaySightingsPage(Model model) {
        List<Sighting> sightingList = service.getAllSightings();
        List<SuperBeing> superList = service.getAllSuperBeings();
        List<Location> locationList = service.getAllLocations();
        model.addAttribute("sightingList", sightingList);
        model.addAttribute("superList", superList);
        model.addAttribute("locationList", locationList);
        return "sightings";
    }

    @GetMapping("/sightingDetails")
    public String displaySightingDetails(HttpServletRequest request, Model model) {
        String sightingIdParameter = request.getParameter("sightingId");
        int sightingId = Integer.parseInt(sightingIdParameter);
        Sighting sighting = service.getSightingById(sightingId);
        model.addAttribute("sighting", sighting);
        return "sightingDetails";
    }
    
    @PostMapping("/createSighting")
    public String createSighting(
            @RequestParam("select-location") int locationId,
            @RequestParam(value = "superIds", required = false) List<String> superIdList,
            @RequestParam("date") String date,
            Model model) {
        
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
        
        return "redirect:sightings";
    }
    
    @GetMapping("/deleteSighting")
    public String deleteSighting(@RequestParam("sightingId") int id) {
        service.deleteSighting(id);
        return "redirect:sightings";
    }
    
    @GetMapping("/editSightingForm")
    public String displayEditSightingForm(
            @RequestParam("sightingId") int id, 
            Model model) {
        
        Sighting sighting = service.getSightingById(id);
        List<Location> locationList = service.getAllLocations();
        List<SuperBeing> superList = service.getAllSuperBeings();
        model.addAttribute("sighting", sighting);
        model.addAttribute("locationList", locationList);
        model.addAttribute("superList", superList);
        return "editSightingForm";
    }
    
    @PostMapping("/editSighting")
    public String editSighting(
            @RequestParam("sightingId") int sightingId,
            @RequestParam("select-location") int locationId,
            @RequestParam(value = "superIds", required = false) List<String> superIdList,
            @RequestParam("date") String date,
            Model model) {
        
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
        
        return "redirect:sightings";
    }

    @PostMapping("/createPower")
    public String createPower(
            @RequestParam("power-description") String description) {
        
        Power power = new Power(description);
        service.addPower(power);
        return "redirect:supers";
    }

}
