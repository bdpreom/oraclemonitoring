package com.tigerioracle.oracle;


import lombok.Data;

@Data
public class CurrentTime {
    public String current_timestamp;

    protected CurrentTime() {}

    public CurrentTime(String current_timestamp) {
        this.current_timestamp = current_timestamp;
    }
}
