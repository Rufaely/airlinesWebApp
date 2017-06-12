package cs545.airline.view;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;

import cs545.airline.model.Airline;
import cs545.airline.model.Airplane;
import cs545.airline.model.Airport;
import cs545.airline.model.Flight;
import cs545.airline.service.AirlineService;
import cs545.airline.service.AirplaneService;
import cs545.airline.service.AirportService;
import cs545.airline.service.FlightService;

@ManagedBean
@ViewScoped
public class ButtonView implements Serializable {

	@Inject
	private AirlineService airlineService;
	@Inject
	private AirplaneService airplaneService;
	@Inject
	private AirportService airportService;
	@Inject
	private FlightService flightService;


	// ======================== AIRLINE=====================
	public void saveAirline(String name) {
		Airline temp = new Airline(name);
		System.out.println("OBJECTO A GUARDAR " + temp);
		airlineService.create(temp);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airline Saved", temp.getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void updateAirline(RowEditEvent actionEvent) {
		airlineService.update((Airline) actionEvent.getObject());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airline updated",
				((Airline) actionEvent.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteAirline(RowEditEvent actionEvent) {
		airlineService.delete((Airline) actionEvent.getObject());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airline deleted",
				((Airline) actionEvent.getObject()).getName());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	// ======================== AIRPLANE=====================

	public void savePlane(String serialnr, String model, int capacity) {
		Airplane temp = new Airplane(serialnr, model, capacity);
		System.out.println("OBJECTO A GUARDAR " + temp);
		airplaneService.create(temp);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airplane Saved", temp.getSerialnr());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void updatePlane(RowEditEvent actionEvent) {
		airplaneService.update((Airplane) actionEvent.getObject());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airplane updated",
				((Airplane) actionEvent.getObject()).getSerialnr());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deletePlane(RowEditEvent actionEvent) {
		airplaneService.delete((Airplane) actionEvent.getObject());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airplane deleted",
				((Airplane) actionEvent.getObject()).getSerialnr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	// ======================== AIRPORT=====================

	public void saveAirport(String airportcode, String name, String city, String country) {
		Airport temp = new Airport(airportcode, name, city, country);
		System.out.println("OBJECTO A GUARDAR " + temp);
		airportService.create(temp);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airport Saved", temp.getAirportcode());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void updateAirport(RowEditEvent actionEvent) {
		airportService.update((Airport) actionEvent.getObject());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airport updated",
				((Airport) actionEvent.getObject()).getAirportcode());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteAirport(RowEditEvent actionEvent) {
		airportService.delete((Airport) actionEvent.getObject());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Airport deleted",
				((Airport) actionEvent.getObject()).getAirportcode());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}

	// ======================== FLIGHTS=====================

	public void saveFlight(String flightnr, Date departureDate, Date departureTime, Date arrivalDate,
			Date arrivalTime, String selectedAirline, String selectedAirplane, String selectedAirportOrigin,
			String selectedAirportDestination) {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat formattime = new SimpleDateFormat("h:mm a");
		
		
		System.out.println("----------Selected Airline:  " +selectedAirline );
		long idAirline= Long.valueOf(selectedAirline.substring(selectedAirline.indexOf("id=")+3,selectedAirline.indexOf(",")));
		Airline tempAirline= airlineService.findById(idAirline);
		
		System.out.println("----------Selected selectedAirplane:  " +selectedAirplane );
		long idAirplane= Long.valueOf(selectedAirplane.substring(selectedAirplane.indexOf("id=")+3,selectedAirplane.indexOf(",")));
		Airplane tempAirplane= airplaneService.findById(idAirplane);
		
		System.out.println("----------Selected selectedAirportOrigin:  " +selectedAirportOrigin );
		long idAirportorigin= Long.valueOf(selectedAirportOrigin.substring(selectedAirportOrigin.indexOf("id=")+3,selectedAirportOrigin.indexOf(",")));
		Airport tempAirportorigin=airportService.findById(idAirportorigin);
		
		System.out.println("----------Selected selectedAirportDestination:  " +selectedAirportDestination );
		long idAirportdestination= Long.valueOf(selectedAirportDestination.substring(selectedAirportDestination.indexOf("id=")+3,selectedAirportDestination.indexOf(",")));
		Airport tempAirportdestination=airportService.findById(idAirportdestination);
		
		
		Flight temp = new Flight(flightnr, format.format(departureDate), formattime.format(departureTime),
				format.format(arrivalDate), formattime.format(arrivalTime),
				tempAirline,tempAirportorigin,tempAirportdestination,tempAirplane);
		
		System.out.println("OBJECTO A GUARDAR " + temp);
		flightService.create(temp);
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Flight Saved", temp.getFlightnr());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void updateFlight(RowEditEvent actionEvent) {

		flightService.update((Flight) actionEvent.getObject());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Flight updated",
				((Flight) actionEvent.getObject()).getFlightnr());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void deleteFlight(RowEditEvent actionEvent) {
		flightService.delete((Flight) actionEvent.getObject());
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Flight deleted",
				((Flight) actionEvent.getObject()).getFlightnr());
		FacesContext.getCurrentInstance().addMessage(null, msg);

	}


	
	

}