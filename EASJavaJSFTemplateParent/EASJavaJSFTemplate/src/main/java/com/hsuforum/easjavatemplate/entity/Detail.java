package com.hsuforum.easjavatemplate.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hsuforum.common.entity.impl.BaseEntityImpl;


/**
 * The persistent class for the tb_detail database table.
 * 
 */
@Entity
@Table(name="tb_detail")
@NamedQuery(name="Detail.findAll", query="SELECT d FROM Detail d")
public class Detail  extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private Master master;

	public Detail() {
	}


	@Id
	@Column(name = "ID", nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CODE", nullable = false, length = 15)
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "NAME", nullable = false, length = 30)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}


	//bi-directional many-to-one association to Master
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TB_MASTER_ID")
	public Master getMaster() {
		return this.master;
	}

	public void setMaster(Master master) {
		this.master = master;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Detail other = (Detail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}