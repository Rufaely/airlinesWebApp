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

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;

import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@Named
@Path("Airport")
public class AirportRest {

	@Inject
	private AirportService airportService;
	@Inject
	private FlightService flightService;
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@POST
	@Path("/Create")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String CreateAirport(Airport airport) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS CREATE JSON : " + airport);
		airportService.create(airport);
		return SUCCESS_RESULT;
	}

	@DELETE
	@Path("/Delete/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String DeleteAirport(@PathParam("id") long id) throws IOException {
		Airport airport = airportService.findById(id);
		System.out.println("ENVIO DE DATOS DESDE WS DELETE: " + airport);
		airportService.delete(airport);
		return SUCCESS_RESULT;
	}

	@POST
	@Path("/Update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Airport UpdateAirport2(Airport airport) throws IOException {

		Airport airport2 = airportService.find(airport);
		airport2.setCity(airport.getCity());
		airport2.setCountry(airport.getCountry());
		airport2.setName(airport.getName());
		System.out.println("ENVIO DE DATOS DESDE WS UPDATE JSON: " + airport2);
		return airportService.update(airport2);
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
	@Produces(MediaType.APPLICATION_JSON)
	public Airport FindBySrlnrAirport2(@QueryParam("airportcode") String airportcode) throws IOException {
		if ("".equals(airportcode)) {
			return null;
		}
		Airport airportfind = airportService.findByCode(airportcode);
		return airportfind;
	}

	@GET
	@Path("/FindByCity")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> FindByCityAirport2(@QueryParam("city") String city) throws IOException {
		if ("".equals(city)) {
			return null;
		}
		return airportService.findByCity(city);
	}

	@GET
	@Path("/FindByCountry")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> FindByCountryAirport2(@QueryParam("country") String country) throws IOException {
		if ("".equals(country)) {
			return null;
		}
		return airportService.findByCountry(country);
	}

	@GET
	@Path("/FindByName")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> FindByNameAirport2(@QueryParam("name") String name) throws IOException {
		if ("".equals(name)) {
			return null;
		}
		return airportService.findByName(name);
	}

	// =============================CREATE OBJECT BEFORE CALL THE SERVICE
	// ==================================

	@GET
	@Path("/FindById")
	@Produces(MediaType.APPLICATION_JSON)
	public Airport FindByIdAirport(@QueryParam("id") long id) throws IOException {
		Airport airport = airportService.findById(id);
		Airport airportfind = airportService.find(airport);
		return airportfind;
	}

	@GET
	@Path("/FindByArrival")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> FindByArrivalAirport2(@QueryParam("id") long id) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = flightService.findById(id);
		return airportService.findByArrival(flight);
	}

	@GET
	@Path("/FindByDeparture")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airport> FindByDepartureAirport2(@QueryParam("id") long id) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = flightService.findById(id);
		return airportService.findByDeparture(flight);
	}

}
