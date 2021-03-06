package com.issinc.pialamodes.persistence;

import com.issinc.pialamodes.domain.Position;
import com.issinc.pialamodes.domain.PositionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Created by jay.moss on 10/30/2015.
 */
public interface PositionRepository extends JpaRepository<Position, PositionId> {

    @Modifying
    @Transactional
    @Query("delete from Position pos where pos.positionId.hexIdent = ?1")
    int deleteByHexIdent(String hexIdent);

    Position findByPositionId(PositionId positionId);
}
