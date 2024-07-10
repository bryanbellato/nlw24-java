package com.rocketseat.planner.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

    @Autowired
    private ParticipantRepository repository;

    @PostMapping("/{id}/confirmed")
    public ResponseEntity<Participant>confirmParticipant(@PathVariable UUID id, @RequestBody ParticipantRequestPayload payload) {
        Optional<Participant> participant = this.repository.findById(id);

        if(participant .isPresent()){
            Participant rowParticipant = participant.get();
            rowParticipant .setIsConfirmed(true);
            rowParticipant .setName(payload.name());

            this.repository.save(rowParticipant);

            return ResponseEntity.ok(rowParticipant);
        }

        return ResponseEntity.notFound().build();
    }
}
