package com.hsuforum.easjavatemplate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easjavatemplate.dao.Many1JpaRepository;
import com.hsuforum.easjavatemplate.entity.Many1;
import com.hsuforum.easjavatemplate.service.Many1JpaService;

@Service("many1JpaService")
public class Many1JpaServiceImpl extends BaseJpaServiceImpl<Many1, String, Many1JpaRepository>
		implements Many1JpaService {

	private static final long serialVersionUID = 8200364878031995881L;
	@Autowired
	private Many1JpaRepository repo;

	@Override
	public Many1JpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(Many1JpaRepository repo) {
		this.repo = repo;

	}
}
