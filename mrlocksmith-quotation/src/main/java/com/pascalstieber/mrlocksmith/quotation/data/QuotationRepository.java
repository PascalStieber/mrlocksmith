package com.pascalstieber.mrlocksmith.quotation.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "quotation", path = "quotation")
public interface QuotationRepository extends PagingAndSortingRepository<Quotation, Long> {

    Quotation findById(@Param("id") long id);

    List<Quotation> findByOrderidAndContractorid(@Param("orderid") long orderid, @Param("contractorid") long contractorid);

    @Query("SELECT q.items FROM Quotation q WHERE q.id = :id")
    public List<Item> getItemsByQuotationId(@Param("id") long id);
    
    @Query("SELECT q FROM Quotation q WHERE q.orderid = :orderid")
    public List<Quotation> getAllQuotationsForOrder(@Param("orderid") long orderid);
}
