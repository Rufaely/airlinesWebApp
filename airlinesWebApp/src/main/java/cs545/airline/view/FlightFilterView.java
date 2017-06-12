package cs545.airline.view;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import cs545.airline.model.Flight;

import cs545.airline.service.FlightService;

@ManagedBean(name = "dtFlightFilterView")
@ViewScoped
public class FlightFilterView implements Serializable {

	private List<Flight> flights;

	private List<Flight> filteredFlights;

	@ManagedProperty("#{flightService}")
	private FlightService service;

	@PostConstruct
	public void init() {
		flights = service.findAll();
	}

	public List<Flight> getFilteredFlights() {
		return filteredFlights;
	}

	public List<Flight> getFlights() {
		return flights;
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}

	public void setFilteredFlights(List<Flight> filteredFlights) {
		this.filteredFlights = filteredFlights;
	}

	public void setService(FlightService service) {
		this.service = service;
	}
	
    public void clearFilters() {
        // Try to clear the datatable filters
        this.filteredFlights = null;     
    }
}
