package com.github.dronezcc.riser.gui.repository;

import com.github.dronezcc.riser.gui.domain.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TokenRepository extends CrudRepository<Token, UUID>{
    Token findByIdentity(UUID token);

    List<Token> findByUserIdAndUsed(long userId, boolean used);
}
