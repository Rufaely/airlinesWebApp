package com.corejsf;

import java.io.Serializable;
import javax.inject.Named; 
import javax.enterprise.context.RequestScoped; 

@Named 
@RequestScoped
public class AirlineMenu implements Serializable {
  private String selectedOption;
  
  public String getSelectedOption() { return selectedOption; }
  
  public String changeOption(String newValue) {
	  selectedOption = newValue;
     return selectedOption;
  }
}
