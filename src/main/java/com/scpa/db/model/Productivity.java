package com.scpa.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;
@Entity
@Table(name = "container_view_2")
public class Productivity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "MinOfTransmittedDatetime")
    private Date minOfTransmitted_Datetime;
    @Column(name = "MaxOfTransmittedDatetime")
    private Date maxOfTransmitted_Datetime;
    @Column(name = "Badge_Number2")
    private String badgeNumber;
    @Column(name = "driver_shift_number")
    private String driverShiftNumber;
    @Column(name = "ship_number")
    private String shipNumber;
    @Column(name = "Crane_Num")
    private String craneNumber;
    @Column(name = "total_driver_shifts")
    private Integer totalDriverShifts;
    @Column(name = "Cont_Moves")
    private Integer contMoves;
    @Column(name = "Hatchcover_Moves")
    private Integer hatchcoverMoves;
    @Column(name = "Calendar_Year")
    private Integer calendarYear;
    @Column(name = "CY_Month_Sort")
    private Integer cyMonthSort;
    @Column(name = "FY_Month_Sort")
    private Integer fyMonthSort;
    @Column(name = "Fiscal_Year")
    private Integer fiscalYear;
    @Column(name = "Day_of_Week")
    private Integer dayOfWeek;

    public Productivity(){
        this.minOfTransmitted_Datetime = new Date();
        this.maxOfTransmitted_Datetime = new Date();
        this.badgeNumber = "";
        this.driverShiftNumber = "";
        this.shipNumber = "";
        this.craneNumber = "";
        this.totalDriverShifts = 0;
        this.contMoves = 0;
        this.hatchcoverMoves = 0;
        this.calendarYear = 0;
        this.cyMonthSort = 0;
        this.fyMonthSort = 0;
        this.fiscalYear = 0;
        this.dayOfWeek = 0;
    }

    public Productivity(Date minOfTransmitted_Datetime, Date maxOfTransmitted_Datetime, String badgeNumber, 
                        String driverShiftNumber, String shipNumber, String craneNumber, Integer totalDriverShifts,
                        Integer contMoves, Integer hatchcoverMoves, Integer calendarYear,
                        Integer cyMonthSort, Integer fyMonthSort, Integer fiscalYear, Integer dayOfWeek) {

        this.minOfTransmitted_Datetime = minOfTransmitted_Datetime;
        this.maxOfTransmitted_Datetime = maxOfTransmitted_Datetime;
        this.badgeNumber = badgeNumber;
        this.driverShiftNumber = driverShiftNumber;
        this.shipNumber = shipNumber;
        this.craneNumber = craneNumber;
        this.totalDriverShifts = totalDriverShifts;
        this.contMoves = contMoves;
        this.hatchcoverMoves = hatchcoverMoves;
        this.calendarYear = calendarYear;
        this.cyMonthSort = cyMonthSort;
        this.fyMonthSort = fyMonthSort;
        this.fiscalYear = fiscalYear;
        this.dayOfWeek = dayOfWeek;
    }

    public Date getMinOfTransmitted_Datetime() {
        return minOfTransmitted_Datetime;
    }

    public void setMinOfTransmitted_Datetime(Date minOfTransmitted_Datetime) {
        this.minOfTransmitted_Datetime = minOfTransmitted_Datetime;
    }

    public Date getMaxOfTransmitted_Datetime() {
        return maxOfTransmitted_Datetime;
    }

    public void setMaxOfTransmittedDatetime(Date maxOfTransmitted_Datetime) {
        this.maxOfTransmitted_Datetime = maxOfTransmitted_Datetime;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public String getDriverShiftNumber() {
        return driverShiftNumber;
    }

    public void setDriverShiftNumber(String driverShiftNumber) {
        this.driverShiftNumber = driverShiftNumber;
    }

    public String getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(String shipNumber) {
        this.shipNumber = shipNumber;
    }

    public String getCraneNumber() {
        return craneNumber;
    }

    public void setCraneNumber(String craneNumber) {
        this.craneNumber = craneNumber;
    }

    public Integer getTotalDriverShifts() {
        return totalDriverShifts;
    }

    public void setTotalDriverShifts(Integer totalDriverShifts) {
        this.totalDriverShifts = totalDriverShifts;
    }

    public Integer getContMoves() {
        return contMoves;
    }

    public void setContMoves(Integer contMoves) {
        this.contMoves = contMoves;
    }

    public Integer getHatchcoverMoves() {
        return hatchcoverMoves;
    }

    public void setHatchcoverMoves(Integer hatchcoverMoves) {
        this.hatchcoverMoves = hatchcoverMoves;
    }

    public Integer getCalendarYear() {
        return calendarYear;
    }

    public void setCalendarYear(Integer calendarYear) {
        this.calendarYear = calendarYear;
    }

    public Integer getCyMonthSort() {
        return cyMonthSort;
    }

    public void setCyMonthSort(Integer cyMonthSort) {
        this.cyMonthSort = cyMonthSort;
    }

    public Integer getFyMonthSort() {
        return fyMonthSort;
    }

    public void setFyMonthSort(Integer fyMonthSort) {
        this.fyMonthSort = fyMonthSort;
    }

    public Integer getFiscalYear() {
        return fiscalYear;
    }

    public void setFiscalYear(Integer fiscalYear) {
        this.fiscalYear = fiscalYear;
    }

    public Integer getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(Integer dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}