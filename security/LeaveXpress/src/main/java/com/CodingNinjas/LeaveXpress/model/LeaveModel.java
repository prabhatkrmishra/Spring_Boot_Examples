package com.CodingNinjas.LeaveXpress.model;

import javax.persistence.*;

@Entity
@Table(name = "leaves")
public class LeaveModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private String startDate;
    private String endDate;
    private String description;
    private boolean isAccepted;

    public LeaveModel() {
    }

    public LeaveModel(String type, String startDate, String endDate, String description, boolean isAccepted) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.isAccepted = isAccepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}