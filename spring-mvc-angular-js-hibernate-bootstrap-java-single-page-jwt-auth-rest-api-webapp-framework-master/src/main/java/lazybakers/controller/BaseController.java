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

import lazybakers.model.dto.BaseDTO;
import lazybakers.model.entity.Base;
import lazybakers.service.BaseService;

@Controller
//@RequestMapping("base")
public class BaseController {
	
	private static Logger log = LoggerFactory.getLogger(ToppingController.class);
	@Autowired
	BaseService baseService;
	
	@RequestMapping(value = "/base", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public Integer addbase(@RequestBody BaseDTO baseDTO) {
		return baseService.createBase(baseDTO.getBaseName(), baseDTO.getPrice());
	}
	
	@RequestMapping(value = "/base", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Base> getallbase() {
		return baseService.getAllBase();
	}
	
	@RequestMapping(value = "/base/{bId}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Base getBaseById(@PathVariable int bId) {
		return baseService.getBaseById(bId);
	}

}
