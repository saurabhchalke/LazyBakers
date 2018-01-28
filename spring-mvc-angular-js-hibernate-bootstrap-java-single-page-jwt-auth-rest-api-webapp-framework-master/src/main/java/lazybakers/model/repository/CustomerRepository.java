package lazybakers.model.repository;

import java.util.List;

import lazybakers.model.entity.Customer;

public interface CustomerRepository {
	
	public Integer createCustomer(String customerFirstName, String customerLastName, String password, String mobilenumber, int points,
			String customerEmail);
	
	public void updateCustomer(int customerId, String customerFirstName, String customerLastName, String password, String mobilenumber, int points,
			String customerEmail);
	
	public void deleteCustomer(int customerId);
	
	public Customer getCustomerById(int customerId);
	
	public List<Customer> getAllCustomer();

}
