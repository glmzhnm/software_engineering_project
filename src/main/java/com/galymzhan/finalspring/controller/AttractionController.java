package com.galymzhan.finalspring.controller;

import com.galymzhan.finalspring.dto.AttractionDto;
import com.galymzhan.finalspring.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/attractions")
public class AttractionController {

    private final AttractionService attractionService;

    @GetMapping
    public ResponseEntity<List<AttractionDto>> getAll() {
        return ResponseEntity.ok(attractionService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttractionDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(attractionService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<AttractionDto> create(@RequestBody AttractionDto dto) {
        return ResponseEntity.ok(attractionService.addAttraction(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER', 'ROLE_ADMIN')")
    public ResponseEntity<AttractionDto> update(@PathVariable Long id, @RequestBody AttractionDto dto) {
        return ResponseEntity.ok(attractionService.updateAttraction(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        attractionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}