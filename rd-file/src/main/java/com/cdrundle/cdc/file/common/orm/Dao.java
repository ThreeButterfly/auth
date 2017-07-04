package com.cdrundle.cdc.file.common.orm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface Dao<T extends LongIdEntity> extends JpaRepository<T, Long>{
	
}
