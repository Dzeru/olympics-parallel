package com.dzeru.olympicsparallel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@EqualsAndHashCode
public class MedalsTypeBySportsman {
    private MedalsBySportsman goldMedals;
    private MedalsBySportsman silverMedals;
    private MedalsBySportsman bronzeMedals;
}
