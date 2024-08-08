package com.jdev.hr_mvc.services;

import com.jdev.hr_mvc.models.Position;

import java.util.List;

public interface PositionService {

    Position create(Position position);

    List<Position> getAllPosition();

    Position getById(long id);

    Position update(Position position, long id);

    void delete(long id);
}
