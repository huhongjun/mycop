CREATE OR REPLACE VIEW VIEW_BBS_TOPIC 
(
    SERIAL_NUM,
    SBOARD_SERIAL,
    TOPIC,
    NICKNAME,
    APPEAR_DATE,
    REPLY_CNT,
    HIT_TIMES,
    REPLY_TIME,
    TOPIC_TYPE,
    TOPIC_ORDER,
    ISBLOCK,
    IS_SHOW,
    REPLYER
)
AS
    select t.serial_num,sboard_serial,t.TOPIC,u.nickname,APPEAR_DATE,count(*)-
        1 as reply_cnt ,hit_times, max(l.REPLY_DATE) as reply_time,TOPIC_TYPE,
        TOPIC_ORDER,ISBLOCK,t.IS_SHOW,u.nickname
      from do_bbs_user u,do_bbs_topiclist l,do_bbs_topic t
      where t.serial_num = l.topic_num
        and u.bbs_user_code=t.bbs_user_code and u.bbs_user_code=l.bbs_user_code
      group by t.TOPIC,u.nickname,APPEAR_DATE,t.serial_num,sboard_serial,
          hit_times,TOPIC_TYPE,TOPIC_ORDER,ISBLOCK,t.IS_SHOW   
