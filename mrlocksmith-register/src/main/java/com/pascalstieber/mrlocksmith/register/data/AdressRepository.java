package com.pascalstieber.mrlocksmith.register.data;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "adress", path = "adress")
public interface AdressRepository extends PagingAndSortingRepository<Adress, Long> {

    Adress findById(@Param("id") long id);
    List<Adress> findByUsers_firstname(String firstname);

}
