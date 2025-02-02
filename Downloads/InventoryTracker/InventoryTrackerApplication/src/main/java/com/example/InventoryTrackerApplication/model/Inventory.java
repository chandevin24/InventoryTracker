package com.example.InventoryTrackerApplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;


@Entity
@Table(name="Inventory")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @Column
    private Long count;

}