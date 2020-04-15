package com.hsuforum.eastemplate.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hsuforum.easjavatemplate.entity.Many1;
import com.hsuforum.easjavatemplate.service.Many1Service;

/**
 * Many1Service unit test class
 * @author Marvin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Many1ServiceTest {
	
	@Autowired
	Many1Service service;
	
	@Test
	public void testCreate() {
		Many1 testingObj = new Many1();

		// TODO Input PK
		testingObj.setId("Input pk");
		service.create(testingObj);
		
		Assert.assertNotEquals(testingObj.getId(), null);;
	}
	
	@Test
	public void testRead() {
	
		Many1 testingObj = service.findByPK("Input pk");
		
		Assert.assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		
		// TODO update at least a field
		Many1 testingObj = service.findByPK("Input pk");
		testingObj.setName("test2");
		service.update(testingObj);
		
		Assert.assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {
		
		// TODO Input PK	
		Many1 testingObj = service.findByPK("Input pk");	
		service.delete(testingObj);
		Many1 testingObj2 = service.findByPK("Input pk");
		Assert.assertEquals(testingObj2, null);
	}	
}
