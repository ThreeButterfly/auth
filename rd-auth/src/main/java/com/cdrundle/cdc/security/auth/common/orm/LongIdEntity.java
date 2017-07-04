package com.cdrundle.cdc.security.auth.common.orm;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public class LongIdEntity implements Serializable{

	private static final long serialVersionUID = -3213729923164986612L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Version
	private Long version;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the version
	 */
	public Long getVersion() {
		return version;
	}
	
	/**
	 * @param version the version to set
	 */
	public void setVersion(Long version) {
		this.version = version;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (!(obj instanceof LongIdEntity)) {
			return false;
		}
		LongIdEntity temp = (LongIdEntity) obj;
		if (temp == null || temp.getId() == null) {
			return false;
		}
		return temp.getId().equals(this.getId());
	}
	
}
