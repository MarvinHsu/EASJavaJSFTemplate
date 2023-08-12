package com.hsuforum.eastemplate.dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.hsuforum.easjavatemplate.dao.primary.DetailDao;
import com.hsuforum.easjavatemplate.entity.primary.Detail;

/**
 * DetailDao unit test class
 * @author Marvin
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class DetailDaoTest {
	
	@Autowired
	DetailDao dao ;
	
	

	@Test
	public void testRead() {
	
		Detail testingObj = dao.findByPK("Input pk");
		
		assertEquals(testingObj.getId(),"Input pk");
	}
	

}
