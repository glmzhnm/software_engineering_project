package com.galymzhan.finalspring.controller;

import com.galymzhan.finalspring.dto.PresidentDto;
import com.galymzhan.finalspring.service.PresidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/presidents")

public class PresidentController {

    private final PresidentService presidentService;

    @GetMapping
    public ResponseEntity<List<PresidentDto>> getAll() {
        return ResponseEntity.ok(presidentService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PresidentDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(presidentService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<PresidentDto> create(@RequestBody PresidentDto dto) {
        return ResponseEntity.ok(presidentService.addPresident(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<PresidentDto> update(@PathVariable Long id, @RequestBody PresidentDto dto) {
        return ResponseEntity.ok(presidentService.updatePresident(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        presidentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}