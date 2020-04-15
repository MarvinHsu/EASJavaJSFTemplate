package com.hsuforum.eastemplate.service.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hsuforum.easjavatemplate.entity.Many2;
import com.hsuforum.easjavatemplate.service.Many2Service;

/**
 * Many2Service unit test class
 * @author Marvin
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Many2ServiceTest {
	
	@Autowired
	Many2Service service;
	
	@Test
	public void testCreate() {
		Many2 testingObj = new Many2();

		// TODO Input PK
		testingObj.setId("Input pk");
		service.create(testingObj);
		
		Assert.assertNotEquals(testingObj.getId(), null);;
	}
	
	@Test
	public void testRead() {
	
		Many2 testingObj = service.findByPK("Input pk");
		
		Assert.assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		
		// TODO update at least a field
		Many2 testingObj = service.findByPK("Input pk");
		testingObj.setName("test2");
		service.update(testingObj);
		
		Assert.assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {
		
		// TODO Input PK	
		Many2 testingObj = service.findByPK("Input pk");	
		service.delete(testingObj);
		Many2 testingObj2 = service.findByPK("Input pk");
		Assert.assertEquals(testingObj2, null);
	}	
}
