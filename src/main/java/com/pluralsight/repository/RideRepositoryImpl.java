package com.pluralsight.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;
import com.pluralsight.util.RideRowMapper;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Ride> getRides() {

		List<Ride> rides = jdbcTemplate.query("select * from ride", new RideRowMapper());

		return rides;
	}

	@Override
	public Ride createRide(Ride ride) {
		// Used to insert, update and delete functionalities
		// jdbcTemplate.update("insert into ride(name, duration) values(?,?)",
		// ride.getName(),ride.getDuration());

		//// Datasource bean configured in the config xml passed in for
		//// establishing DB connection
		// SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
		//
		//// Create table object
		// List<String> columns = new ArrayList<>();
		//
		// columns.add("name");
		// columns.add("duration");
		//
		// insert.setTableName("ride");
		// insert.setColumnNames(columns);
		// insert.setGeneratedKeyName("id");
		//
		//// Map columns and values to form data and execute
		// Map<String, Object> data = new HashMap<>();
		//
		// data.put("name", ride.getName());
		// data.put("duration", ride.getDuration());
		//
		// Number id = insert.executeAndReturnKey(data);
		//
		// System.out.println("Key Value : " + id);

		// To Insert a record and fetch the details immediately for POST
		// operation for JDBC
		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {

				PreparedStatement ps = conn.prepareStatement("insert into ride(name, duration) values(?,?)",
						new String[] { "id" });
				ps.setString(1, ride.getName());
				ps.setInt(2, ride.getDuration());
				return ps;
			}
		}, keyHolder);

		Number id = keyHolder.getKey();

		return getRide(id.intValue());
	}

	@Override
	public Ride updateRide(Ride ride) {
		// Used to insert, update and delete functionalities
		jdbcTemplate.update("update ride set name=?, duration=? where id=?", ride.getName(), ride.getDuration(),
				ride.getId());

		return ride;
	}

	@Override
	public Ride getRide(Integer id) {

		Ride ride = jdbcTemplate.queryForObject("select * from ride where id=?", new RideRowMapper(), id);
		return ride;
	}

	@Override
	public void updateBatch(List<Object[]> pairs) {
		jdbcTemplate.batchUpdate("update ride set ride_date=? where id=?", pairs);
	}

	@Override
	public void deleteRide(Integer id) {
//		jdbcTemplate.update("delete from ride where id=?", id);
		
//		Named Parameter JDBC Template Example
		NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
		
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		
		namedTemplate.update("delete from ride where id = :id", params);
	}
	
	
}
