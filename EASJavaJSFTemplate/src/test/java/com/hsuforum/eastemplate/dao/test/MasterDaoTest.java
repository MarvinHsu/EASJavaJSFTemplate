package com.hsuforum.eastemplate.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.easjavatemplate.dao.MasterDao;
import com.hsuforum.easjavatemplate.entity.Master;

/**
 * MasterDao unit test class
 * @author Marvin
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MasterDaoTest {
	
	@Autowired
	MasterDao dao ;
	
	@Test
	public void testCreate() {
		Master testingObj = new Master();

		// TODO Input PK
		testingObj.setId("Input pk");
		dao.create(testingObj);
		
		assertNotEquals(testingObj.getId(), null);
	}

	@Test
	public void testRead() {
	
		Master testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	
	@Test
	public void testUpdate() {	
		// TODO update at least a field
		Master testingObj = dao.findByPK("Input pk");
		testingObj.setName("test2");
		dao.update(testingObj);
		
		assertEquals(testingObj.getName(), "test2");
	}
	
	@Test
	public void testDelete() {	
		// TODO Input PK
		Master testingObj = dao.findByPK("Input pk");	
		dao.delete(testingObj);
		Master testingObj2 = dao.findByPK("Input pk");
		assertEquals(testingObj2, null);
	}	
}
