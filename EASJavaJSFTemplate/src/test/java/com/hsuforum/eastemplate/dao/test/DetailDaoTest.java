package com.hsuforum.eastemplate.dao.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hsuforum.easjavatemplate.dao.DetailDao;
import com.hsuforum.easjavatemplate.entity.Detail;

/**
 * DetailDao unit test class
 * @author Marvin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DetailDaoTest {
	
	@Autowired
	DetailDao dao ;
	
	@Test
	public void testCreate() {
		Detail testingObj = new Detail();

		// TODO Input PK
		testingObj.setId("Input pk");
		dao.create(testingObj);
		
		Assert.assertNotEquals(testingObj.getId(), null);
	}

	@Test
	public void testRead() {
	
		Detail testingObj = dao.findByPK("Input pk");
		
		Assert.assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		// TODO update at least a field
		Detail testingObj = dao.findByPK("Input pk");
		testingObj.setName("test2");
		dao.update(testingObj);
		
		Assert.assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {	
		// TODO Input PK
		Detail testingObj = dao.findByPK("Input pk");	
		dao.delete(testingObj);
		Detail testingObj2 = dao.findByPK("Input pk");
		Assert.assertEquals(testingObj2, null);
	}	
}
