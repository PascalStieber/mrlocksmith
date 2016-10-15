package com.pascalstieber.mrlocksmith.order.data;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "orderentity", path = "orderentity")
public interface OrderRepository extends PagingAndSortingRepository<OrderEntity, Long> {


    List<OrderEntity> findByUserid(@Param("userid") long userid);
    
    

}
