package com.scpa.db.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "employees")
public class Employees {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;
    @Column(name = "dept")
    private String dept;
    @Column(name = "dept_name")
    private String dept_name;
    @Column(name = "job_title")
    private String job_title;
    
    public Employees(){
        this.name = "-1";
        this.dept = "-1";
        this.dept_name = "-1";
        this.job_title = "-1";
    }
    public Employees(String name,
                        String dept, String dept_name, String job_title) {


        this.name = name;
        this.dept = dept;
        this.dept_name = dept_name;
        this.job_title = job_title;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDept() {
        return dept;
    }

    public String getDeptName() {
        return dept_name;
    }

    public String getJobTitle() {
        return job_title;
    }

    public String toString() {
        return String.format(
            "Container[id=%s, name=%s, dept=%s, dept_name=%s, job_title=%s", id, name, dept, dept_name, job_title
        );
    }
}
