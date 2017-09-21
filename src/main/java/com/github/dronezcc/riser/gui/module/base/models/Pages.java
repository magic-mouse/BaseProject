package com.github.dronezcc.riser.gui.module.base.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pages {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

}
