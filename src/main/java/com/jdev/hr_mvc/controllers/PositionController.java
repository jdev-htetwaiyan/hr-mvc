package com.jdev.hr_mvc.controllers;

import com.jdev.hr_mvc.models.Position;
import com.jdev.hr_mvc.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/position")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {

        this.positionService = positionService;
    }

    @PostMapping("/create")
    public ResponseEntity<Position> create(@RequestBody Position position) {
        return ResponseEntity.ok(positionService.create(position));
    }

    @GetMapping("/list")
    public List<Position> getAllPosition() {
        return positionService.getAllPosition();
    }

    @GetMapping("/{id}")
    public Position getById(@PathVariable long id) {
        return positionService.getById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Position> update(@RequestBody Position position, @PathVariable long id) {
        return ResponseEntity.ok(positionService.update(position, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        positionService.delete(id);
        return ResponseEntity.ok("Deleted");
    }

}
