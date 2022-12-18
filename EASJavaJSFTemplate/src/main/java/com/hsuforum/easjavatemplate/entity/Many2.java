package com.hsuforum.easjavatemplate.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the TB_MANY2 database table.
 * 
 */
@Entity
@Table(name="TB_MANY2")
@NamedQuery(name="Many2.findAll", query="SELECT m FROM Many2 m")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class Many2  extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, length = 36)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "CODE", nullable = false, length = 15)
	private String code;
	@Column(name = "NAME", nullable = false, length = 30)
	private String name;
	//bi-directional many-to-many association to Many1
	@ManyToMany(mappedBy = "many2s", fetch = FetchType.LAZY, targetEntity = Many1.class)
	private Set<Many1> many1s;

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
	
}