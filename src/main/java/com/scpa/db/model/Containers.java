package com.scpa.db.model;

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
    private String craneNumber;
    @Column(name = "Ship_Num")
    private String shipNumber;
    @Column(name = "Badge_Num")
    private String badgeNumber;
    @Column(name = "Transmitted_Datetime")
    private Date transmitted_datetime;
    @Column(name = "Driver_Shift_Number")
    private String driver_shift_number;
    
    public Containers(){
        this.craneNumber = "";
        this.shipNumber = "";
        this.badgeNumber = "";
        this.transmitted_datetime = new Date();
        this.driver_shift_number = "";
    }
    public Containers(String crane_number, String ship_number,
                        String badge_number, Date transmitted_datetime, String driver_shift_number) {

        this.craneNumber = crane_number;
        this.shipNumber = ship_number;
        this.badgeNumber = badge_number;
        this.transmitted_datetime = transmitted_datetime;
        this.driver_shift_number = driver_shift_number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCraneNumber() {
        return craneNumber;
    }

    public void setCraneNumber(String craneNumber) {
        this.craneNumber = craneNumber;
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Date getTransmittedDatetime() {
        return transmitted_datetime;
    }

    public void setTransmittedDatetime(Date transmitted_datetime) {
        this.transmitted_datetime = transmitted_datetime;
    }

    public String getDriverShiftNumber() {
        return driver_shift_number;
    }

    public void setDriverShiftNumber(String driver_shift_number) {
        this.driver_shift_number = driver_shift_number;
    }

    public String toString() {
        return String.format(
            "Container[record_number=%d, crane_number=%s, ship_number=%s, badge_number=%d", id, craneNumber, shipNumber, badgeNumber
        );
    }
}
