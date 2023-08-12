package com.hsuforum.eastemplate.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.easjavatemplate.dao.primary.Many2Dao;
import com.hsuforum.easjavatemplate.entity.primary.Many2;

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
	public void testRead() {
	
		Many2 testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}

}
