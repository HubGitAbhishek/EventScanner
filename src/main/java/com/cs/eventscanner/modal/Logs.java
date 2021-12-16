package com.cs.eventscanner.modal;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Logs {

	@NotNull
    private String id;
	
	@NotNull
    private String state;
    
    private String host;

    private  String type;
    
    @NotNull
    private long timestamp;
    
	
}
