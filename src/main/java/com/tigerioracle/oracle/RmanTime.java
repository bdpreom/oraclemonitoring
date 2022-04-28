package com.tigerioracle.oracle;

import lombok.Data;

@Data
public class RmanTime {
    private String session_key;
    private String input_type;
    private String status;
    private String start_time;
    private String end_time;
    private String hrs;

    protected RmanTime() {
    }

    public RmanTime(String session_key, String input_type, String status, String start_time, String end_time, String hrs) {
        this.session_key = session_key;
        this.input_type = input_type;
        this.status = status;
        this.start_time = start_time;
        this.end_time = start_time;
        this.hrs = hrs;
    }
}
