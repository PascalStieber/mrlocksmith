package com.pascalstieber.mrlocksmith.register.data;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends
		PagingAndSortingRepository<User, Long> {

	List<User> findByFirstname(@Param("firstname") String firstname);

}
