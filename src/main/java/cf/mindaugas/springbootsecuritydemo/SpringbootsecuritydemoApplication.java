package cf.mindaugas.springbootsecuritydemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.Ordered;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SpringBootApplication
@Controller
public class SpringbootsecuritydemoApplication implements WebMvcConfigurer {

	// .. also add the controller for the page
	@GetMapping("/")
	public String index(){
		return "index";
	}

	@GetMapping("/profile")
	public String profile(){
		return "profile/index";
	}

	@GetMapping("/admin")
	public String admin(){
		return "admin/index";
	}

	@GetMapping("/power_user")
	public String powerUser(){
		return "power_user/index";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootsecuritydemoApplication.class, args);
	}
	// ... inside the __Application.java
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("customLogin");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
	}

}
