package com.github.dronezcc.riser.gui.repository;

import com.github.dronezcc.riser.gui.domain.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends CrudRepository<MenuItem, Integer> {


}
