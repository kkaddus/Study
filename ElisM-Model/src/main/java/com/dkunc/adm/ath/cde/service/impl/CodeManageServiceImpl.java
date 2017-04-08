package com.dkunc.adm.ath.cde.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dkunc.adm.ath.cde.mapper.CodeDetailWriteMapper;
import com.dkunc.adm.ath.cde.mapper.CodeManageMapper;
import com.dkunc.adm.ath.cde.mapper.CodeMasterWriteMapper;
import com.dkunc.adm.ath.cde.service.CodeManageService;
import com.dkunc.adm.ath.cde.vo.CodeDetailVO;
import com.dkunc.adm.ath.cde.vo.CodeManageVO;
import com.dkunc.adm.ath.cde.vo.CodeMasterVO;
import com.dkunc.cmn.SearchDefaultVO;
import com.dkunc.cmn.vo.LoginVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.security.userdetails.util.EgovUserDetailsHelper;

@Service("codeManageService")
public class CodeManageServiceImpl extends EgovAbstractServiceImpl implements CodeManageService {

	/** codeManageMapper */
	@Resource(name="codeManageMapper")
	private CodeManageMapper codeManageMapper;
	
	/** codeDetailWriteMapper */
	@Resource(name="codeDetailWriteMapper")
	private CodeDetailWriteMapper codeDetailWriteMapper;
	
	/** codeMasterWriteMapper */
	@Resource(name="codeMasterWriteMapper")
	private CodeMasterWriteMapper codeMasterWriteMapper;
	
	@Override
	public List<CodeManageVO> selectCodeList(SearchDefaultVO searchDefaultVO) {
		return codeManageMapper.selectCodeList(searchDefaultVO);
	}

    @Override
	public int selectCodeListTotCnt(SearchDefaultVO searchDefaultVO) {
    	return codeManageMapper.selectCodeListTotCnt(searchDefaultVO);
    }
    
	@Override
	public int checkIdDplct(String checkId) {
		return codeManageMapper.checkIdDplct(checkId);
	}
	
	@Override
	public int insertCodeMaster(CodeMasterVO codeMasterVO) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		codeMasterVO.setCreateId(user.getId());
		codeMasterVO.setUpdateId(user.getId());
		return codeMasterWriteMapper.insertCodeMaster(codeMasterVO);
	}
	
	@Override
	public CodeMasterVO selectCodeMaster(CodeMasterVO codeMasterVO) throws Exception {
		return codeMasterWriteMapper.selectCodeMaster(codeMasterVO);
	}

	@Override
	public int updateCodeMaster(CodeMasterVO codeMasterVO) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		codeMasterVO.setUpdateId(user.getId());
		return codeMasterWriteMapper.updateCodeMaster(codeMasterVO);
	}
	
	@Override
	public int deleteCodeMaster(CodeMasterVO codeMasterVO) throws Exception {
		return codeMasterWriteMapper.deleteCodeMaster(codeMasterVO);
	}
	
	@Override
	public List<CodeManageVO> selectCodeDetailList(SearchDefaultVO searchDefaultVO) throws Exception {
		return codeManageMapper.selectCodeDetailList(searchDefaultVO);
	}
	
	@Override
	public int selectCodeDetailListTotCnt(SearchDefaultVO searchDefaultVO) throws Exception {
		return codeManageMapper.selectCodeDetailListTotCnt(searchDefaultVO);
	}
	
	@Override
	public int checkDescDplct(Map checkMap) throws Exception {
		return codeManageMapper.checkDescDplct(checkMap);
	}
	
	@Override
	public int insertCodeDetail(CodeDetailVO codeDetailVO) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		codeDetailVO.setCreateId(user.getId());
		codeDetailVO.setUpdateId(user.getId());
		return codeDetailWriteMapper.insertCodeDetail(codeDetailVO);
	}
	
	@Override
	public CodeDetailVO selectCodeDetail(CodeDetailVO codeDetailVO) throws Exception {
		return codeDetailWriteMapper.selectCodeDetail(codeDetailVO);
	}
	
	@Override
	public int updateCodeDetail(CodeDetailVO codeDetailVO) throws Exception {
		LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
		codeDetailVO.setUpdateId(user.getId());
		return codeDetailWriteMapper.updateCodeDetail(codeDetailVO);
	}
	
	@Override
	public int deleteCodeDetail(CodeDetailVO codeDetailVO) throws Exception {
		return codeDetailWriteMapper.deleteCodeDetail(codeDetailVO);
	}
}