package com.pascalstieber.mrlocksmith.quotation.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "quotation", path = "quotation")
public interface QuotationRepository extends PagingAndSortingRepository<Quotation, Long> {

    Quotation findById(@Param("id") long id);

}
