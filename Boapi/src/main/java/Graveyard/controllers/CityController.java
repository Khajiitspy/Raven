package Graveyard.controllers;

import Graveyard.data.dto.city.CityCreateDTO;
import Graveyard.data.dto.city.CityItemDTO;
import Graveyard.services.CityService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @Operation(summary = "Створити нове місто")
    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CityItemDTO> create(@ModelAttribute CityCreateDTO dto) {
        return ResponseEntity.ok(cityService.create(dto));
    }
}
