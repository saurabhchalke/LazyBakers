package lazybakers.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lazybakers.model.entity.Address;
import lazybakers.model.entity.User;
import lazybakers.model.repository.AddressRepository;
import lazybakers.service.AddressService;

@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	AddressRepository addressRepository;

	@Override
	public Integer createAddress(int houseNo, String street, String city, String state, String pincode, User user) {
		return addressRepository.createAddress(houseNo, street, city, state, pincode, user);
	}

	@Override
	public Address getAddressById(int addressId) {
		return addressRepository.getAddressById(addressId);
	}

	@Override
	public List<Address> getAddressByUserId(int userId) {
		return addressRepository.getAddressByUserId(userId);
	}

}
