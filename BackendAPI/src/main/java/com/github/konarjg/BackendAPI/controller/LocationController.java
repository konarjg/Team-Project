package com.github.konarjg.BackendAPI.controller;

import com.github.konarjg.BackendAPI.entity.Location;
import com.github.konarjg.BackendAPI.requestBody.LocationDeleteRequest;
import com.github.konarjg.BackendAPI.requestBody.LocationRequest;
import com.github.konarjg.BackendAPI.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/fetch")
    public ResponseEntity<List<Location>> fetch() {
        List<Location> locations = locationService.findAll();

        if (locations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public ResponseEntity<Location> create(@RequestBody LocationRequest request) {
        Location location = new Location();
        location.setName(request.getName());

        if (!locationService.save(location)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(location, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/delete")
    public ResponseEntity<Location> delete(@RequestBody LocationDeleteRequest request) {
        Location location = locationService.findById(request.getLocationId());

        if (location == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!locationService.delete(location)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(location, HttpStatus.OK);
    }
}
