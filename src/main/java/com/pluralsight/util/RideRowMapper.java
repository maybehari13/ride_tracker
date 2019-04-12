/*
 * Copyright (C) 2019, Liberty Mutual Group
 *
 * Created on Mar 12, 2019
 */

package com.pluralsight.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.pluralsight.model.Ride;

/**
 * @author n0172808
 *
 */
public class RideRowMapper implements RowMapper<Ride>{

	@Override
	public Ride mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Ride ride = new Ride();
		ride.setId(rs.getInt("id"));
		ride.setName(rs.getString("name"));
		ride.setDuration(rs.getInt("duration"));			
		
		return ride;
	}

}
