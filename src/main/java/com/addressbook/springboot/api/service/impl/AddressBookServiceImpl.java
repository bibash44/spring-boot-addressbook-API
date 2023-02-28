package com.addressbook.springboot.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.addressbook.springboot.api.model.AddressBook;
import com.addressbook.springboot.api.service.AddressBookService;

@Service
public class AddressBookServiceImpl implements AddressBookService {

	@Override
	public List<AddressBook> getAllAddressBook() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<AddressBook> getAddressBook(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public AddressBook addAddressBook(AddressBook addressBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressBook updateAddressBook(Long id, AddressBook addressBook) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAddressBook(Long id) {
		// TODO Auto-generated method stub
		
	}

}
