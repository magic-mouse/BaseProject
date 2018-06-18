package com.github.dronezcc.riser.gui.module.base.models.repository;

import com.github.dronezcc.riser.gui.module.base.models.domain.Pages;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagesRepository extends CrudRepository<Pages, Integer> {
    Pages findByPath(String p);

    void deleteById(Integer id);
}
