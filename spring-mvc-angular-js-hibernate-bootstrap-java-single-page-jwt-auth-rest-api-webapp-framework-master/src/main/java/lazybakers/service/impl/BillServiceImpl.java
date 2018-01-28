package lazybakers.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lazybakers.model.entity.Address;
import lazybakers.model.entity.Bill;
import lazybakers.model.entity.Coupon;
import lazybakers.model.entity.User;
import lazybakers.model.repository.BillRepository;
import lazybakers.service.BillService;

@Service("billService")
@Transactional
public class BillServiceImpl implements BillService {
	
	@Autowired
	BillRepository billRepository;

	@Override
	public Integer createBill(Date billDate, int totalItems, float billAmount, float tax, String stage, int feedback,
			String comments, String offerType, Coupon coupon, Address address, User user) {
		return billRepository.createBill(billDate, totalItems, billAmount, tax, stage, feedback, comments, offerType, coupon, address, user);
	}

	@Override
	public Bill getBillById(int billId) {
		return billRepository.getBillById(billId);
	}

}
