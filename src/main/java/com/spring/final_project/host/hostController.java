package com.spring.final_project.host;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/host")
public class hostController {

	@GetMapping("/regist")
	public String regist() {
		return "host/regist";
	}

	@PostMapping("/registproc")
	public String registproc() {
		return "/";
	}

	@GetMapping("/center")
	public String center() {
		return "host/managecenter";
	}

	@GetMapping("/info/{hostNum}")
	public String hostInfo(@PathVariable("hostNum") int hostNum) {

		return "host/hostinfo";
	}

}
