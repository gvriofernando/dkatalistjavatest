package com.example.dkatalisttest.model;

import java.util.HashMap;

public class People {
	HashMap<String, peopleData> peopleHMap = new HashMap<String, peopleData>();

	public HashMap<String, peopleData> getPeopleHMap() {
		return peopleHMap;
	}

	public void setPeopleHMap(HashMap<String, peopleData> peopleHMap) {
		this.peopleHMap = peopleHMap;
	}

	public static class peopleData {
		private int balance = 0;
		private int owe = 0;
		private String oweName = "";

		public int getBalance() {
			return balance;
		}

		public void setBalance(int balance) {
			this.balance = balance;
		}

		public int getOwe() {
			return owe;
		}

		public void setOwe(int owe) {
			this.owe = owe;
		}

		public String getOweName() {
			return oweName;
		}

		public void setOweName(String oweName) {
			this.oweName = oweName;
		}
	}
}
