package com.zoloti.trend_talk.rest;

import com.zoloti.trend_talk.model.GuestDTO;
import com.zoloti.trend_talk.service.GuestService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/guests", produces = MediaType.APPLICATION_JSON_VALUE)
public class GuestResource {

    private final GuestService guestService;

    public GuestResource(final GuestService guestService) {
        this.guestService = guestService;
    }

    @GetMapping
    public ResponseEntity<List<GuestDTO>> getAllGuests() {
        return ResponseEntity.ok(guestService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuestDTO> getGuest(@PathVariable(name = "id") final Long id) {
        return ResponseEntity.ok(guestService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createGuest(@RequestBody @Valid final GuestDTO guestDTO) {
        final Long createdId = guestService.create(guestDTO);
        return new ResponseEntity<>(createdId, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Long> updateGuest(@PathVariable(name = "id") final Long id,
            @RequestBody @Valid final GuestDTO guestDTO) {
        guestService.update(id, guestDTO);
        return ResponseEntity.ok(id);
    }

    /*
    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteGuest(@PathVariable(name = "id") final Long id) {
        guestService.delete(id);
        return ResponseEntity.noContent().build();
    }
     */
}
