package com.jdev.hr_mvc.services.Impl;

import com.jdev.hr_mvc.Exceptions.PositionAlreadyExistsException;
import com.jdev.hr_mvc.Exceptions.ResourceNotFoundException;
import com.jdev.hr_mvc.models.Position;
import com.jdev.hr_mvc.repositories.PositionRepository;
import com.jdev.hr_mvc.services.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {

        this.positionRepository = positionRepository;
    }

    @Override
    public Position create(Position position) {

        if (positionRepository.existsByTitle(position.getTitle())) {
            throw new PositionAlreadyExistsException("Position " + position.getTitle() + " already exists");
        }

        return positionRepository.save(position);
    }

    @Override
    public List<Position> getAllPosition() {

        return positionRepository.findAll();
    }

    @Override
    public Position getById(long id) {

        return positionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no position with ID " + id));
    }

    @Override
    public Position update(Position position, long id) {

        Position existingPosition = positionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is no position with ID " + id));

        existingPosition.setTitle(position.getTitle());

        return positionRepository.save(existingPosition);
    }

    @Override
    public void delete(long id) {

        positionRepository.deleteById(id);
    }

}
