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
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.FlightService;

@Named
@Path("Airplane")
public class AirplaneRest {

	@Inject
	private AirplaneService airplaneService;
	@Inject
	private FlightService flightService;
	private static final String SUCCESS_RESULT = "<result>success</result>";
	private static final String FAILURE_RESULT = "<result>failure</result>";

	@POST
	@Path("/Create")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String CreateAirplane(Airplane airplane) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS CREATE JSON : " + airplane);
		airplaneService.create(airplane);
		return SUCCESS_RESULT;
	}

	@DELETE
	@Path("/Delete/{id}")
	@Produces(MediaType.APPLICATION_XML)
	public String DeleteAirplane(@PathParam("id") long id) throws IOException {
		Airplane airplane = airplaneService.findById(id);
		System.out.println("ENVIO DE DATOS DESDE WS DELETE: " + airplane);
		airplaneService.delete(airplane);
		return SUCCESS_RESULT;
	}

	@POST
	@Path("/Update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Airplane UpdateAirplane(Airplane airplane) throws IOException {
		System.out.println("ENVIO DE DATOS DESDE WS UPDATE: " + airplane);
		Airplane airplaneup = airplaneService.update(airplane);
		return airplaneup;
	}

	// =================================== GET WITHOUT
	// PARAMETERS===========================================================
	@Path("/List")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airplane> getAirplanes() {
		return airplaneService.findAll();
	}

	// =================================== GET WITH
	// QUERYPARAMETER
	// INPUT===========================================================

	@GET
	@Path("/FindBySrlnr")
	@Produces(MediaType.APPLICATION_JSON)
	public Airplane FindBySrlnrAirplane(@QueryParam("serialnr") String serialnr) throws IOException {
		if ("".equals(serialnr)) {
			return null;
		}
		Airplane airlinefind = airplaneService.findBySrlnr(serialnr);
		return airlinefind;
	}

	// =============================CREATE OBJECT BEFORE CALL THE SERVICE
	// ==================================

	@GET
	@Path("/FindById")
	@Produces(MediaType.APPLICATION_JSON)
	public Airplane FindByIdAirplane(@QueryParam("id") long id) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Airplane airplane = airplaneService.findById(id);
		Airplane airlinefind = airplaneService.find(airplane);
		return airlinefind;
	}

	@GET
	@Path("/FindByFlight")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airplane> findByFlightAirline(@QueryParam("id") long id) throws IOException {
		if ("".equals(id)) {
			return null;
		}
		Flight flight = flightService.findById(id);
		return airplaneService.findByFlight(flight);
	}

	@GET
	@Path("/FindByModel")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Airplane> FindByModelAirplane(@QueryParam("model") String model) throws IOException {
		if ("".equals(model)) {
			return null;
		}
		Airplane airplane = new Airplane();
		airplane.setModel(model);
		return airplaneService.findByModel(model);
	}

}
