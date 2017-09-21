package com.github.dronezcc.riser.gui.module.customers;

import javax.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;


}
