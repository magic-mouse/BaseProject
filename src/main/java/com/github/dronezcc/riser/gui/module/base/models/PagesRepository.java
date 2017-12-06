package com.github.dronezcc.riser.gui.module.base.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagesRepository extends CrudRepository<Pages, Integer> {
    Pages findByPath(String p);
}
