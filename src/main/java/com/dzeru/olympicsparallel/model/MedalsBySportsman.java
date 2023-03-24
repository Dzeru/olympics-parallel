package com.dzeru.olympicsparallel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MedalsBySportsman {
    private String sportsman;
    private int goldMedals;
    private int silverMedals;
    private int bronzeMedals;

    public MedalsBySportsman(String sportsman, int goldMedals, int silverMedals, int bronzeMedals) {
        this.sportsman = sportsman;
        this.goldMedals = goldMedals;
        this.silverMedals = silverMedals;
        this.bronzeMedals = bronzeMedals;
    }

    public MedalsBySportsman(String sportsman) {
        this.sportsman = sportsman;
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
