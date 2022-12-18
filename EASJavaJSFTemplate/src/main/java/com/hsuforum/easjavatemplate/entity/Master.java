package com.hsuforum.easjavatemplate.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.hsuforum.common.entity.impl.BaseEntityImpl;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the TB_MASTER database table.
 * 
 */
@Entity
@Table(name="TB_MASTER")
@NamedQuery(name="Master.findAll", query="SELECT m FROM Master m")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@NoArgsConstructor
public class Master  extends BaseEntityImpl<String> {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "ID", nullable = false, length = 36)
	@EqualsAndHashCode.Include()
	private String id;
	@Column(name = "CODE", nullable = false, length = 15)
	private String code;
	@Column(name = "NAME", nullable = false, length = 30)
	private String name;
	//bi-directional many-to-one association to Detail
	@OneToMany(mappedBy="master",targetEntity=Detail.class)
	private Set<Detail> details;

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

}