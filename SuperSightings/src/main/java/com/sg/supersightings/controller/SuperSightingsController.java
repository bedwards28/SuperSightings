package com.sg.supersightings.controller;

import com.sg.supersightings.model.Sighting;
import com.sg.supersightings.model.SuperBeing;
import com.sg.supersightings.service.SuperSightingsServiceLayer;
import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class SuperSightingsController {
    
    @Inject
    private SuperSightingsServiceLayer service;
        
    public SuperSightingsController() {
    }
    
    @GetMapping("/sightings")
    public List<Sighting> getAllSightings() {
        return service.getAllSightings();
    }
    
    @GetMapping("/super/{id}")
    public SuperBeing getSuperBeing(@PathVariable("superId") int id) {
        return service.getSuperBeingById(id);
    }
    
//    @GetMapping("/")
//    public String showIndex() {
//        return "index";
//    }
    
//    @RequestMapping(value="/sayhi", method=RequestMethod.GET)
//    public String sayHi(Map<String, Object> model) {
//        model.put("message", "Hello from the controller" );
//        return "hello";
//    }
    
    
}
