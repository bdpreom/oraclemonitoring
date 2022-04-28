package com.tigerioracle.oracle;


import lombok.Data;

@Data
public class Basic {
    private String db_unique_name;
    private String log_mode;
    private String flashback_on;
    private String database_role;
    private String uptime;

    protected Basic() {}

    public Basic(String db_unique_name, String log_mode, String flashback_on, String database_role, String uptime) {
        this.db_unique_name = db_unique_name;
        this.log_mode = log_mode;
        this.flashback_on = flashback_on;
        this.database_role = database_role;
        this.uptime = uptime;
    }
}
