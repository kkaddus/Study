SET DEFINE OFF;
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (2012000, '����ȸ', 2010000, 2, '����ȸ', 
    0, '/EgovPageLink.do?link=main/sample_menu/EgovServiceManage', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:15:09.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 ���� 11:15:09.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (2021000, '������', 2020000, 1, '������', 
    0, '/EgovPageLink.do?link=main/sample_menu/EgovServiceResult', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:15:57.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 ���� 11:15:57.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (2022000, '�Աݵ��', 2020000, 2, '�Աݵ��', 
    0, '/EgovPageLink.do?link=main/sample_menu/EgovServiceResult', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:16:14.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 ���� 11:16:14.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (2011000, '�����', 2010000, 1, '�����', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:14:55.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 ���� 1:25:20.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3011000, '��۰�ȹ ��ȸ', 3010000, 1, '��۰�ȹ ��ȸ', 
    0, '/adm/ath/rol/viewRoleInfo.do', '/adm/ath/rol/.*.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:17:12.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/04 ���� 9:57:48.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3012000, '���ϼ���', 3010000, 2, '���ϼ���', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:17:28.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 ���� 11:17:28.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3041000, '��۳��� ��ȸ', 3040000, 1, '��۳��� ��ȸ', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:17:54.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 ���� 11:17:54.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3042000, '��ۺ����̷���ȸ', 3040000, 2, '��ۺ����̷���ȸ', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:18:12.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 ���� 11:18:12.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (3043000, '������ں���', 3040000, 3, '������ں���', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/04/03 ���� 11:18:26.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/04/03 ���� 11:18:26.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, UPDATE_ID, USAGE_YN)
 Values
   (0, '���ڷ���', 9999999, 1, '�ֻ����޴�', 
    0, '/', '/', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (2000000, '�ǸŻ��', 0, 2, '�ǸŻ��', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 ���� 2:39:41.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (2010000, '������', 2000000, 1, '������', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 ���� 1:24:58.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (2020000, '������', 2000000, 2, '������', 
    0, '/EgovPageLink.do?link=main/sample_menu/EgovServiceResult', '/EgovPageLink.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 ���� 11:15:32.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (3000000, '��۰���', 0, 3, '��۰���', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 ���� 6:39:34.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (3010000, '��۰�ȹ', 3000000, 1, '��۰�ȹ', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_AAAAAAAAAAAA', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 ���� 11:16:36.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (3040000, '��۰���', 3000000, 2, '��۰���', 
    0, '/cop/bbs/selectBoardList.do?bbsId=BBSMSTR_BBBBBBBBBBBB', '/cop/bbs/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/04/03 ���� 11:16:50.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6000000, '�������', 0, 6, '�������', 
    0, '/adm/ath/usr/viewUserInfoList.do', '/', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 ���� 6:37:28.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6010000, '����ڰ���', 6000000, 1, '����ڰ���', 
    0, '/adm/ath/usr/viewUserInfoList.do', '/', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/27 ���� 6:21:07.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, UPDATE_ID, USAGE_YN)
 Values
   (6010100, 'ȸ������', 6010000, 1, 'ȸ������', 
    0, '/adm/ath/usr/viewUserInfoList.do', '/adm/ath/usr/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020000, '����ڱ��Ѱ���', 6000000, 2, '����ڱ��Ѱ���', 
    0, '/adm/ath/mnu/viewMenuInfoList.do', '/', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/27 ���� 6:21:17.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020400, '�������� ����', 6020000, 4, '�������� ����', 
    0, '/adm/ath/rol/viewRoleInfo.do', '/adm/ath/rol/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 ���� 6:13:01.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020500, '�������Һ� �޴�����', 6020000, 5, '�������Һ� �޴�����', 
    0, '/adm/ath/rol/viewRoleMenuInfo.do', '/adm/ath/rol/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 ���� 3:31:24.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020600, '�������Һ� ����� ����', 6020000, 6, '�������Һ� ����� ����', 
    0, '/adm/ath/rol/viewRoleUserInfo.do', '/adm/ath/rol/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/29 ���� 10:00:11.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, UPDATE_ID, USAGE_YN)
 Values
   (6010400, '�Խ��ǰ���', 6010000, 4, '�Խ��ǰ���', 
    0, '/adm/ath/brd/viewBoardInfo.do', '/adm/ath/brd/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, UPDATE_ID, USAGE_YN)
 Values
   (6010200, '�μ�����', 6010000, 2, '�μ�����', 
    0, '/adm/ath/dpt/viewDeptInfo.do', '/adm/ath/dpt/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020100, '�ڵ����', 6020000, 1, '�ڵ����', 
    0, '/adm/ath/cde/viewCodeMaster.do', '/adm/ath/cde/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/28 ���� 1:13:11.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, CREATE_ID, 
    UPDATE_DT, USAGE_YN)
 Values
   (6020200, '�ڵ�󼼰���', 6020000, 2, '�ڵ�󼼰���', 
    0, '/adm/ath/cde/viewCodeDetail.do', '/adm/ath/cde/.*.do.*', TO_TIMESTAMP('2017/03/21 ���� 11:01:52.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'admin', 
    TO_TIMESTAMP('2017/03/28 ���� 1:13:59.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 'Y');
Insert into ELISM_ADMIN.T_MENU_INFO
   (MENU_ID, MENU_NM, PARENT_MENU_ID, MENU_ORDER, MENU_DESC, 
    MENU_DEPTH, MENU_URL, AUTH_PTTRN, CREATE_DT, UPDATE_DT, 
    USAGE_YN)
 Values
   (6020300, '�޴�����', 6020000, 3, '�Ŵ�����', 
    0, '/adm/ath/mnu/viewMenuInfoList.do', '/adm/ath/mnu/.*.do.*', TO_TIMESTAMP('2017/03/27 ���� 3:09:36.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), TO_TIMESTAMP('2017/03/31 ���� 11:28:13.000000','YYYY/MM/DD AM fmHH12fm:MI:SS.FF'), 
    'Y');
COMMIT;
