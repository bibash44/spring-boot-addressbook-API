package com.addressbook.springboot.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.addressbook.springboot.api.model.AddressBook;
import com.addressbook.springboot.api.service.AddressBookService;

/* 
 * Restful Controller for END point routes
 * Defining base ROUTE for all other routes
 */
@RestController
@RequestMapping("api/v1/address-book")
public class AddressBookController {
	/*
	 * Automatic dependency injection of AddressBook service in controller class
	 */
	@Autowired
	private AddressBookService addressBookService;

	public AddressBookController(AddressBookService addressBookService) {
		super();
		this.addressBookService = addressBookService;
	}

	/*
	 * API to serve all listings of available addressBooks data
	 */
	@GetMapping("/all")
	public ResponseEntity<List<AddressBook>> getAllAddressBook() {

		List<AddressBook> addressBookListFromDatabase = addressBookService.getAllAddressBook();

		/*
		 * Avoiding null pointer exception using try catch Returns all list of data if
		 * available Return 500 internal server error code if the database table is
		 * empty
		 */

		try {

			if (addressBookListFromDatabase.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(addressBookListFromDatabase, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * API to serve single data based on id*
	 */
	@GetMapping("/{id}")
	public ResponseEntity<AddressBook> getOneAddressBook(@PathVariable Long id) {
		Optional<AddressBook> data = addressBookService.getAddressBook(id);

		if (data.isPresent()) {
			return new ResponseEntity<>(data.get(), HttpStatus.OK);
		}
		/*
		 * Returns data 404 not found if no data is found in the database table
		 */
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

	/*
	 * Accepts AddressBook Model as object obtained from @RequestBody
	 * Creates new row in database
	 * Returns the Object of Newly created data
	 */
	@PostMapping
	public ResponseEntity<AddressBook> addAddressBook(@RequestBody AddressBook newaddressBook) {
		AddressBook addressBook = addressBookService.addAddressBook(newaddressBook);
		return new ResponseEntity<>(addressBook, HttpStatus.CREATED);
	}
	
	/*
	 * Accepts AddressBook Model as object obtained from @RequestBody and Id from @PathVariable
	 * Updated the existing row in database
	 * Returns the Object of updated data
	 */

	@PutMapping("/{id}")
	public ResponseEntity<AddressBook> updateAddressBook(@PathVariable Long id,
			@RequestBody AddressBook addressBookDataToUpdate) {
		AddressBook addressBook = addressBookService.updateAddressBook(id, addressBookDataToUpdate);

		return new ResponseEntity<>(addressBook, HttpStatus.CREATED);
	}
	
	/*
	 * Accepts Id from @PathVariable 
	 * Deletes the row that matches with the ID provided
	 */

	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteAddressBook(@PathVariable Long id) {
		addressBookService.deleteAddressBook(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
