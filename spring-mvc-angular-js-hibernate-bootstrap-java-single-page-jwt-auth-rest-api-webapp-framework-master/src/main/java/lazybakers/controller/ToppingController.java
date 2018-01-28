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

import lazybakers.model.dto.ToppingDTO;
import lazybakers.model.entity.Topping;
import lazybakers.service.ToppingService;

@Controller
//@RequestMapping("topping")
public class ToppingController {
	private static Logger LOG = LoggerFactory.getLogger(ToppingController.class);
	@Autowired
	ToppingService toppingService;
	
	@RequestMapping(value = "/topping", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Topping> getalltopping() {
		return toppingService.getAllTopping();
	}
	
	@RequestMapping(value = "/topping/{tId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Topping gettopping(@PathVariable int tId){
        return toppingService.getToppingById(tId);
    }
	
	@RequestMapping(value = "/topping", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Integer addtopping(@RequestBody ToppingDTO toppingDTO) {
		return toppingService.createTopping(toppingDTO.gettoppingname(), toppingDTO.getPrice(), toppingDTO.getStock(), toppingDTO.isVegetarian());
	}

}
