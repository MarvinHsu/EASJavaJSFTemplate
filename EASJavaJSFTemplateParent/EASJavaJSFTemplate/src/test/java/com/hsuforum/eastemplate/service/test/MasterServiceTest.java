package com.hsuforum.eastemplate.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.hsuforum.easjavatemplate.entity.Master;
import com.hsuforum.easjavatemplate.service.MasterService;

/**
 * MasterService unit test class
 * @author Marvin
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:**/test/conf/spring/ApplicationContext.xml"})
@Transactional("transactionManager")
public class MasterServiceTest {
	
	@Autowired
	MasterService service;
	
	@Test
	public void testCreate() {
		Master testingObj = new Master();

		// TODO Input PK
		testingObj.setId("Input pk");
		service.create(testingObj);
		
		Assert.assertNotEquals(testingObj.getId(), null);;
	}
	
	@Test
	public void testRead() {
	
		Master testingObj = service.findByPK("Input pk");
		
		Assert.assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		
		// TODO update at least a field
		Master testingObj = service.findByPK("Input pk");
		testingObj.setName("test2");
		service.update(testingObj);
		
		Assert.assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {
		
		// TODO Input PK	
		Master testingObj = service.findByPK("Input pk");	
		service.delete(testingObj);
		Master testingObj2 = service.findByPK("Input pk");
		Assert.assertEquals(testingObj2, null);
	}	
}
