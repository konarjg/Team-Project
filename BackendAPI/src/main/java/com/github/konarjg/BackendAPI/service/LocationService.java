package com.github.konarjg.BackendAPI.service;

import com.github.konarjg.BackendAPI.entity.Location;
import com.github.konarjg.BackendAPI.repository.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location findById(long id) {
        return locationRepository.findById(id).orElse(null);
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public boolean save(Location location) {
        try {
            locationRepository.save(location);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }

    public boolean delete(Location location) {
        try {
            locationRepository.delete(location);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
