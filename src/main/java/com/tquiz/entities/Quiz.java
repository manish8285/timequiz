package com.tquiz.entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Quiz {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String question;
	private List<String> options = new ArrayList<String>();
	private int rightAnswer;
	private Date startDate;
	private Date endDate;
	
	// derived property
	// this property will not be stored in database
	@Transient
	private String status;
	
	//each time when the object will be fetched from database
	//this getter method will provide status on the basis start and end date
	public String getStatus() {

		Date currentDate = new Date();
		if(new Date().before(startDate)) {
			
			this.status="inactive";
		}
		else if(currentDate.after(this.startDate) && currentDate.before(this.endDate)) {
			this.status="active";
		}
		else if(currentDate.after(this.endDate)){
			this.status="finished";
		}
	
		return this.status;
	}

}
