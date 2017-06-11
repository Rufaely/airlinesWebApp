package cs545.airline.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import cs545.airline.dao.AirlineDao;
import cs545.airline.model.Airline;
import cs545.airline.model.Flight;

@Named
@ApplicationScoped
@Transactional
public class AirlineService implements Serializable {

	// These services should be evaluated to reconsider which methods should be
	// public

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private AirlineDao airlineDao;

	public void create(Airline airport) {
		airlineDao.create(airport);
	}

	public void delete(Airline airport) {
		airlineDao.delete(airport);
	}

	public Airline update(Airline airport) {
		return airlineDao.update(airport);
	}

	public Airline find(Airline airport) {
		return airlineDao.findOne(airport.getId());
	}

	public Airline findByName(String name) {
		return airlineDao.findOneByName(name);
	}

	public List<Airline> findByFlight(Flight flight) {
		if (flight != null) {
			return airlineDao.findByFlight(flight.getId());
		} else {
			return null;
		}
	}

	public List<Airline> findAll() {
		return airlineDao.findAll();
	}

	// ADDED
	public Airline findById(long id) {
		return airlineDao.findOneById(id);
	}

	// need GET to be able to use
	public Collection<Airline> getAllAirline() {
		return airlineDao.findAll();
	}

	public List<Airline> getFindByFlight(Flight flight) {
		return this.findByFlight(flight);
	}

}
