package com.krunchykreme.server;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private static List<Doughnut> menu;
	
	private static void initializeMenu()
	{
		menu = new ArrayList<Doughnut>();
		menu.add(new Doughnut(1, "The Caramel One"));
		menu.add(new Doughnut(2, "The Chocolate Custard One"));
		menu.add(new Doughnut(3, "The Cookie One"));
		menu.add(new Doughnut(4, "The Straberries Jam One"));
	}
	
	public static List<Doughnut> getMenu()
	{
		if(menu == null) {
			initializeMenu();
		}
		return menu;
	}

	public static boolean isValidOrder(int doughnutId, int quantity)
	{
		if(quantity <= 0) {
			return false;
		}
		
		for(Doughnut d : getMenu()) {
			if(d.getId() == doughnutId) {
				return true;
			}
		}
		
		return false;
	}
	
}
