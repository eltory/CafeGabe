package com.rest.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;

//@RestController
@Controller
public class TestController {
	
	@GetMapping (value = "/hello/json")
	@ResponseBody
	public Hello hell() {
		Hello hello = new Hello();
		hello.msg = "hihi";
		return hello;
	}
	
	@GetMapping (value = "/helloworld/page")
	public String helloworld() {
		return "helloworld";
	}
	
	@Getter
	@Setter
	public static class Hello{
		private String msg;
	}
	
}
