package com.hsuforum.easjavatemplate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easjavatemplate.dao.Many2JpaRepository;
import com.hsuforum.easjavatemplate.entity.Many2;
import com.hsuforum.easjavatemplate.service.Many2JpaService;

@Service("many2JpaService")
public class Many2JpaServiceImpl extends BaseJpaServiceImpl<Many2, String, Many2JpaRepository>
		implements Many2JpaService {

	private static final long serialVersionUID = 8200364878031995881L;
	@Autowired
	private Many2JpaRepository repo;

	@Override
	public Many2JpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(Many2JpaRepository repo) {
		this.repo = repo;

	}
}
