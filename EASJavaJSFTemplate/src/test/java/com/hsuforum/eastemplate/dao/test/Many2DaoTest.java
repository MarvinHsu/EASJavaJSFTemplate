package com.hsuforum.eastemplate.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.easjavatemplate.dao.Many2Dao;
import com.hsuforum.easjavatemplate.entity.Many2;

/**
 * Many2Dao unit test class
 * @author Marvin
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class Many2DaoTest {
	
	@Autowired
	Many2Dao dao ;
	
	@Test
	public void testCreate() {
		Many2 testingObj = new Many2();

		// TODO Input PK
		testingObj.setId("Input pk");
		dao.create(testingObj);
		
		assertNotEquals(testingObj.getId(), null);
	}

	@Test
	public void testRead() {
	
		Many2 testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		// TODO update at least a field
		Many2 testingObj = dao.findByPK("Input pk");
		testingObj.setName("test2");
		dao.update(testingObj);
		
		assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {	
		// TODO Input PK
		Many2 testingObj = dao.findByPK("Input pk");	
		dao.delete(testingObj);
		Many2 testingObj2 = dao.findByPK("Input pk");
		assertEquals(testingObj2, null);
	}	
}
