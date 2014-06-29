package com.krunchykreme.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KrunchyKremeHandler implements KrunchyKreme.Iface {
	final static Logger logger = LoggerFactory.getLogger(Server.class);
	 
	public boolean order(int doughtnutId, short quantity) throws TException {
		logger.debug("Received an order for " + quantity + " X doughnut id "
				+ doughtnutId);
		return Menu.isValidOrder(doughtnutId, quantity);
	}

	public List<Doughnut> getMenu() throws TException {
		logger.debug("Menu requested");
		
		return Menu.getMenu();
	}
}
