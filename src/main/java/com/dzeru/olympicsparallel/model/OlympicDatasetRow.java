package com.dzeru.olympicsparallel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class OlympicDatasetRow {
    private String city;
    private int year;
    private String sport;
    private String discipline;
    private String event;
    private String athlete;
    private String gender;
    private String countryCode;
    private String country;
    private String eventGender;
    private String medal;

    public OlympicDatasetRow(String[] items) {
        this.city = items[0];
        this.year = items[1].isBlank() ? -1 : Integer.parseInt(items[1]);
        this.sport = items[2];
        this.discipline = items[3];
        this.event = items[4];
        this.athlete = items[5];
        this.gender = items[6];
        this.countryCode = items[7];
        this.country = items[8];
        this.eventGender = items[9];
        this.medal = items[10];
    }
}
