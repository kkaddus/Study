﻿CREATE OR REPLACE TYPE ELISM_ADMIN.user_role AS object
( 
  TARGET_ID varchar2(30),
  ROLE_CD varchar2(30)
);
/
CREATE OR REPLACE TYPE ELISM_ADMIN.user_role_row is TABLE OF user_role;
/

CREATE OR REPLACE FUNCTION ELISM_ADMIN.fn_cmmCodeIdToCodeNm (cmmCode IN VARCHAR2, cmmCodeId IN VARCHAR2) return VARCHAR2
IS cmmCodeNm VARCHAR2(100) := '';
BEGIN
  SELECT CODE_NM INTO cmmCodeNm
  FROM T_CODE_DETAIL
  WHERE USAGE_YN = 'Y' AND CODE_ID = cmmCode AND TRIM(CODE_DESC) = TRIM(cmmCodeId);
  RETURN cmmCodeNm;
END fn_cmmCodeIdToCodeNm;
/

CREATE OR REPLACE FUNCTION ELISM_ADMIN.tb_fc_getUserRole 
(
  SEARCH_NAME IN VARCHAR2
) RETURN user_role_row PIPELINED is returnVal user_role;
BEGIN
  FOR ss IN(
      SELECT TARGET_ID, ROLE_CD FROM T_USER_ROLE 
      WHERE (TARGET_ID=SEARCH_NAME AND TARGET_TYPE='U' ) 
      OR (TARGET_TYPE='D' AND TARGET_ID IN (SELECT DEPT_CD FROM T_DEPT_INFO START WITH DEPT_CD = (SELECT DEPT_CD FROM T_USER_INFO WHERE USER_ID=SEARCH_NAME) CONNECT BY PRIOR UP_DEPT_CD = DEPT_CD))
    )
    LOOP
      returnVal := user_role(ss.TARGET_ID, SS.ROLE_CD);
      PIPE ROW(returnVal);
    END LOOP;
  RETURN;
END tb_fc_getUserRole;
/