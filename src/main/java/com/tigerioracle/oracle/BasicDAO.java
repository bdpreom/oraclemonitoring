package com.tigerioracle.oracle;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BasicDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CurrentTime> curtime(){
        String sql = "SELECT\n" +
                "  CURRENT_TIMESTAMP\n" +
                "FROM\n" +
                "  dual";
        List<CurrentTime> listCurrentTime = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(CurrentTime.class));
        return listCurrentTime;
    }


    public List<Basic> basiclist(){
        String sql = "select a.DB_UNIQUE_NAME, a.LOG_MODE, a.FLASHBACK_ON, a.DATABASE_ROLE, floor(sysdate - startup_time) || ' day(s) ' || trunc( 24*((sysdate-startup_time) - trunc(sysdate-startup_time))) || ' hour(s) ' || mod(trunc(1440*((sysdate-startup_time) - trunc(sysdate-startup_time))), 60) ||' minute(s) ' || mod(trunc(86400*((sysdate-startup_time) - trunc(sysdate-startup_time))), 60) ||' seconds' uptime from v$database a, v$instance b";
        List<Basic> listBasic = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Basic.class));
        return listBasic;
    }

    public List<DefaultTbs> defaulttbslist(){
        String sql = "select USERNAME,DEFAULT_TABLESPACE,TEMPORARY_TABLESPACE from dba_users where default_tablespace not in ('SYSTEM','SYSAUX','USERS')";
        List<DefaultTbs> listDefaultTbs = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(DefaultTbs.class));
        return listDefaultTbs;
    }

    public List<TbsDetail> tbsdetails(){
        String sql = "SELECT\n" +
                "    a.tablespace_name \"TABLESPACE_NAME\",\n" +
                "    round(a.current_mb) \"CURRENT_SIZE_MB\",\n" +
                "    round(a.max_mb) \"MAX_SIZE_MB\",\n" +
                "    round(a.current_mb - c.free) \"USED_MB\",\n" +
                "    round(a.max_mb - (a.current_mb - c.free)) \"FREE_MB\",\n" +
                "    round(((a.current_mb - c.free)*100)/a.max_mb) \"USED_PCT\"\n" +
                "FROM\n" +
                "    (\n" +
                "        SELECT\n" +
                "            tablespace_name,\n" +
                "            SUM(a.bytes)/(1024*1024) current_mb,\n" +
                "            SUM(decode(a.autoextensible, 'NO', a.bytes/(1024*1024), GREATEST (a.maxbytes/(1024*1024),a.bytes/(1024*1024)))) max_mb\n" +
                "        FROM\n" +
                "            dba_data_files a\n" +
                "        GROUP BY tablespace_name\n" +
                "    ) a,\n" +
                "    (\n" +
                "        SELECT\n" +
                "            d.tablespace_name, sum(nvl(c.bytes/(1024*1024),0)) free\n" +
                "        FROM\n" +
                "            dba_tablespaces d,\n" +
                "            dba_free_space c\n" +
                "        WHERE\n" +
                "            d.tablespace_name = c.tablespace_name(+)\n" +
                "        --AND d.contents='PERMANENT'\n" +
                "        --AND d.status='ONLINE'\n" +
                "        GROUP BY\n" +
                "            d.tablespace_name\n" +
                "    ) c\n" +
                "WHERE\n" +
                "    a.tablespace_name = c.tablespace_name\n" +
                "ORDER BY used_pct desc";

        List<TbsDetail> listTbsDetail = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(TbsDetail.class));
        return listTbsDetail;
    }


    public List<Archivelog> archivelog(){
        String sql = "SELECT name,space_limit/1024/1024 as space_limit ,space_used/1024/1024 as space_used ,space_reclaimable/1024/1024 as space_reclaimable,number_of_files  FROM V$RECOVERY_FILE_DEST";
        List<Archivelog> listArchivelog = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Archivelog.class));
        return listArchivelog;
    }


    public List<RmanTime> rmantimedetails(){
        String sql = " select SESSION_KEY, INPUT_TYPE, STATUS,to_char(START_TIME,'mm/dd/yy hh24:mi') as start_time,to_char(END_TIME,'mm/dd/yy hh24:mi') as end_time,elapsed_seconds/3600 as hrs from V$RMAN_BACKUP_JOB_DETAILS order by session_key";
        List<RmanTime> listRmanTime = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RmanTime.class));
        return listRmanTime;
    }

    public List<RmanCurrent> rmancurrentdetails(){
        String sql = "   SELECT SID, SERIAL# as serial, CONTEXT, SOFAR, TOTALWORK,ROUND (SOFAR/TOTALWORK*100, 2) as completed FROM V$SESSION_LONGOPS WHERE OPNAME LIKE 'RMAN%' AND OPNAME NOT LIKE '%aggregate%' AND TOTALWORK! = 0 AND SOFAR <> TOTALWORK";
        List<RmanCurrent> listRmanCurrent = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RmanCurrent.class));
        return listRmanCurrent;
    }

    public List<RmanHistory> rmanhistorydetails(){
        String sql = "select SESSION_KEY, INPUT_TYPE, STATUS,to_char(START_TIME,'mm-dd-yyyy hh24:mi:ss') as RMAN_Bkup_start_time,to_char(END_TIME,'mm-dd-yyyy hh24:mi:ss') as RMAN_Bkup_end_time,elapsed_seconds/3600 Hours from V$RMAN_BACKUP_JOB_DETAILS order by session_key";
        List<RmanHistory> listRmanHistory = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(RmanHistory.class));
        return listRmanHistory;
    }

    public List<LongRunningObj> longrunningobjdetails(){
        String sql = "  select\n" +
                "     opname,\n" +
                "     start_time,\n" +
                "     target,\n" +
                "     sofar,\n" +
                "     totalwork,\n" +
                "     units,\n" +
                "     elapsed_seconds,\n" +
                "     message\n" +
                "   from\n" +
                "        v$session_longops where rownum <=10\n" +
                "  order by start_time desc";
        List<LongRunningObj> listLongRunningObj = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(LongRunningObj.class));
        return listLongRunningObj;
    }


























}
