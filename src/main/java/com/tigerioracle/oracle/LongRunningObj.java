package com.tigerioracle.oracle;

import lombok.Data;

@Data
public class LongRunningObj {
    public String opname;
    public String start_time;
    private String target;
    private String sofar;
    private String totalwork;
    private String units;
    private String elapsed_seconds;
    private String message;

    protected LongRunningObj(){}

    public LongRunningObj(String opname, String start_time, String target, String sofar, String totalwork, String units, String elapsed_seconds, String message){
        this.opname = opname;
        this.start_time = start_time;
        this.target = target;
        this.sofar = sofar;
        this.totalwork = totalwork;
        this.units = units;
        this.elapsed_seconds = elapsed_seconds;
        this.message = message;
    }
}
