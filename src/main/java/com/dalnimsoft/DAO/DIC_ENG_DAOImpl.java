package com.dalnimsoft.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;

import com.dalnimsoft.domain.DIC_ENG_VO;



import org.springframework.stereotype.Repository;

import com.dalnimsoft.DAO.DIC_ENG_DAO;


@Repository
public class DIC_ENG_DAOImpl implements DIC_ENG_DAO {
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.dalnimsoft.mapper.Dic_ENG_Mapper";

	@Override
	public List<DIC_ENG_VO> selectWordByWordLevel(Integer wordlevel) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace + ".selectWordByWordLevel", wordlevel);
	}
	@Override
	public DIC_ENG_VO selectWord(String word) throws Exception {
		return (DIC_ENG_VO)sqlSession.selectOne(namespace + ".selectWord", word);
	}

}
