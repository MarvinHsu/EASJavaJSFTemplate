package com.hsuforum.eastemplate.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.easjavatemplate.entity.Master;
import com.hsuforum.easjavatemplate.service.MasterService;

/**
 * MasterService unit test class
 * @author Marvin
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MasterServiceTest {
	
	@Autowired
	MasterService service;
	
	@Test
	public void testCreate() {
		Master testingObj = new Master();

		// TODO Input PK
		testingObj.setId("Input pk");
		service.create(testingObj);
		
		assertNotEquals(testingObj.getId(), null);;
	}
	
	@Test
	public void testRead() {
	
		Master testingObj = service.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		
		// TODO update at least a field
		Master testingObj = service.findByPK("Input pk");
		testingObj.setName("test2");
		service.update(testingObj);
		
		assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {
		
		// TODO Input PK	
		Master testingObj = service.findByPK("Input pk");	
		service.delete(testingObj);
		Master testingObj2 = service.findByPK("Input pk");
		assertEquals(testingObj2, null);
	}	
}
