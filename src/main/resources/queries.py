import psutil




# dsn_tns = cx_Oracle.makedsn('localhost', 1521, 'shadman')
# con = cx_Oracle.connect("shadman", "shadman", dsn_tns)

#if would like to logged in as sysdba use the following parameters
# con = cx_Oracle.connect("sys", "tigerit", dsn_tns,mode=cx_Oracle.SYSDBA)





#queries



#long running

  select
     opname,
     start_time,
     target,
     sofar,
     totalwork,
     units,
     elapsed_seconds,
     message
   from
        v$session_longops where rownum <=10
  order by start_time desc;








#locked obj

select
  object_name,
  object_type,
  session_id,
  type,         -- Type or system/user lock
  lmode,        -- lock mode in which session holds lock
  request,
  block,
  ctime         -- Time since current mode was granted
from
  v$locked_object, all_objects, v$lock
where
  v$locked_object.object_id = all_objects.object_id AND
  v$lock.id1 = all_objects.object_id AND
  v$lock.sid = v$locked_object.session_id
order by
  session_id, ctime desc, object_name;



#last running queries
select S.USERNAME, s.sid, s.osuser, t.sql_id, sql_text
from v$sqltext_with_newlines t,V$SESSION s
where t.address =s.sql_address
and t.hash_value = s.sql_hash_value
and s.status = 'ACTIVE'
and s.username <> 'SYSTEM' and rownum < 10
order by s.sid,t.piece;







alert_log_View = """SELECT host_id,record_id,error_instance_id,error_instance_sequence,to_char(originating_timestamp,'DD.MM.YYYY HH24:MI:SS'),message_text FROM v$alert_log"""




asm_usage = """SELECT
    name                                     group_name
  , sector_size                              sector_size
  , block_size                               block_size
  , allocation_unit_size                     allocation_unit_size
  , state                                    state
  , type                                     type
  , total_mb                                 total_mb
  , (total_mb - free_mb)                     used_mb
  , ROUND((1- (free_mb / total_mb))*100, 2)  pct_used
FROM
    v$asm_diskgroup
ORDER BY
    name"""


asm_stat =  """SELECT
    a.name                disk_group_name
  , b.path                disk_path
  , b.reads               reads
  , b.writes              writes
  , b.read_errs           read_errs
  , b.write_errs          write_errs
  , b.read_time           read_time
  , b.write_time          write_time
  , b.bytes_read/1024/1024/1024         bytes_read
  , b.bytes_written/1024/1024/1024       bytes_written
FROM
    v$asm_diskgroup a JOIN v$asm_disk b USING (group_number)
ORDER BY
    a.name"""







listener_log = "select logtime1,connect1, protocol1,action1,service1 from listenerlog"


active_instance = "SELECT * FROM V$ACTIVE_INSTANCES"


schemastatgather = """
BEGIN
      DBMS_STATS.gather_schema_stats (
      ownname => 'shadman',
      ESTIMATE_PERCENT  => dbms_stats.auto_sample_size,
      degree   => 4);
END;
"""

tablestatgather = """
 exec dbms_stats.gather_table_stats(ownname=>'',tabname=>'',estimate_percent=>DBMS_STATS.AUTO_SAMPLE_SIZE,cascade=>TRUE,degree =>4)
"""

indexstatgather = """
  exec DBMS_STATS.GATHER_INDEX_STATS(ownname => '&OWNER',indname =>'&INDEX_NAME',estimate_percent =>DBMS_STATS.AUTO_SAMPLE_SIZE)
"""

systemstatgather = """
 execute dbms_stats.gather_system_stats('interval',60)
"""
#exec dbms_stats.gather_schema_stats(ownname=>'shadman',ESTIMATE_PERCENT=>dbms_stats.auto_sample_size,degree =>4);


statgather = {
    'schemastatgather' : schemastatgather,
    'tablestatgather': tablestatgather,
    'indexstatgather': indexstatgather,
    'systemstatgather' : systemstatgather
}




--long running
select * from
(
  select
     opname,
     start_time,
     target,
     sofar,
     totalwork,
     units,
     elapsed_seconds,
     message
   from
        v$session_longops
  order by start_time desc
)
where rownum <=10;






--locked obj

select
  object_name,
  object_type,
  session_id,
  type,         -- Type or system/user lock
  lmode,        -- lock mode in which session holds lock
  request,
  block,
  ctime         -- Time since current mode was granted
from
  v$locked_object, all_objects, v$lock
where
  v$locked_object.object_id = all_objects.object_id AND
  v$lock.id1 = all_objects.object_id AND
  v$lock.sid = v$locked_object.session_id
order by
  session_id, ctime desc, object_name;



--last running queries
select S.USERNAME, s.sid, s.osuser, t.sql_id, sql_text
from v$sqltext_with_newlines t,V$SESSION s
where t.address =s.sql_address
and t.hash_value = s.sql_hash_value
and s.status = 'ACTIVE'
and s.username <> 'SYSTEM' and rownum < 10
order by s.sid,t.piece;










