package com.issinc.pialamodes.service;

import com.issinc.pialamodes.domain.Position;
import com.issinc.pialamodes.domain.PositionId;
import com.issinc.pialamodes.persistence.PositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  Created by jay.moss on 11/4/2015.
 */
@Service
public class PositionService implements IPositionService {

    private PositionRepository positionRepo;
    private static final Logger log = LoggerFactory.getLogger(PositionService.class);

    @Autowired
    public PositionService(PositionRepository repo) {
        this.positionRepo = repo;
    }

    @Override
    @Transactional
    public Position create(Position position) {
        Position result;

        Position existing = positionRepo.findByPositionId(new PositionId(position.getHexIdent(), position.getTimestamp()));
        if (existing != null) {
            log.warn("attempted insert of existing position, hexIdent: " + position.getHexIdent());
            result = existing;
        }
        else {
            result = positionRepo.save(position);
        }
        return result;
    }

    @Override
    @Transactional
    public Position create(String hexIdent, Double lat, Double lon, Double heading, Double groundSpeed, Double verticalRate) {
        return positionRepo.save(
            new Position(hexIdent, lat, lon, heading, groundSpeed, verticalRate));
    }

    @Override
    @Transactional
    public List<Position> find() {
        return positionRepo.findAll();
    }

    @Override
    @Transactional
    public List<Position> findLast(String hexIdent, Integer numberOfPositions) {
        return null;
    }
}
