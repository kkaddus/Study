SET DEFINE OFF;
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (2012000, '고객조회', 2010000, 2, '고객조회', 
    0, '/EgovPageLink.do?link=main/sample_menu/EgovServiceManage', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/04/03 오전 11:15:09.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 오전 11:15:09.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (2021000, '상담관리', 2020000, 1, '상담관리', 
    0, '/EgovPageLink.do?link=main/sample_menu/EgovServiceResult', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/04/03 오전 11:15:57.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 오전 11:15:57.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (2022000, '입금등록', 2020000, 2, '입금등록', 
    0, '/EgovPageLink.do?link=main/sample_menu/EgovServiceResult', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/04/03 오전 11:16:14.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 오전 11:16:14.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (2011000, '고객등록', 2010000, 1, '고객등록', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/04/03 오전 11:14:55.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 오후 1:25:20.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3011000, '배송계획 조회', 3010000, 1, '배송계획 조회', 
    0, '/adm/ath/rol/viewRoleInfo.do', '/adm/ath/rol/.*.do.*', TO_TIMESTAMP('2017/04/03 오전 11:17:12.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/04 오전 9:57:48.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3012000, '출하선택', 3010000, 2, '출하선택', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/04/03 오전 11:17:28.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 오전 11:17:28.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3041000, '배송내역 조회', 3040000, 1, '배송내역 조회', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/04/03 오전 11:17:54.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 오전 11:17:54.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3042000, '배송변경이력조회', 3040000, 2, '배송변경이력조회', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/04/03 오전 11:18:12.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 오전 11:18:12.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3043000, '배송일자변경', 3040000, 3, '배송일자변경', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/04/03 오전 11:18:26.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 오전 11:18:26.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, UPDATE_ID, USAGE_YN)
 Values
   (0, '전자랜드', 9999999, 1, '최상위메뉴', 
    0, '/', '/', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (2000000, '판매상담', 0, 2, '판매상담', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 오후 2:39:41.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (2010000, '고객관리', 2000000, 1, '고객관리', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 오후 1:24:58.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (2020000, '상담관리', 2000000, 2, '상담관리', 
    0, '/EgovPageLink.do?link=main/sample_menu/EgovServiceResult', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 오전 11:15:32.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (3000000, '배송관리', 0, 3, '배송관리', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 오후 6:39:34.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (3010000, '배송계획', 3000000, 1, '배송계획', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 오전 11:16:36.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (3040000, '배송관리', 3000000, 2, '배송관리', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 오전 11:16:50.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6000000, '공통관리', 0, 6, '공통관리', 
    0, '/adm/ath/usr/viewUserInfoList.do', '/', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 오후 6:37:28.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6010000, '사용자관리', 6000000, 1, '사용자관리', 
    0, '/adm/ath/usr/viewUserInfoList.do', '/', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/27 오후 6:21:07.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, UPDATE_ID, USAGE_YN)
 Values
   (6010100, '회원관리', 6010000, 1, '회원관리', 
    0, '/adm/ath/usr/viewUserInfoList.do', '/adm/ath/usr/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020000, '사용자권한관리', 6000000, 2, '사용자권한관리', 
    0, '/adm/ath/mnu/viewMenuInfoList.do', '/', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/27 오후 6:21:17.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020400, '업무역할 관리', 6020000, 4, '업무역할 관리', 
    0, '/adm/ath/rol/viewRoleInfo.do', '/adm/ath/rol/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 오후 6:13:01.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020500, '업무역할별 메뉴관리', 6020000, 5, '업무역할별 메뉴관리', 
    0, '/adm/ath/rol/viewRoleMenuInfo.do', '/adm/ath/rol/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 오후 3:31:24.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020600, '업무역할별 사용자 관리', 6020000, 6, '업무역할별 사용자 관리', 
    0, '/adm/ath/rol/viewRoleUserInfo.do', '/adm/ath/rol/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 오전 10:00:11.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, UPDATE_ID, USAGE_YN)
 Values
   (6010400, '게시판관리', 6010000, 4, '게시판관리', 
    0, '/adm/ath/brd/viewBoardInfo.do', '/adm/ath/brd/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, UPDATE_ID, USAGE_YN)
 Values
   (6010200, '부서관리', 6010000, 2, '부서관리', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/adm/ath/dpt/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020100, '코드관리', 6020000, 1, '코드관리', 
    0, '/adm/ath/cde/viewCodeMaster.do', '/adm/ath/cde/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/28 오후 1:13:11.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020200, '코드상세관리', 6020000, 2, '코드상세관리', 
    0, '/adm/ath/cde/viewCodeDetail.do', '/adm/ath/cde/.*.do.*', TO_TIMESTAMP('2017/03/21 오전 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/28 오후 1:13:59.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (6020300, '메뉴관리', 6020000, 3, '매뉴관리', 
    0, '/adm/ath/mnu/viewMenuInfoList.do', '/adm/ath/mnu/.*.do.*', TO_TIMESTAMP('2017/03/27 오후 3:09:36.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/03/31 오전 11:28:13.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
COMMIT;
