package com.dalnimsoft.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.dalnimsoft.DAO.KnowDAO;
import com.dalnimsoft.domain.KnowVO;

@Service
public class KnowServiceImpl implements KnowService {
	@Inject
	private KnowDAO dao;

	public void add(KnowVO knowVO) throws Exception {
		// TODO Auto-generated method stub
		dao.insertKnow(knowVO);
	}

	
	public KnowVO read(Integer uid, String word) throws Exception {
		return dao.selectKnowByIDAndWord(uid, word);
	}

	
	public void remove(Integer uid, String word) throws Exception {
		// TODO Auto-generated method stub
		dao.deleteByIDAndWord(uid, word);
	}
	
	
}
