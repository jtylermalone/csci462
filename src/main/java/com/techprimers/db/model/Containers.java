package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Containers {

    @Id
    @GeneratedValue
    @Column(name = "Record_Number")
    private Integer record_number;
    @Column(name = "Crane_Num")
    private Integer crane_number;
    @Column(name = "Ship_Num")
    private Integer ship_number;
    @Column(name = "Badge_Num")
    private Integer badge_number;
    @Column(name = "Transmitted_Datetime")
    private Date transmitted_datetime;
    @Column(name = "Driver_Shift_Number")
    private Double driver_shift_number;
    
    public Containers(){
        this.record_number = 0;
        this.crane_number = 0;
        this.ship_number = 0;
        this.badge_number = 0;
        this.transmitted_datetime = new Date();
        this.driver_shift_number = 0.0;
    }
    public Containers(Integer record_number, Integer crane_number, Integer ship_number,
                        Integer badge_number, Date transmitted_datetime, Double driver_shift_number) {
        this.record_number = record_number;
        this.crane_number = crane_number;
        this.ship_number = ship_number;
        this.badge_number = badge_number;
        this.transmitted_datetime = transmitted_datetime;
        this.driver_shift_number = driver_shift_number;
    }

    public Integer getRecordNumber() {
        return record_number;
    }

    public void setRecordNumber(Integer record_number) {
        this.record_number = record_number;
    }

    public Integer getCraneNumber() {
        return crane_number;
    }

    public void setCraneNumber(Integer crane_number) {
        this.crane_number = crane_number;
    }

    public Integer getShipNumber() {
        return ship_number;
    }

    public void setShipNumber(Integer ship_number) {
        this.ship_number = ship_number;
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

    public Double getDriverShiftNumber() {
        return driver_shift_number;
    }

    public void setDriverShiftNumber(Double driver_shift_number) {
        this.driver_shift_number = driver_shift_number;
    }

    public String toString() {
        return String.format(
            "User[record_number=%d, crane_number=%s, ship_number=%s, badge_number=%d", record_number, crane_number, ship_number, badge_number
        );
    }
}
