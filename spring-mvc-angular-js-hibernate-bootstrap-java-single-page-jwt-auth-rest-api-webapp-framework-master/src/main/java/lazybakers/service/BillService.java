package lazybakers.service;

import java.util.Date;

import lazybakers.model.entity.Address;
import lazybakers.model.entity.Bill;
import lazybakers.model.entity.Coupon;
import lazybakers.model.entity.User;

public interface BillService {
	
	public Integer createBill(Date billDate, int totalItems, float billAmount, float tax, String stage, int feedback,
			String comments, String offerType, Coupon coupon, Address address, User user);
	
	public Bill getBillById(int billId);

}
