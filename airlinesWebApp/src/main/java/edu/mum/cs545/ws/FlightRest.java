package edu.mum.cs545.ws;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@Path("Flight")
public class FlightRest {

	@Inject
	private FlightService flightService;
	@Inject
	private AirportService airportService;
	@Inject
	private AirlineService airlineService;
	@Inject
	private AirplaneService airplaneService;
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@POST
	@Path("/Create")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String CreateFlight(Flight flight) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS CREATE JSON : " + flight);
		flightService.create(flight);
		return SUCCESS_RESULT;
	}
	
	
	@DELETE
	@Path("/Delete/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String DeleteFlight(@PathParam("id") long id) throws IOException {
		Flight flight = flightService.findById(id);
		System.out.println("ENVIO DE DATOS DESDE WS DELETE: " + flight);
		flightService.delete(flight);
		return SUCCESS_RESULT;
	}

	
	@POST
	@Path("/Update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Flight UpdateFlight(	Flight flight) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS UPDATE: " + flight);
		return flightService.update(flight);
	}
	
	// =================================== GET WITHOUT
	// PARAMETERS===========================================================

	@GET
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> getFlights() {
		return flightService.findAll();
	}

	// =================================== GET WITH
	// QUERYPARAMETER
	// INPUT===========================================================

	@GET
	@Path("/FindByNumber")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> FindByNumberFlight(@QueryParam("Flightnr") String flightnr) throws IOException {
		if ("".equals(flightnr)) {
			return null;
		}
		return flightService.findByNumber(flightnr);
	}

	@GET
	@Path("/FindByArrivalDate")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByArrivalFlight(@QueryParam("datetime") String datetime) throws IOException {
		if ("".equals(datetime)) {
			return null;
		}
		  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		  
		try {
			System.out.println("FECHA FindByArrival-----"+formatter.parse(datetime));
			return flightService.findByArrival(formatter.parse(datetime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/FindByArrivalBetween")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> FindByArrivalBetweenFlight(@QueryParam("datetimeFrom") String datetimeFrom,
			@QueryParam("datetimeTo") String datetimeTo)
			throws IOException {
		if ("".equals(datetimeFrom) || "".equals(datetimeTo)) {
			return null;
		}
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		try {
			System.out.println("FECHA FindByArrivalBetween-----"+formatter.parse(datetimeFrom) + " -  " + formatter.parse(datetimeTo));
			return flightService.findByArrivalBetween(formatter.parse(datetimeFrom), formatter.parse(datetimeTo));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/FindByDeparture")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDepartureFlight(@QueryParam("datetime") String datetime) throws IOException {
		if ("".equals(datetime)) {
			return null;
		}
		  SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		  
		try {
			System.out.println("FECHA FindByDeparture -----"+formatter.parse(datetime));
			return flightService.findByDeparture(formatter.parse(datetime));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@GET
	@Path("/FindByDepartureBetween")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDepartureBetweenFlight(@QueryParam("datetimeFrom") String datetimeFrom,
			@QueryParam("datetimeTo") String datetimeTo)
			throws IOException {
		if ("".equals(datetimeFrom) || "".equals(datetimeTo)) {
			return null;
		}
		 SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yy");
		try {
			System.out.println("FECHA FindByDepartureBetween-----"+formatter.parse(datetimeFrom) + " -  " + formatter.parse(datetimeTo));

			return flightService.findByDepartureBetween(formatter.parse(datetimeFrom), formatter.parse(datetimeTo));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	//=============================CREATE OBJECT BEFORE CALL THE SERVICE ==================================
	@GET
	@Path("/FindById")
	@Produces(MediaType.APPLICATION_JSON)
	public Flight FindByIdFlight(@QueryParam("id") long id)
			throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = flightService.findById(id);
		return flightService.find(flight);
	}
	
	@GET
	@Path("/FindByAirline")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByAirlineFlight(@QueryParam("id") long id) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airline airline = airlineService.findById(id);
		return flightService.findByAirline(airline);
	}
	
	@GET
	@Path("/FindByOrigin")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByOriginFlight(@QueryParam("id") long id) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airport airport = airportService.findById(id);
		return flightService.findByOrigin(airport);
	}
	
	@GET
	@Path("/FindByDestination")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByDestinationFlight(@QueryParam("id") long id) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airport airport = airportService.findById(id);
		return flightService.findByDestination(airport);
	}
	
	
	@GET
	@Path("/FindByArrivalPlane")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Flight> findByArrivalFlight(@QueryParam("id") long id) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airplane airplane = airplaneService.findById(id);
		return flightService.findByArrival(airplane);
	}
	
}
