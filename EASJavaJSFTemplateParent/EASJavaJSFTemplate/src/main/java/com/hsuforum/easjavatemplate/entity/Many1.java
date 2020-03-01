package com.hsuforum.easjavatemplate.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.hsuforum.common.entity.impl.BaseEntityImpl;


/**
 * The persistent class for the TB_MANY1 database table.
 * 
 */
@Entity
@Table(name="TB_MANY1")
@NamedQuery(name="Many1.findAll", query="SELECT m FROM Many1 m")
public class Many1  extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private String name;
	private Set<Many2> many2s;

	public Many1() {
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


	//bi-directional many-to-many association to Many2
	@ManyToMany(targetEntity = Many2.class, cascade = { CascadeType.MERGE })
	@JoinTable(
		name="TB_MANY_REL"
		, joinColumns={
			@JoinColumn(name="TB_MANY1_ID")
			}
		, inverseJoinColumns={
			@JoinColumn(name="TB_MANY2_ID")
			}
		)
	public Set<Many2> getMany2s() {
		return this.many2s;
	}

	public void setMany2s(Set<Many2> many2s) {
		this.many2s = many2s;
	}

	public Many2 addMany2(Many2 many2) {
		if (getMany2s() == null) {
			setMany2s(new LinkedHashSet<Many2>());
		}
		getMany2s().add(many2);
		many2.getMany1s().add(this);

		return many2;
	}

	public Many2 removeMany2(Many2 many2) {
		if (getMany2s() != null) {
			getMany2s().remove(many2);
		}
		many2.getMany1s().remove(this);

		return many2;
	}

	public void clearMany2s() {
		this.setMany2s(null);
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
		Many1 other = (Many1) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}