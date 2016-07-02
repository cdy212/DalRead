package com.dalnimsoft.DAO;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.dalnimsoft.DAO.KnowDAO;
import com.dalnimsoft.domain.KnowVO;

@Repository
public class KnowDAOImpl implements KnowDAO {
	@Inject
	private SqlSession sqlSession;
	private static final String namespace="com.dalnimsoft.mapper.KnowMapper";
	@Override
	public String getTime() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace + ".getTime");
	}
	@Override
	public void insertKnow(KnowVO vo) {
		// TODO Auto-generated method stub
		sqlSession.insert(namespace + ".insertKnow", vo);
	}
	@Override
	public KnowVO selectKnowByID(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		return (KnowVO)sqlSession.selectOne(namespace + ".selectKnowByID", uid);
	}
	@Override
	public KnowVO selectKnowByIDAndWord(Integer uid, String word) {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("word", word);
		
		return (KnowVO)sqlSession.selectOne(namespace + ".selectKnowByIDAndWord", paramMap);
	}
	@Override
	public void deleteByID(Integer uid) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("tbl_Know", "test.KNOW_ENG");
		sqlSession.delete(namespace + ".deleteByID", paramMap);
		
	}
	@Override
	public void deleteByIDAndWord(Integer uid, String word) throws Exception {
		// TODO Auto-generated method stub
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("uid", uid);
		paramMap.put("word", word);
		paramMap.put("tbl_Know", "test.KNOW_ENG");
		sqlSession.delete(namespace + ".deleteByIDAndWord", paramMap);
	}

	
	
}
