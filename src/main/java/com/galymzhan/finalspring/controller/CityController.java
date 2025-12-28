package com.galymzhan.finalspring.controller;

import com.galymzhan.finalspring.dto.CityDto;
import com.galymzhan.finalspring.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/city")
public class CityController {
    private final CityService cityService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(cityService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(cityService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCity(@RequestBody CityDto cityDto){
        cityService.createCity(cityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCity(@PathVariable(name = "id") Long id, @RequestBody CityDto cityDto){
        cityService.updateCity(id, cityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCity(@PathVariable(name = "id") Long id){
        cityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
