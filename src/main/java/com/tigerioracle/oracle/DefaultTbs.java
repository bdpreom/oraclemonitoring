package com.tigerioracle.oracle;


import lombok.Data;

@Data
public class DefaultTbs {
    private String username;
    private String default_tablespace;
    private String temporary_tablespace;

    protected DefaultTbs() {
    }

    public DefaultTbs(String username, String default_tablespace, String temporary_tablespace) {
        this.username = username;
        this.default_tablespace = default_tablespace;
        this.temporary_tablespace = temporary_tablespace;
    }
}


