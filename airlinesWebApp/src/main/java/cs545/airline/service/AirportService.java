package cs545.airline.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.AirportDao;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

@Named
@ApplicationScoped
@Transactional
public class AirportService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// These services should be evaluated to reconsider which methods should be public 

	@Inject
	private AirportDao airportDao;
		

	public void create(Airport airport) {
		airportDao.create(airport);
	}

	public void delete(Airport airport) {
		airportDao.delete(airport);
	}

	public Airport update(Airport airport) {
		return airportDao.update(airport);
	}
		
	public Airport find(Airport airport) {
		return airportDao.findOne(airport.getId());
	}

	public Airport findByCode(String airportcode) {
		return airportDao.findOneByCode(airportcode);
	}

	public List<Airport> findByArrival(Flight flight) {
		if(flight!= null){
		return airportDao.findByArrival(flight.getId());}
		else{
			return null;
		}
	}

	public List<Airport> findByDeparture(Flight flight) {
		if(flight!= null){
		return airportDao.findByDeparture(flight.getId());}
		else{
			return null;
		}
	}

	public List<Airport> findByCity(String city) {
		return airportDao.findByCity(city);
	}

	public List<Airport> findByCountry(String country) {
		return airportDao.findByCountry(country);
	}

	public List<Airport> findByName(String name) {
		return airportDao.findByName(name);
	}

	public List<Airport> findAll() {
		return airportDao.findAll();
	}
	
	//ADDED
	public Airport findById(long id) {
		return airportDao.findOneById(id);
	}
	
	//need GET to be able to use
	public Collection<Airport> getAllAirport() {
		return airportDao.findAll();
	}
	
	public List<Airport> getFindByDeparture(Flight flight) {
		return this.findByDeparture(flight);
	}
	
	public List<Airport> getFindByArrival(Flight flight) {
		return this.getFindByArrival(flight);
	}

}