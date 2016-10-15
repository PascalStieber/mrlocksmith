package com.pascalstieber.mrlocksmith.register.data;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserRepository extends
		PagingAndSortingRepository<User, Long> {

	List<User> findByFirstname(@Param("firstname") String firstname);
	User findById(@Param("id") Long id);
	
	@Query("SELECT u.adresses FROM User u WHERE u.id = :id")
	public List<Adress> getUserAdressesByUserId(@Param("id") long id);
	
	@Query("SELECT u.adresses FROM User u WHERE u.id = :id AND u.orderid= :orderid")
	public Adress getOneUserAdressesByUseridAndOrderid(@Param("id") long id, @Param("orderid") long orderid);
	
	User findByEmail(@Param("email") String email);

}
