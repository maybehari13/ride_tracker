package com.pluralsight.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.pluralsight.model.Ride;

import org.junit.Test;

public class RestControllerTest {

	@Test(timeout=10000)
	public void testGetRides() {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<List<Ride>> ridesResponse = restTemplate.exchange(
				"http://localhost:8088/ride_tracker/rides", HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Ride>>() {
				});
		List<Ride> rides = ridesResponse.getBody();

		for (Ride ride : rides) {
			System.out.println("Ride name: " + ride.getName());
		}
	}
	
	@Test(timeout=10000)
	public void testCreateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = new Ride();
		ride.setName("Union Trail Ride");
		ride.setDuration(29);
		
		ride = restTemplate.postForObject("http://localhost:8088/ride_tracker/ride", ride, Ride.class);
		
		System.out.println("Ride : " + ride.getName());
		
	}
	
	@Test(timeout=10000)
	public void testGetRide() {
		RestTemplate restTemplate = new RestTemplate();
		Ride ride = restTemplate.getForObject("http://localhost:8088/ride_tracker/ride/1", Ride.class);
		
		System.out.println("Ride : " + ride.getName());		
	}
	
	
	@Test(timeout=10000)
	public void testUpdateRide() {
		RestTemplate restTemplate = new RestTemplate();
		
		Ride ride = restTemplate.getForObject("http://localhost:8088/ride_tracker/ride/1", Ride.class);
		
		ride.setName("Canyon Trail Ride");
		ride.setDuration(85);
		
//		To test update
		restTemplate.put("http://localhost:8088/ride_tracker/ride", ride);
		
		System.out.println("Id : " + ride.getId() + "   Ride : " + ride.getName() 
							+ "  Duration : " + ride.getDuration());
		
	}
	
	@Test(timeout=10000)
	public void testUpdateBatch() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8088/ride_tracker/batch", Object.class);
	}
	
	@Test(timeout=10000)
	public void testdeleteRide() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete("http://localhost:8088/ride_tracker/delete/9");
	}
	
	@Test(timeout=10000)
	public void testException() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getForObject("http://localhost:8088/ride_tracker/test",Ride.class);
	}
	
}
