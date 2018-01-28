package lazybakers.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lazybakers.model.dto.AddressDTO;
import lazybakers.model.entity.Address;
import lazybakers.model.entity.User;
import lazybakers.service.AddressService;
import lazybakers.service.UserService;

@Controller
public class AddressController {
	
	private static Logger log = LoggerFactory.getLogger(ToppingController.class);
	@Autowired
	AddressService addressService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/address", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Integer addAddress(@RequestBody AddressDTO addressDTO) {
		User user = userService.getUserById(addressDTO.getUserId());
		return addressService.createAddress(addressDTO.getHouseNo(), addressDTO.getStreet(), addressDTO.getCity(), addressDTO.getState(), addressDTO.getPincode(), user);
	}
	
	@RequestMapping(value = "/address/{aId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Address getAddress(@PathVariable int aId) {
        return addressService.getAddressById(aId);
    }
	
	@RequestMapping(value = "/address/user/{uId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Address> getAddressByUser(@PathVariable int uId) {
        return addressService.getAddressByUserId(uId);
    }

}
