package com.github.dronezcc.riser.gui.repository;

import com.github.dronezcc.riser.gui.domain.Token;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long>{
    Token findByIdentity(Long token);
}
