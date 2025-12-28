package com.galymzhan.finalspring.controller;

import com.galymzhan.finalspring.dto.CountryDto;
import com.galymzhan.finalspring.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<?> getAll(){
        return new ResponseEntity<>(countryService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable(name = "id") Long id){
        return new ResponseEntity<>(countryService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addCountry(@RequestBody CountryDto countryDto){
        countryService.createCountry(countryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCountry(@PathVariable(name = "id") Long id, @RequestBody CountryDto countryDto){
        countryService.updateCountry(id, countryDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable(name = "id") Long id){
        countryService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
