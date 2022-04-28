package com.tigerioracle.oracle;


import lombok.Data;

@Data
public class TbsDetail {
    public String tablespace_name;
    public String current_size_mb;
    public String max_size_mb;
    public String used_mb;
    public String free_mb;
    public String used_pct;

    protected TbsDetail() {}

    public TbsDetail(String tablespace_name, String current_size_mb, String max_size_mb, String used_mb, String free_mb, String used_pct) {
        this.tablespace_name = tablespace_name;
        this.current_size_mb = current_size_mb;
        this.max_size_mb = max_size_mb;
        this.used_mb = used_mb;
        this.free_mb = free_mb;
        this.used_pct = used_pct;
    }
}


