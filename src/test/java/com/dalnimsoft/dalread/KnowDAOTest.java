package com.dalnimsoft.dalread;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dalnimsoft.DAO.KnowDAO;
import com.dalnimsoft.domain.KnowVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
public class KnowDAOTest {
	@Inject
	private KnowDAO dao;
	
	@Test
	public void testTime() throws Exception {
		System.out.println("dao.getTime() : " + dao.getTime());
	}
	
//	@Test
//	public void testInsertKnow() throws Exception {
//		KnowVO vo = new KnowVO();
//		vo.setUid(1112);
//		vo.setWord("dalnim");
//		System.out.println("testInsertKnow");
//		dao.insertKnow(vo);
//	}
	
	@Test
	public void testSelectByID() throws Exception {
		System.out.println("testSelectByID");
		KnowVO vo = new KnowVO();
		System.out.println("testSelectByID");
		vo = (KnowVO)dao.selectKnowByID(1510);
		System.out.println("VO : " + vo.getUid());
		System.out.println("VO : " + vo.getWord());
	}
	
	@Test
	public void testSelectByIDAndWord() throws Exception {
		System.out.println("testSelectByIDAndWord");
		KnowVO vo = new KnowVO();
		vo = (KnowVO)dao.selectKnowByIDAndWord(1510, "the");
		System.out.println("VO : " + vo.getUid());
		System.out.println("VO : " + vo.getWord());
		System.out.println("VO : " + vo);
	}
	@Test
	public void deleteByID() throws Exception {
		dao.deleteByID(1111);
	}
	@Test
	public void deleteByIDAndWord() throws Exception {
		dao.deleteByIDAndWord(1112, "dalnim");
	}
}
