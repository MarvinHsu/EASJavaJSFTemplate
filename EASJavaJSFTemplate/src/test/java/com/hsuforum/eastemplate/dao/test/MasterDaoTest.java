package com.hsuforum.eastemplate.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.easjavatemplate.dao.primary.MasterDao;
import com.hsuforum.easjavatemplate.entity.primary.Master;

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
	public void testRead() {
	
		Master testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
}
