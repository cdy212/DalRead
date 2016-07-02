package com.dalnimsoft.DAO;
import com.dalnimsoft.domain.KnowVO;

public interface KnowDAO {
//	private String tblName = "test.KNOW_ENG";
	public String getTime();
	public void insertKnow(KnowVO vo);
	public KnowVO selectKnowByID(Integer uid) throws Exception;
	public KnowVO selectKnowByIDAndWord(Integer uid, String word);
	public void deleteByID( Integer uid) throws Exception;
	public void deleteByIDAndWord(Integer uid, String word) throws Exception;
}
