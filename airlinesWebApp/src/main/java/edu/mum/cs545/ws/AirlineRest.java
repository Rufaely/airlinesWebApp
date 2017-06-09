package edu.mum.cs545.ws;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airline;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;

@Named
@Path("Airline")
public class AirlineRest {

	@Inject
	private AirlineService airlineService;
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	
	@POST
	@Path("/Create")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String CreateAirline(Airline airline, @Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS CREATE: " + airline);
		airlineService.create(airline);
		return SUCCESS_RESULT;
	}
	

	@DELETE
	@Path("/Delete/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String DeleteAirline(@PathParam("id") long id, @Context HttpServletResponse servletResponse) throws IOException {
		Airline airline = airlineService.findById(id);
		System.out.println("ENVIO DE DATOS DESDE WS DELETE: " + airline);
		airlineService.delete(airline);
		return SUCCESS_RESULT;
	}

	
	@POST
	@Path("/Update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Airline UpdateAirline(Airline airline, @Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS UPDATE JSON: " + airline);
		Airline airlineup = airlineService.update(airline);
		return airlineup;
	}
	
	// =================================== GET WITHOUT
	// PARAMETERS===========================================================
	@Path("/List")
	@GET
	public List<Airline> getAirlines() {
		return airlineService.findAll();
	}
	
	// =================================== GET WITH
	// QUERYPARAMETER
	// INPUT===========================================================

	
	
	@GET
	@Path("/FindByName")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Airline FindByNameAirline(@QueryParam("name") String name,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(name)) {
			return null;
		}
		Airline airlinefind = airlineService.findByName(name);
		return airlinefind;
	}
	

	//=============================CREATE OBJECT BEFORE CALL THE SERVICE ==================================
	
	
	@GET
	@Path("/FindById")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Airline FindByIdAirline(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airline airline = new Airline();
		airline.setId(id);
		Airline airlinefind = airlineService.find(airline);
		return airlinefind;
	}
	
	
	@GET
	@Path("/FindByFlight")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Airline> findByFlightAirline(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return airlineService.findByFlight(flight);
	}
	




}
