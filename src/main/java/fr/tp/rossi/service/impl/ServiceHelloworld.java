package fr.tp.rossi.service.impl;

import org.springframework.stereotype.Service;

import fr.tp.rossi.service.IServiceHelloworld;

@Service
public class ServiceHelloworld implements IServiceHelloworld {

	public String getHtml() {
		return "ServiceHelloworld::getHtml()";
	}

}
