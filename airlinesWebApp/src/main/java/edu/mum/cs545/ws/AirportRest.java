package edu.mum.cs545.ws;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

import cs545.airline.service.AirportService;

@Named
@Path("Airport")
public class AirportRest {

	@Inject
	private AirportService airportService;
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@POST
	@Path("/Create")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String CreateAirport(Airport airport, @Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS CREATE JSON : " + airport);
		airportService.create(airport);
		return SUCCESS_RESULT;
	}
	
	
	@DELETE
	@Path("/Delete/{id}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String DeleteAirport(@PathParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		Airport airport = airportService.findById(id);
		System.out.println("ENVIO DE DATOS DESDE WS DELETE: " + airport);
		airportService.delete(airport);
		return SUCCESS_RESULT;
	}


	@POST
	@Path("/Update")
	@Consumes(MediaType.APPLICATION_JSON)
	public Airport UpdateAirport2(Airport airport, @Context HttpServletResponse servletResponse) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS UPDATE JSON: " + airport);
		Airport airportup = airportService.update(airport);
		return airportup;
	}

	// =================================== GET WITHOUT
	// PARAMETERS===========================================================

	@GET
	@Path("/List")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> getAirports() {
		return airportService.findAll();
	}

	// =================================== GET WITH
	// QUERYPARAMETER
	// INPUT===========================================================

	@GET
	@Path("/FindByCode")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Airport FindBySrlnrAirport2(@QueryParam("airportcode") String airportcode,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(airportcode)) {
			return null;
		}
		Airport airportfind = airportService.findByCode(airportcode);
		return airportfind;
	}

	@GET
	@Path("/FindByCity")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Airport> FindByCityAirport2(@QueryParam("city") String city,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(city)) {
			return null;
		}
		return airportService.findByCity(city);
	}

	@GET
	@Path("/FindByCountry")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Airport> FindByCountryAirport2(@QueryParam("country") String country,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(country)) {
			return null;
		}
		return airportService.findByCountry(country);
	}

	@GET
	@Path("/FindByName")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Airport> FindByNameAirport2(@QueryParam("name") String name,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(name)) {
			return null;
		}
		return airportService.findByName(name);
	}


	
	//=============================CREATE OBJECT BEFORE CALL THE SERVICE ==================================
	
	@GET
	@Path("/FindById")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Airport FindByIdAirport(@QueryParam("id") long id, @Context HttpServletResponse servletResponse)
			throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airport airport = new Airport();
		airport.setId(id);
		Airport airportfind = airportService.find(airport);
		return airportfind;
	}
	
	@GET
	@Path("/FindByArrival")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Airport> FindByArrivalAirport2(@QueryParam("id") long id, @Context HttpServletResponse servletResponse)
			throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return airportService.findByArrival(flight);
	}
	
	@GET
	@Path("/FindByDeparture")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Airport> FindByDepartureAirport2(@QueryParam("id") long id,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = new Flight();
		flight.setId(id);
		return airportService.findByDeparture(flight);
	}

	// =================================== GET WITH PATH
	// PARAMETER===========================================================

	@GET
	@Path("/FindByName/{name}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public List<Airport> FindByNameAirport3(@PathParam("name") String name,
			@Context HttpServletResponse servletResponse) throws IOException {
		if ("".equals(name)) {
			return null;
		}
		return airportService.findByName(name);
	}

}
