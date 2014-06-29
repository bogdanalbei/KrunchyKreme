package com.krunchykreme.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;

public class KrunchyKremeHandler implements KrunchyKreme.Iface {
	public boolean order(int doughtnutId, short quantity) throws TException {
		System.out.println("Received an order for "+quantity+" X doughnut"+doughtnutId);
		return true;
	}

	public List<Doughnut> getMenu() throws TException {
		List<Doughnut> result = new ArrayList<Doughnut>();
		result.add(new Doughnut(1, "The Caramel One"));
		result.add(new Doughnut(2, "The Chocolate Custard One"));
		result.add(new Doughnut(3, "The Cookie One"));
		result.add(new Doughnut(4, "The Straberries Jam One"));
		
		return result;
	}
}
