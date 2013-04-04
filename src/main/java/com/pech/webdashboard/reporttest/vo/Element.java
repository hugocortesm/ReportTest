package com.pech.webdashboard.reporttest.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class Element implements Serializable {

    private static final long serialVersionUID = 5377085676577463471L;

    private Integer id;
    private String elementId;
    private String name;
    private String description;
    private Integer oneNumber;
    private String newProperty;
    private Long longProperty;
    private BigDecimal bigProperty;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNewProperty() {
        return newProperty;
    }

    public void setNewProperty(String newProperty) {
        this.newProperty = newProperty;
    }

    public Integer getOneNumber() {
        return oneNumber;
    }

    public void setOneNumber(Integer oneNumber) {
        this.oneNumber = oneNumber;
    }

    public BigDecimal getBigProperty() {
        return bigProperty;
    }

    public void setBigProperty(BigDecimal bigProperty) {
        this.bigProperty = bigProperty;
    }


	public Long getLongProperty() {
        return longProperty;
    }

    public void setLongProperty(Long longProperty) {
        this.longProperty = longProperty;
    }

}
