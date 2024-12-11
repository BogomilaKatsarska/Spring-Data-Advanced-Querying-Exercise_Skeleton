package com.example.springintro.model.entity;

import java.math.BigDecimal;

//Projection
public interface BookInfo {
    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();
}
