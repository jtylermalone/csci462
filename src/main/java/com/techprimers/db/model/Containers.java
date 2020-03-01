package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "containers")
public class Containers {

    //@Id
    //@GeneratedValue
    @Column(name = "id")
    @Id @GeneratedValue private Integer id;
    @Column(name = "Crane_Num")
    private Integer craneNumber;
    @Column(name = "Ship_Num")
    private Integer shipNumber;
    @Column(name = "Badge_Num")
    private Integer badge_number;
    @Column(name = "Transmitted_Datetime")
    private Date transmitted_datetime;
    @Column(name = "Driver_Shift_Number")
    private Integer driver_shift_number;
    
    public Containers(){
        this.craneNumber = 0;
        this.shipNumber = 0;
        this.badge_number = 0;
        this.transmitted_datetime = new Date();
        this.driver_shift_number = 0;
    }
    public Containers(Integer crane_number, Integer ship_number,
                        Integer badge_number, Date transmitted_datetime, Integer driver_shift_number) {

        this.craneNumber = crane_number;
        this.shipNumber = ship_number;
        this.badge_number = badge_number;
        this.transmitted_datetime = transmitted_datetime;
        this.driver_shift_number = driver_shift_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCraneNumber() {
        return craneNumber;
    }

    public void setCraneNumber(Integer craneNumber) {
        this.craneNumber = craneNumber;
    }

    public Integer getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(Integer shipNumber) {
        this.shipNumber = shipNumber;
    }

    public Integer getBadgeNumber() {
        return badge_number;
    }

    public void setBadgeNumber(Integer badge_number) {
        this.badge_number = badge_number;
    }

    public Date getTransmittedDatetime() {
        return transmitted_datetime;
    }

    public void setTransmittedDatetime(Date transmitted_datetime) {
        this.transmitted_datetime = transmitted_datetime;
    }

    public Integer getDriverShiftNumber() {
        return driver_shift_number;
    }

    public void setDriverShiftNumber(Integer driver_shift_number) {
        this.driver_shift_number = driver_shift_number;
    }

    public String toString() {
        return String.format(
            "Container[record_number=%d, crane_number=%s, ship_number=%s, badge_number=%d", id, craneNumber, shipNumber, badge_number
        );
    }
}
