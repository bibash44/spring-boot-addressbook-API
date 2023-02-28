package com.addressbook.springboot.api.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.addressbook.springboot.api.model.AddressBook;
import com.addressbook.springboot.api.repository.AddressBookRepo;
import com.addressbook.springboot.api.service.AddressBookService;

/*
 * Service or a business logic layer
 * All Logic and methods for definding CRUD operation
 */
@Service
public class AddressBookServiceImpl implements AddressBookService {

	/*
	 * Automatic dependency injection of AddressBook repository in service class
	 */
	@Autowired
	private AddressBookRepo addressBookRepo;

	public AddressBookServiceImpl(AddressBookRepo addressBookRepo) {
		super();
		this.addressBookRepo = addressBookRepo;
	}

	/*
	 * Returns list of AddressBook objects
	 */
	@Override
	public List<AddressBook> getAllAddressBook() {
		List<AddressBook> addressBooks = new ArrayList<>();
		addressBookRepo.findAll().forEach(addressBooks::add);

		return addressBooks;

	}

	/*
	 * Returns one AddressBook objects data based on Id If not present returns empty
	 * object
	 */

	@Override
	public Optional<AddressBook> getAddressBook(Long id) {
		Optional<AddressBook> getOneAddressBook = addressBookRepo.findById(id);

		return getOneAddressBook;
	}

	/*
	 * Saves new data in the database Returns the instance of saved object
	 */

	@Override
	public AddressBook addAddressBook(AddressBook addressBook) {
		return addressBookRepo.save(addressBook);

	}

	/*
	 * Finds the old data based on Id Checks if data is present and updates Returns
	 * instance of updated data
	 */
	@Override
	public AddressBook updateAddressBook(Long id, AddressBook addressBookDataForUpdating) {

		Optional<AddressBook> oldAddressBookData = addressBookRepo.findById(id);
		AddressBook dataToUpdate = oldAddressBookData.get();

		if (oldAddressBookData.isPresent()) {

			dataToUpdate.setFname(addressBookDataForUpdating.getFname());
			dataToUpdate.setLname(addressBookDataForUpdating.getLname());
			dataToUpdate.setAddress(addressBookDataForUpdating.getAddress());
			dataToUpdate.setPhone(addressBookDataForUpdating.getPhone());
			addressBookRepo.save(dataToUpdate);
			return dataToUpdate;
		}

		return dataToUpdate;
	}

	/*
	 * Deletes data based on Id
	 */
	@Override
	public void deleteAddressBook(Long id) {
		addressBookRepo.deleteById(id);

	}

}
