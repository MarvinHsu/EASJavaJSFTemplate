package com.hsuforum.easjavatemplate.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsuforum.common.service.impl.BaseJpaServiceImpl;
import com.hsuforum.easjavatemplate.dao.MasterJpaRepository;
import com.hsuforum.easjavatemplate.entity.Master;
import com.hsuforum.easjavatemplate.service.MasterJpaService;

@Service("masterJpaService")
public class MasterJpaServiceImpl extends BaseJpaServiceImpl<Master, String, MasterJpaRepository>
		implements MasterJpaService {

	private static final long serialVersionUID = 8200364878031995881L;
	@Autowired
	private MasterJpaRepository repo;

	@Override
	public MasterJpaRepository getRepo() {

		return this.repo;
	}

	@Override
	public void setRepo(MasterJpaRepository repo) {
		this.repo = repo;

	}
}
