package com.assignment.cabservice.controller;

import com.assignment.cabservice.repository.CarRepository;
import com.assignment.cabservice.repository.DriverRepository;
import com.assignment.cabservice.repository.UserRepository;
import com.assignment.cabservice.repository.CustomerBookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import com.assignment.cabservice.model.User;


import java.util.Collection;

@Controller
@SessionAttributes({"username", "role"})
public class WelcomeController {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomerBookingRepository bookingRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String gotoWelcomePage(ModelMap model) {
        String username = getLoggedInUsername();
        String role = getUserRole();




        model.put("username", username);
        model.put("role", role);

        // Fetch counts from the database
        model.put("totalCars", carRepository.count());
        model.put("totalDrivers", driverRepository.count());
        model.put("totalUsers", userRepository.count());
        model.put("totalBookings", bookingRepository.count());

        User loggedInUser = userRepository.findByUsername(username);

        if (loggedInUser != null) {
            model.put("userBookings", bookingRepository.findByCustomerName(username).size());
        } else {
            model.put("userBookings", 0); // Default to 0 if no user found
        }


        return "welcome"; // Redirect all users to welcome.jsp
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    private String getUserRole() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            return authority.getAuthority(); // Return first assigned role (e.g., "ROLE_ADMIN")
        }
        return "ROLE_USER"; // Default role if no role found
    }
}
