package com.hsuforum.easjavatemplate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easjavatemplate.dao.primary.DetailJpaRepository;
import com.hsuforum.easjavatemplate.entity.primary.Detail;
import com.hsuforum.easjavatemplate.service.DetailJpaService;

@Service("detailJpaService")
public class DetailJpaServiceImpl extends BaseJpaServiceImpl<Detail, String, DetailJpaRepository>
		implements DetailJpaService {

	private static final long serialVersionUID = 8200364878031995881L;
	@Autowired
	private DetailJpaRepository repo;

	@Override
	public DetailJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(DetailJpaRepository repo) {
		this.repo = repo;

	}
}
