package com.dalnimsoft.dalread;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalnimsoft.DAO.DIC_ENG_DAO;
import com.dalnimsoft.DAO.KnowDAO;
import com.dalnimsoft.domain.DIC_ENG_VO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class DIC_ENG_DAOTest {
	@Inject
	private DIC_ENG_DAO dao;
	
	@Inject
	private KnowDAO knowDao;
	
	@Test
	public void selectWord()  {
		System.out.println("selectWord");
		DIC_ENG_VO vo = new DIC_ENG_VO();
		System.out.println("selectWord");
		try {
		
			vo = (DIC_ENG_VO)dao.selectWord("the");
//			System.out.println("VO : " + vo.getMEANING());
//			System.out.println("VO : " + vo.getPRONOUNCE());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void selectWordByWordLevel() throws Exception {
		System.out.println("selectWordByWordLevel");
		List<DIC_ENG_VO> list = dao.selectWordByWordLevel(3);
		System.out.println("selectWordByWordLevel");
		for (DIC_ENG_VO vo : list) {
			System.out.println("VO : " + vo.getWORD());
//			System.out.println("VO : " + vo.getMEANING());
//			System.out.println("VO : " + vo.getPRONOUNCE());			
		}
	}
}
