package com.pascalstieber.mrlocksmith.register.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "contractor", path = "contractor")
public interface ContractorRepository extends PagingAndSortingRepository<Contractor, Long> {

    Contractor findById(@Param("id") long id);

}
