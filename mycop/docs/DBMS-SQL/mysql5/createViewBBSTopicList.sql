
CREATE VIEW `view_bbs_topiclist` 
(
   NICKNAME,
   USER_ICON,
   LAST_LOG_TIME,
   USER_CODE,
   USER_STATE,
   SEX_CODE
)
	AS 
		( select
   NICKNAME,
   USER_ICON,
   LAST_LOG_TIME,
   USER_CODE,
   USER_STATE,
   SEX_CODE
from
   DO_BBS_USER u)