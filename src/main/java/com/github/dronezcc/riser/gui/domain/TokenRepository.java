package com.github.dronezcc.riser.gui.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<Token, Long>{

    Token findByIdentity(Long token);
}
