package com.rest.api.util;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * 
 * @author lsh
 *
 */
public class DbCrawler {

	
	@Scheduled()
	private void dbShortTermUpdate() {
		
	}
	
	@Scheduled
	private void dbLongTermUpdate() {
		
	}
}
