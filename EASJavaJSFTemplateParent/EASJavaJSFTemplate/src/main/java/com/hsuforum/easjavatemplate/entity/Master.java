package com.hsuforum.easjavatemplate.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.hsuforum.common.entity.impl.BaseEntityImpl;


/**
 * The persistent class for the tb_master database table.
 * 
 */
@Entity
@Table(name="tb_master")
@NamedQuery(name="Master.findAll", query="SELECT m FROM Master m")
public class Master  extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private Set<Detail> details;

	public Master() {
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


	//bi-directional many-to-one association to Detail
	@OneToMany(mappedBy="master",targetEntity=Detail.class)
	public Set<Detail> getDetails() {
		return this.details;
	}

	public void setDetails(Set<Detail> details) {
		this.details = details;
	}

	public Detail addDetail(Detail detail) {
		if(this.getDetails()==null){
			this.setDetails(new LinkedHashSet<Detail>());
		}
		getDetails().add(detail);
		detail.setMaster(this);

		return detail;
	}

	public Detail removeDetail(Detail detail) {
		if(this.getDetails()!=null){
			this.getDetails().remove(detail);
		}
		detail.setMaster(null);

		return detail;
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
		Master other = (Master) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}