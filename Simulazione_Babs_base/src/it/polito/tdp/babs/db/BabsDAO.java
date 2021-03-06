package it.polito.tdp.babs.db;

import it.polito.tdp.babs.model.Station;
import it.polito.tdp.babs.model.Trip;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BabsDAO {

	public List<Station> getAllStations() {
		List<Station> result = new ArrayList<Station>();
		
		String sql = "SELECT * FROM station";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				Station station = new Station(rs.getInt("station_id"), rs.getString("name"), rs.getDouble("lat"), rs.getDouble("long"), rs.getInt("dockcount"));
				result.add(station);
			}
			st.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in database query", e);
		}

		return result;
	}

	public List<Trip> getAllTrips() {
		List<Trip> result = new LinkedList<Trip>();
		
		String sql = "SELECT * FROM trip";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Trip trip = new Trip(rs.getInt("tripid"), rs.getInt("duration"), rs.getTimestamp("startdate").toLocalDateTime(), rs.getInt("startterminal"),
						rs.getTimestamp("enddate").toLocalDateTime(), rs.getInt("endterminal"));
				result.add(trip);
			}
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in database query", e);
		}

		return result;
	}

	public int getPickNumber(Station stemp, LocalDate ld) {
		int result;
		
		String sql = "select count(*) as counter "+
				"from trip "+
				"where DATE(StartDate)=? and StartTerminal=?";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, Date.valueOf(ld));
			st.setInt(2, stemp.getStationID());
			
			ResultSet rs = st.executeQuery();
			
			rs.first();
			result=rs.getInt("counter");
			
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in database query", e);
		}
		return result;
	}

	public int getDropNumber(Station stemp, LocalDate ld) {
		int result;
		Connection conn = DBConnect.getConnection();
		String sql = "select count(*) as counter "+
				"from trip "+
				"where DATE(EndDate)=? and EndTerminal=?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, Date.valueOf(ld));
			st.setInt(2, stemp.getStationID());
			
			ResultSet rs = st.executeQuery();
			
			rs.first();
			result=rs.getInt("counter");
			
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in database query", e);
		}
		return result;
		
	}

	public List<Trip> getTripsForDayPick(LocalDate ld) {
		
		List<Trip> result = new LinkedList<Trip>();
		
		String sql = "SELECT * FROM trip WHERE DATE(StartDate)=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, Date.valueOf(ld));
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Trip trip = new Trip(rs.getInt("tripid"), rs.getInt("duration"), rs.getTimestamp("startdate").toLocalDateTime(), rs.getInt("startterminal"),
						rs.getTimestamp("enddate").toLocalDateTime(), rs.getInt("endterminal"));
				result.add(trip);
			}
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in database query", e);
		}

		return result;
	}
	
	
public List<Trip> getTripsForDayDrop(LocalDate ld) {
		
		List<Trip> result = new LinkedList<Trip>();
		
		String sql = "SELECT * FROM trip WHERE DATE(EndDate)=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDate(1, Date.valueOf(ld));
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Trip trip = new Trip(rs.getInt("tripid"), rs.getInt("duration"), rs.getTimestamp("startdate").toLocalDateTime(), rs.getInt("startterminal"),
						rs.getTimestamp("enddate").toLocalDateTime(), rs.getInt("endterminal"));
				result.add(trip);
			}
			st.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in database query", e);
		}

		return result;
	}
}