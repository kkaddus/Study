package com.dkunc.cmn.mapper;

import java.util.List;

import com.dkunc.cmn.ComDefaultCodeVO;
import com.dkunc.cmn.service.CmmnDetailCode;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper(value = "cmmUseMapper")
public interface CmmUseMapper {
	/**
     * 주어진 조건에 따른 공통코드를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
	public List<CmmnDetailCode> selectCmmCodeDetail(ComDefaultCodeVO vo) throws Exception;

    /**
     * 공통코드로 사용할 조직정보를 를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<CmmnDetailCode> selectOgrnztIdDetail(ComDefaultCodeVO vo) throws Exception;

    /**
     * 공통코드로 사용할그룹정보를 를 불러온다.
     *
     * @param vo
     * @return
     * @throws Exception
     */
    public List<CmmnDetailCode> selectGroupIdDetail(ComDefaultCodeVO vo) throws Exception;
}
