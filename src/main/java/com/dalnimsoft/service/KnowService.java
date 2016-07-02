package com.dalnimsoft.service;

import com.dalnimsoft.domain.KnowVO;
public interface KnowService {
	public void add(KnowVO knowVO) throws Exception;
	public KnowVO read(Integer uid, String word) throws Exception;
	public void remove(Integer uid, String word) throws Exception;
}
