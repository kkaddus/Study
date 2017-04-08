package com.dkunc.adm.ath.cde.mapper;

import com.dkunc.adm.ath.cde.vo.CodeDetailVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="codeDetailWriteMapper")
public interface CodeDetailWriteMapper {
	
	public int insertCodeDetail(CodeDetailVO vo) throws Exception;
	
	public int updateCodeDetail(CodeDetailVO vo) throws Exception;
	
	public int deleteCodeDetail(CodeDetailVO vo) throws Exception;
	
	public CodeDetailVO selectCodeDetail(CodeDetailVO vo) throws Exception;
	
}
