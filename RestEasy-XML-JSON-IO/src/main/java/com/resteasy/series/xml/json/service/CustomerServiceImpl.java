package com.resteasy.series.xml.json.service;


public class CustomerServiceImpl implements ICustomerService {

	/**
	 * returns a String value with SUCCESS message after adding/saving a New Customer information
	 */
	@Override
	public String createOrSaveNewCustomerInfo(CustomerType customerType) {

		// get the player information from formal arguments and inserts into database & return playerId (primary_key)
		return "Customer information saved successfully with Customer_ID " + customerType.getCustomerId();
	}

	/**
	 * retrieves a customer information object based on the ID supplied in the formal argument using @PathParam
	 */
	@Override
	public CustomerType getCustomerInfo(int customerId) {

		// retrieve player based on the id supplied in the formal argument
		CustomerType customerType = new CustomerType();
		customerType.setCustomerId(customerId);
		customerType.setName("Captain Planet");
		customerType.setAge(40);
		return customerType;
	}

	/**
	 * returns a String value with SUCCESS message after updating customer information
	 */
	@Override
	public String updateCustomerInfo(CustomerType customerType) {

		// update customer info & return SUCCESS message
		return "Customer information updated successfully";
	}

	/**
	 * returns a String value with SUCCESS message after deleting customer information
	 */
	@Override
	public String deleteCustomerInfo(CustomerType customerType) {

		// delete customer info & return SUCCESS message
		return "Customer information deleted successfully";
	}

	/**
	 * retrieves all customer stored
	 */
	@Override
	public CustomerListType getAllCustomerInfo() {

		// create a object of type CustomerListType which takes customer objects in its list
		CustomerListType customerListType = new CustomerListType();

		CustomerType customerOne = new CustomerType();
		customerOne.setCustomerId(10025);
		customerOne.setName("Super Man");
		customerOne.setAge(37);
		customerListType.getCustomerType().add(customerOne); // add to customerListType

		CustomerType customerTwo = new CustomerType();
		customerTwo.setCustomerId(10026);
		customerTwo.setName("Batman");
		customerTwo.setAge(33);
		customerListType.getCustomerType().add(customerTwo); // add to customerListType

		return customerListType;
	}
}