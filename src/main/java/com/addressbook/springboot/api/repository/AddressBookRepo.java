package com.addressbook.springboot.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/*
 * JPA repository for entity AddressBook 
 */
@Repository
public interface AddressBookRepo extends JpaRepository<AddressBookRepo, Long>{

}
