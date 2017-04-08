package com.dkunc.adm.ath.cde.mapper;

import com.dkunc.adm.ath.cde.vo.CodeMasterVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="codeMasterWriteMapper")
public interface CodeMasterWriteMapper {
	
	public int insertCodeMaster(CodeMasterVO vo) throws Exception;
	
	public int updateCodeMaster(CodeMasterVO vo) throws Exception;
	
	public int deleteCodeMaster(CodeMasterVO vo) throws Exception;
	
	public CodeMasterVO selectCodeMaster(CodeMasterVO vo) throws Exception;
	
}
