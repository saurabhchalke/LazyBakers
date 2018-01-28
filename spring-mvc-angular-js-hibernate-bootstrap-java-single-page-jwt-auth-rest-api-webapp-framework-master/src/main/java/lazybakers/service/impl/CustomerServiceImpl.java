package lazybakers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lazybakers.model.entity.Customer;
import lazybakers.model.repository.CustomerRepository;
import lazybakers.service.CustomerService;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Integer createCustomer(String customerFirstName, String customerLastName, String password,
			String mobilenumber, int points, String customerEmail) {
		return customerRepository.createCustomer(customerFirstName, customerLastName, password, 
	        		 mobilenumber, points, customerEmail);
	}

	@Override
	public void updateCustomer(int customerId, String customerFirstName, String customerLastName, String password,
			String mobilenumber, int points, String customerEmail) {
		customerRepository.updateCustomer(customerId, customerFirstName, customerLastName, password, mobilenumber, points, customerEmail);
		
	}

	@Override
	public void deleteCustomer(int customerId) {
		customerRepository.deleteCustomer(customerId);
		
	}

	@Override
	public Customer getCustomerById(int customerId) {
		return customerRepository.getCustomerById(customerId);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.getAllCustomer();
	}

}
