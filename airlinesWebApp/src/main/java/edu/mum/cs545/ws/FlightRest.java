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
import cs545.airline.service.FlightService;

@Named
@Path("Flight")
public class FlightRest {

	@Inject
	private FlightService flightService;
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@POST
	@Path("/Create")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String CreateFlight(Flight flight, @Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS CREATE JSON : " + flight);
		flightService.create(flight);
		return SUCCESS_RESULT;
	}
	
	
	@DELETE
	@Path("/Delete/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String DeleteFlight(@PathParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		Flight flight = flightService.findById(id);
		System.out.println("ENVIO DE DATOS DESDE WS DELETE: " + flight);
		flightService.delete(flight);
		return SUCCESS_RESULT;
	}

	
	@POST
	@Path("/Update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Flight UpdateFlight(	Flight flight, @Context HttpServletResponse servletResponse) throws IOException {
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> FindByNumberFlight(@QueryParam("Flightnr") String flightnr,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(flightnr)) {
			return null;
		}
		return flightService.findByNumber(flightnr);
	}

	@GET
	@Path("/FindByArrivalDate")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> findByArrivalFlight(@QueryParam("datetime") String datetime,
			@Context HttpServletResponse servletResponse) throws IOException {
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> FindByArrivalBetweenFlight(@QueryParam("datetimeFrom") String datetimeFrom,
			@QueryParam("datetimeTo") String datetimeTo, @Context HttpServletResponse servletResponse)
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> findByDepartureFlight(@QueryParam("datetime") String datetime,
			@Context HttpServletResponse servletResponse) throws IOException {
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> findByDepartureBetweenFlight(@QueryParam("datetimeFrom") String datetimeFrom,
			@QueryParam("datetimeTo") String datetimeTo, @Context HttpServletResponse servletResponse)
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
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Flight FindByIdFlight(@QueryParam("id") long id, 
			@Context HttpServletResponse servletResponse)
			throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return flightService.find(flight);
	}
	
	@GET
	@Path("/FindByAirline")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> findByAirlineFlight(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airline airline = new Airline();
		airline.setId(id);
		return flightService.findByAirline(airline);
	}
	
	@GET
	@Path("/FindByOrigin")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> findByOriginFlight(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airport airport = new Airport();
		airport.setId(id);
		return flightService.findByOrigin(airport);
	}
	
	@GET
	@Path("/FindByDestination")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> findByDestinationFlight(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airport airport = new Airport();
		airport.setId(id);
		return flightService.findByDestination(airport);
	}
	
	
	@GET
	@Path("/FindByArrivalPlane")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Flight> findByArrivalFlight(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airplane airplane = new Airplane();
		airplane.setId(id);
		return flightService.findByArrival(airplane);
	}
	
}
