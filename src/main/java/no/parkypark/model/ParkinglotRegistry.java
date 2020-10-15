package no.parkypark.model;

import java.util.ArrayList;

public class ParkinglotRegistry {
	String name;
	ArrayList<Parkinglot> listOfParkinglots = new ArrayList<>();


	ParkinglotRegistry(){}


	public void setName(String name) {
		this.name = name;
	}

	public void setListOfParkinglots(ArrayList<Parkinglot> listOfParkinglots) {
		this.listOfParkinglots = listOfParkinglots;
	}

	public void addLot(Parkinglot lot) {
		this.listOfParkinglots.add(lot);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Parkinglot> getListOfParkinglots() {
		return listOfParkinglots;
	}

	public Parkinglot getParkinglotById(String parkinglotId) {
		for (Parkinglot i: listOfParkinglots) {
			if(i.getId().equals(parkinglotId)) {
				return i;
			}
		}
		return new Parkinglot("Lol", "Suckers", "4321");
	}
}
