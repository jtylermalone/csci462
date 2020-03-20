package com.scpa.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "vessel")
public class Vessel {

    @Id
    @GeneratedValue
    @Column(name = "VR_VESSEL_FIRST_FO")
    private String first_fo;
    @Column(name = "VR_VESSEL_NAME")
    private String vessel_name;
    @Column(name = "VR_LINE_CODE")
    private String line_code;
    @Column(name = "VR_LINE_NAME")
    private String line_name;
    
    public Vessel(){
        this.first_fo = "";
        this.vessel_name = "";
        this.line_code = "";
        this.line_name = "";
    }

    public Vessel(String first_fo, String vessel_name,
                        String line_code, String line_name) {

        this.first_fo = first_fo;
        this.vessel_name = vessel_name;
        this.line_code = line_code;
        this.line_name = line_name;
    }

    public String getFirstFo() {
        return first_fo;
    }

    public String getVesselName() {
        return vessel_name;
    }

    public String getLineCode() {
        return line_code;
    }

    public String getLineName() {
        return line_name;
    }

    public String toString() {
        return String.format(
            "Container[first_fo=%s, vessel_name=%s, line_code=%s, line_name=%s", first_fo, vessel_name, line_code, line_name
        );
    }
}
