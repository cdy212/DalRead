package com.dalnimsoft.DAO;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dalnimsoft.domain.DIC_ENG_VO;

@Component
public interface DIC_ENG_DAO {
	public List<DIC_ENG_VO> selectWordByWordLevel(Integer wordlevel) throws Exception;
	public DIC_ENG_VO selectWord(String word) throws Exception;	
}
