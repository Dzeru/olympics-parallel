package com.dzeru.olympicsparallel.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class InfinityItem {
    private int position;
    private String threadName;
    private int number;

    public InfinityItem(int position, String threadName, int number) {
        this.position = position;
        this.threadName = threadName;
        this.number = number;
    }
}
