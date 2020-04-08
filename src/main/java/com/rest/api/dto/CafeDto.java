package com.rest.api.dto;

import java.io.Serializable;

import com.rest.api.entity.cafe.Cafe;

public class CafeDto implements Serializable, ToEntity<Cafe>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3437669041353711046L;

	@Override
	public Cafe toEntity() {
		return null;
	}

}
