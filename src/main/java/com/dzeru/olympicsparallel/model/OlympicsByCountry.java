package com.dzeru.olympicsparallel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class OlympicsByCountry {
    private String country;
    private int olympics;

    public OlympicsByCountry(String country, int olympics) {
        this.country = country;
        this.olympics = olympics;
    }
}
