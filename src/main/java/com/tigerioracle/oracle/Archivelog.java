package com.tigerioracle.oracle;


import lombok.Data;

@Data
public class Archivelog {
    public String name;
    public String space_limit;
    public String space_used;
    public String space_reclaimable;
    public String number_of_files;

    protected Archivelog() {}

    public Archivelog(String name, String space_limit, String space_used, String space_reclaimable, String number_of_files) {
        this.name = name;
        this.space_limit = space_limit;
        this.space_used = space_used;
        this.space_reclaimable = space_reclaimable;
        this.number_of_files = number_of_files;
    }

}
