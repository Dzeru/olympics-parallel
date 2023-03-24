package com.dzeru.olympicsparallel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class OlympicsByCountrySport {
    private String country;
    private String sport;
    private int olympics;

    public OlympicsByCountrySport(String country, String sport, int olympics) {
        this.country = country;
        this.sport = sport;
        this.olympics = olympics;
    }
}
