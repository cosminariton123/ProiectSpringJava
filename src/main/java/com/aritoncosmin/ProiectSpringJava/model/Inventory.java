package com.aritoncosmin.ProiectSpringJava.model;

import javax.persistence.*;

@Entity
@Table(name = "Inventories")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;

    @Column(name = "radiator")
    private Integer radiatorQuantity;

    @Column(name = "engine")
    private Integer engineQuantity;

    @Column(name = "fan")
    private Integer fanQuantity;

    @Column(name = "windshield")
    private Integer windshieldQuantity;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public Integer getRadiatorQuantity() {
        return radiatorQuantity;
    }

    public void setRadiatorQuantity(Integer radiatorQuantity) {
        this.radiatorQuantity = radiatorQuantity;
    }

    public Integer getEngineQuantity() {
        return engineQuantity;
    }

    public void setEngineQuantity(Integer engineQuantity) {
        this.engineQuantity = engineQuantity;
    }

    public Integer getFanQuantity() {
        return fanQuantity;
    }

    public void setFanQuantity(Integer fanQuantity) {
        this.fanQuantity = fanQuantity;
    }

    public Integer getWindshieldQuantity() {
        return windshieldQuantity;
    }

    public void setWindshieldQuantity(Integer windshieldQuantity) {
        this.windshieldQuantity = windshieldQuantity;
    }
}
