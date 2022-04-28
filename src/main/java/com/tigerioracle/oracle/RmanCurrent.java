package com.tigerioracle.oracle;

import lombok.Data;

@Data
public class RmanCurrent {
    public String sid;
    public String serial;
    public String context;
    public String sofar;
    public String total;
    public String completed;

    protected RmanCurrent() {
    }

    public RmanCurrent(String sid, String serial, String context, String sofar, String total, String completed) {
        this.sid = sid;
        this.serial = serial;
        this.context = context;
        this.sofar = sofar;
        this.total = total;
        this.completed = completed;
    }
}