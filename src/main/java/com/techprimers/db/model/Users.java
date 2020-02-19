package com.techprimers.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Users {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "teamname")
    private String teamName;
    @Column(name = "salary")
    private Integer salary;
    
    public Users(){
        this.name = "default";
        this.teamName = "default";
        this.salary = 0;
    }
    public Users(String name, String teamName, int salary) {
        this.name = name;
        this.teamName = teamName;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String toString() {
        return String.format(
            "User[id=%d, name=%s, teamname=%s, salary=%d]", id, name, teamName, salary
        );
    }
}
