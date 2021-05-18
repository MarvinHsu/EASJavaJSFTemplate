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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The persistent class for the TB_MANY1 database table.
 * 
 */
@Entity
@Table(name = "TB_MANY1")
@NamedQuery(name = "Many1.findAll", query = "SELECT m FROM Many1 m")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class Many1 extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, length = 36)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "CODE", nullable = false, length = 15)
	private String code;
	@Column(name = "NAME", nullable = false, length = 30)
	private String name;
	// bi-directional many-to-many association to Many2
	@ManyToMany(targetEntity = Many2.class, cascade = { CascadeType.MERGE })
	@JoinTable(name = "TB_MANY_REL", joinColumns = { @JoinColumn(name = "TB_MANY1_ID") }, inverseJoinColumns = {
			@JoinColumn(name = "TB_MANY2_ID") })
	private Set<Many2> many2s;

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

}