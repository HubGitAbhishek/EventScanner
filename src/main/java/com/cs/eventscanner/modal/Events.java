package com.cs.eventscanner.modal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
@Entity
@Table(name="EVENTS")
public class Events {
	
	  @Id
	  private  String id;
	  @NotNull
	  private  long duration;
	  private  String type;
	  private  String host;
	  @NotNull
	  private  boolean alert;
	
	
	  public static class Builder {
	        private String id;
	        private long duration;
	        private boolean alert;
	        private String type;
	        private String host;

	        public Builder(String id) {
	            this.id = id;
	        }
	        
	        public Builder withDuration(long duration) {
	            this.duration = duration;
	            return this;
	        }
	        
	        public Builder withAlert(boolean alert) {
	            this.alert = alert;
	            return this;
	        }

	        public Builder withType(String type) {
	            this.type = type;
	            return this;
	        }

	        public Builder withHost(String host) {
	            this.host = host;
	            return this;
	        }

	        public Events build() {
	            Events event = new Events();
	            event.alert = this.alert;
	            event.duration = this.duration;
	            event.type = this.type;
	            event.host = this.host;
	            event.id = this.id;
	            return event;
	        }
	    }

}
