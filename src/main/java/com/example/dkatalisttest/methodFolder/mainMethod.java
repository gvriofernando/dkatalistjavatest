package com.example.dkatalisttest.methodFolder;

import java.util.HashMap;

import com.example.dkatalisttest.model.People;
import com.example.dkatalisttest.model.People.peopleData;

public class mainMethod {
    public void login(String loginName, String userName, People peopleInfo) {

        loginName = userName;

        Integer currBalance = 0;

        if (peopleInfo.getPeopleHMap().get(userName) == null) {
            People.peopleData peopleInfoData = new People.peopleData();
            peopleInfoData.setBalance(0);
            peopleInfoData.setOwe(0);
            peopleInfoData.setOweName("");
            HashMap<String, peopleData> peopleBalance = new HashMap<String, peopleData>();
            peopleBalance.put(userName, peopleInfoData);
            peopleInfo.setPeopleHMap(peopleBalance);
        }

        currBalance = (peopleInfo.getPeopleHMap().get(loginName)).getBalance();

        System.out.println("Hello, " + userName + "!");
        System.out.println("Your balance is $" + currBalance);

        HashMap<String, peopleData> peopleHMap = new HashMap<String, peopleData>();
        peopleHMap = peopleInfo.getPeopleHMap();
        peopleHMap.forEach((key, value) -> {
            String currentOweName = "";
            currentOweName = (peopleInfo.getPeopleHMap().get(userName)).getOweName();
            if (currentOweName.equals(userName)) {
                Integer currentOwe = 0;
                currentOwe = (peopleInfo.getPeopleHMap().get(userName)).getOwe();
                System.out.println("Owed $" + currentOwe + " from " + key);
            }
        });
    }

    public void logout(String loginName) {
        System.out.println("Goodbye, " + loginName + "!");
        loginName = "";
    }

    public void deposit(String loginName, int amount) {
        System.out.println("Deposit $" + amount);
    }

    public void withdraw(String loginName, int amount) {
        System.out.println("Withdraw $" + amount);
    }

    public void transfer(String loginName, String targetName, int amount) {
        System.out.println("Transfer from " + loginName + " to " + targetName + " $" + amount);
    }
}
