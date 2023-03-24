package com.dzeru.olympicsparallel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MedalsByCountry {
    private String country;
    private int goldMedals;
    private int silverMedals;
    private int bronzeMedals;

    public MedalsByCountry(String country, int goldMedals, int silverMedals, int bronzeMedals) {
        this.country = country;
        this.goldMedals = goldMedals;
        this.silverMedals = silverMedals;
        this.bronzeMedals = bronzeMedals;
    }

    public MedalsByCountry(String country) {
        this.country = country;
        this.goldMedals = 0;
        this.silverMedals = 0;
        this.bronzeMedals = 0;
    }

    public int sumMedals() {
        return this.goldMedals + this.silverMedals + this.bronzeMedals;
    }

    public void addMedal(MedalType medalType) {
        switch (medalType) {
            case GOLD:
                goldMedals++;
            case SILVER:
                silverMedals++;
            case BRONZE:
                bronzeMedals++;
        }
    }
}
