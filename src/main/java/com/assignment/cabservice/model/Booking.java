package com.assignment.cabservice.model;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")  // Explicitly mapping to the "booking" table in MySQL
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "car_id", nullable = false)
    private Integer carId;

    @Column(name = "driver_id", nullable = true)
    private Integer driverId;

    @Column(name = "status", nullable = false)
    private String status;

    // ✅ Getters and Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public Integer getCarId() { return carId; }
    public void setCarId(Integer carId) { this.carId = carId; }

    public Integer getDriverId() { return driverId; }
    public void setDriverId(Integer driverId) { this.driverId = driverId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", username='" + username + '\'' +  // Wrapped in quotes
                ", carId=" + carId +
                ", driverId=" + driverId +
                ", status='" + status + '\'' +
                '}';
    }
}