package com.addressbook.springboot.api.service;

import java.util.List;
import java.util.Optional;

import com.addressbook.springboot.api.model.AddressBook;


/*
 * DATA ACCESS OBJECT (DAO) as service methods 
 * These methods are over ridden by implementing in serviceImplemenation class
 * Total 5 methods are responsible for CRUD operation
 */
public interface AddressBookService {
	List<AddressBook> getAllAddressBook();
	Optional<AddressBook> getAddressBook(Long id);
	AddressBook addAddressBook(AddressBook addressBook);
	AddressBook updateAddressBook(Long id, AddressBook addressBook);
	void deleteAddressBook(Long id);
}
