package com.hsuforum.easjavatemplate.entity.primary;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the TB_DETAIL database table.
 * 
 */
@Entity
@Table(name="TB_DETAIL")
@NamedQuery(name="Detail.findAll", query="SELECT d FROM Detail d")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper=false)
@NoArgsConstructor
public class Detail  extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, length = 36)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "CODE", nullable = false, length = 15)
	private String code;
	@Column(name = "NAME", nullable = false, length = 30)
	private String name;
	//bi-directional many-to-one association to Master
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TB_MASTER_ID")
	private Master master;

}