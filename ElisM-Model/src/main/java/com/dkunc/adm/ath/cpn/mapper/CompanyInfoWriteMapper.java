package com.dkunc.adm.ath.cpn.mapper;

import com.dkunc.adm.ath.cpn.vo.CompanyInfoVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value="companyInfoWriteMapper")
public interface CompanyInfoWriteMapper {

	public int insertCompanyInfo(CompanyInfoVO vo) throws Exception;

	public int updateCompanyInfo(CompanyInfoVO vo) throws Exception;

	public int deleteCompanyInfo(CompanyInfoVO vo) throws Exception;

	public CompanyInfoVO selectCompanyInfo(CompanyInfoVO vo) throws Exception;
}
