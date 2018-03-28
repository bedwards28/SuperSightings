package com.sg.supersightings.controller;

import com.sg.supersightings.model.Location;
import com.sg.supersightings.model.Organization;
import com.sg.supersightings.model.Power;
import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperBeing;
import com.sg.supersightings.service.SuperSightingsServiceLayer;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@CrossOrigin
//@RestController
@Controller
public class SuperSightingsController {

    @Inject
    private SuperSightingsServiceLayer service;

    public SuperSightingsController() {
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayHomePage(Model model) {
        // add newsfeed items to model
        System.out.println("hello");
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
        model.addAttribute("orgList", orgList);
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
    public String createOrganization(HttpServletRequest request) {
        Organization org = new Organization();
        Location loc = new Location();

        loc.setName(request.getParameter("name"));
        loc.setDescription(request.getParameter("description"));
        loc.setAddressLine1(request.getParameter("addressLine1"));
        loc.setAddressLine2(request.getParameter("addressLine2"));
        loc.setCity(request.getParameter("city"));
        loc.setRegion(request.getParameter("region"));
        loc.setPostalCode(request.getParameter("postalCode"));
        loc.setCountry(request.getParameter("country"));

        String latitudeParameter = request.getParameter("latitude");
        Double latitude = Double.parseDouble(latitudeParameter);
        loc.setLatitude(latitude);

        String longitudeParameter = request.getParameter("longitude");
        Double longitude = Double.parseDouble(longitudeParameter);
        loc.setLongitude(longitude);

        service.addLocation(loc);

        org.setDescription(request.getParameter("description"));
        org.setLocation(loc);
        org.setPhone(request.getParameter("phone"));
        org.setEmail(request.getParameter("email"));

        service.addOrganization(org);

        return "redirect:organizations";
    }

    @GetMapping("/deleteOrganization")
    public String deleteOrganization(HttpServletRequest request) {
        String organizationIdParameter = request.getParameter("organizationId");
        int organizationId = Integer.parseInt(organizationIdParameter);
        service.deleteOrganization(organizationId);
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
    public String createLocation(HttpServletRequest request) {
        Location location = new Location();
        location.setName(request.getParameter("name"));
        location.setDescription(request.getParameter("description"));
        location.setAddressLine1(request.getParameter("addressLine1"));
        location.setAddressLine2(request.getParameter("addressLine2"));
        location.setCity(request.getParameter("city"));
        location.setRegion(request.getParameter("region"));
        location.setPostalCode(request.getParameter("postalCode"));
        location.setCountry(request.getParameter("country"));

        String latitudeParameter = request.getParameter("latitude");
        Double latitude = Double.parseDouble(latitudeParameter);
        location.setLatitude(latitude);

        String longitudeParameter = request.getParameter("longitude");
        Double longitude = Double.parseDouble(longitudeParameter);
        location.setLongitude(longitude);

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

    @GetMapping("/sightings")
    public String displaySightingsPage(Model model) {
        List<Sighting> sightingList = service.getAllSightings();
        model.addAttribute("sightingList", sightingList);
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

    @GetMapping("/addPowerForm")
    public String displayAddPowerForm() {
        return "addPowerForm";
    }

}
