package com.carboncapture.carbon.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndustryYearlyCarbon {
    private String inventory;
    private String carbonSource;
    private int year;
    private double output;

}
