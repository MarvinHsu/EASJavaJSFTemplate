package com.hsuforum.easjavatemplate.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hsuforum.common.entity.impl.BaseEntityImpl;


/**
 * The persistent class for the tb_many2 database table.
 * 
 */
@Entity
@Table(name="tb_many2")
@NamedQuery(name="Many2.findAll", query="SELECT m FROM Many2 m")
public class Many2  extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private Set<Many1> many1s;

	public Many2() {
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


	//bi-directional many-to-many association to Many1
	@ManyToMany(mappedBy = "many2s", fetch = FetchType.LAZY, targetEntity = Many1.class)
	public Set<Many1> getMany1s() {
		return this.many1s;
	}

	public void setMany1s(Set<Many1> many1s) {
		this.many1s = many1s;
	}

	public Many1 addMany1(Many1 many1) {
		if (getMany1s() == null) {
			setMany1s(new LinkedHashSet<Many1>());
		}
		getMany1s().add(many1);
		many1.getMany2s().add(this);

		return many1;
	}

	public Many1 removeMany1(Many1 many1) {
		if (getMany1s() != null) {
			getMany1s().remove(many1);
		}
		many1.getMany2s().remove(this);

		return many1;
	}

	public void clearMany1s() {
		this.setMany1s(null);
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
		Many2 other = (Many2) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}