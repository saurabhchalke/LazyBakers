package lazybakers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lazybakers.model.dto.CouponDTO;
import lazybakers.model.entity.Coupon;
import lazybakers.service.CouponService;

@Controller
@RequestMapping("coupon")
public class CouponController {
	
	@Autowired
	CouponService couponService;
	
	@RequestMapping(value = "/coupon", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public void addcoupon(@RequestBody CouponDTO couponDTO) {
		couponService.createCoupon(couponDTO.getCouponCode(), couponDTO.getOfferPerc(), couponDTO.getMaxAmount());
	}
	
	@RequestMapping(value = "/coupon/{cId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Coupon getCoupon(@PathVariable String cId){
        return couponService.getCouponByCode(cId);
    }

}
