package in.trois.stockmanagement.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Shafeel
 *
 */
public abstract class AbstractController implements SecureSwaggerController {

	@RequestMapping(method = RequestMethod.GET)
	public String apiInfo() {
		return "Welcome to " + getClass() + " services";
	}
}
