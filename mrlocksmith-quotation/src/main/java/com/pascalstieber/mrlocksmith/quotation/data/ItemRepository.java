package com.pascalstieber.mrlocksmith.quotation.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "item", path = "item")
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {

    List<Item> findByQuotation(@Param("quotation") Quotation quotation);
    
    @Query("SELECT SUM(i.value) FROM Item i WHERE i.quotation = :quotation")
    public String getSumOfQuotation(@Param("quotation") Quotation quotation);
    
    
}
