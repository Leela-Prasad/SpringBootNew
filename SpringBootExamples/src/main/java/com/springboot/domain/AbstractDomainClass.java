package com.springboot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@MappedSuperclass
public abstract class AbstractDomainClass {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Version
	private Integer version;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false,updatable=false)
	private Date createdTimestamp;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedTimestamp;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public Date getUpdatedTimestamp() {
		return updatedTimestamp;
	}

	@PreUpdate
	@PrePersist
	public void updateTimeStamps() {
		updatedTimestamp=new Date();
		if(createdTimestamp==null)
			createdTimestamp=new Date();
	}
	

	
}
