package com.tigerioracle.oracle;


import lombok.Data;

@Data
public class RmanHistory {
    private String session_key;
    private String input_type;
    private String status;
    private String rman_bkup_start_time;
    private String rman_bkup_end_time;
    private String hours;

    protected RmanHistory() {
    }

    public RmanHistory(String session_key, String input_type, String status, String rman_bkup_start_time, String rman_bkup_end_time, String hours) {
        this.session_key = session_key;
        this.input_type = input_type;
        this.status = status;
        this.rman_bkup_start_time = rman_bkup_start_time;
        this.rman_bkup_end_time = rman_bkup_end_time;
        this.hours = hours;
    }


}
