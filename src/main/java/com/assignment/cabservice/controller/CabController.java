package com.assignment.cabservice.controller;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assignment.cabservice.exception.InvalidSeatingCapacityException;
import com.assignment.cabservice.model.Car;
import com.assignment.cabservice.model.Driver;
import com.assignment.cabservice.repository.CarRepository;
import com.assignment.cabservice.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CabController {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private DriverRepository driverRepository;

    @RequestMapping("list-cabs")
    public String listAllCars(ModelMap modelMap) {
        List<Car> cars=carRepository.findAll();
        modelMap.put("cars",cars);
        return "listCabs";
    }

    @RequestMapping("list-available-cars")
    public String listAllAvailableCarsForBooking(@RequestParam int seatingCapacity,ModelMap modelMap) {
        List<Car> cars=carRepository.findBySeatingCapacityAndAvailableForBookingTrue(seatingCapacity);
        modelMap.put("cars",cars);
        return "listCarsAvailableForBooking";
    }

    @RequestMapping(value="add-cab",method= RequestMethod.GET)
    public String showNewCarPage(Car car) {
        return "car";
    }

    //public String addNewTodo(@Valid Todo todo, ModelMap modelMap, BindingResult bindingResult) {
    @RequestMapping(value="add-cab",method= RequestMethod.POST)
    public String addNewCar(Car car,ModelMap modelMap,RedirectAttributes redirectAttributes) throws Exception {
        int capacity=car.getSeatingCapacity();
        if (capacity != 3 && capacity != 4 && capacity != 7) {
            modelMap.put("errorMessage", "❌ Allowed seating capacities: 3, 4, or 7 seats.");
            return "car"; // Return to the same form page to show the error
        }

        car.setAvailableForBooking(true);
        carRepository.save(car);
        redirectAttributes.addFlashAttribute("successMessage", "✅ Cab added successfully!");
        return "redirect:/list-cabs";
    }

    //http://localhost:8080/delete-car?id=502
    @RequestMapping(value="delete-car")
    public String deleteCar(@RequestParam int id, RedirectAttributes redirectAttributes ) {
        carRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("successMessage", "✅ Car deleted successfully!");
        return "redirect:/list-cabs";
    }



    //localhost:8080/assign-car/carId/503/driverId/152
    @GetMapping(value="assign-car/carId/{carId}/driverId/{driverId}")
    public String assignDriverToCar(@PathVariable int carId,@PathVariable int driverId) throws Exception {
        Driver driver=driverRepository.findById(driverId).orElseThrow(() ->
                new Exception("Driver not found with driverID - " + driverId));
        int previousAssignedCarId=driver.getAssignedCarId();
        driver.setAssignedCarId(carId);
        driver.setUsedCarIds(driver.getUsedCarIds()+","+carId);
        Car previousAssignedCar=carRepository.findById(previousAssignedCarId).orElseThrow(() ->
                new Exception("Car not found with carID - " + previousAssignedCarId));;
        previousAssignedCar.setDriverId(null);
        Car car=carRepository.findById(carId).orElseThrow(() ->
                new Exception("Car not found with carID - " + carId));;
        car.setDriverId(driverId);
        carRepository.save(previousAssignedCar);
        carRepository.save(car);
        driverRepository.save(driver);
        return "redirect:/list-cabs";
    }

    /*@GetMapping(path="/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        return user.get().getPosts();
    }*/
}
