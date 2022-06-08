package com.example.dkatalisttest.interfaceFolder;

import java.util.HashMap;

import com.example.dkatalisttest.model.People;
import com.example.dkatalisttest.model.People.peopleData;

public interface peopleAction {
    public static void login(String userName, People peopleInfo) {
        Integer currBalance = 0;

        HashMap<String, peopleData> peopleHMap = new HashMap<String, peopleData>();
        peopleHMap = peopleInfo.getPeopleHMap();

        if (peopleHMap.get(userName) == null) {
            People.peopleData peopleInfoData = new People.peopleData();
            peopleInfoData.setBalance(0);
            peopleInfoData.setOwe(0);
            peopleInfoData.setOweName("");
            peopleHMap.put(userName, peopleInfoData);
            peopleInfo.setPeopleHMap(peopleHMap);
        }

        currBalance = (peopleInfo.getPeopleHMap().get(userName)).getBalance();

        System.out.println("Hello, " + userName + "!");
        System.out.println("Your balance is $" + currBalance);

        peopleHMap = peopleInfo.getPeopleHMap();
        peopleHMap.forEach((key, value) -> {
            String currentOweName = "";
            currentOweName = (peopleInfo.getPeopleHMap().get(key)).getOweName();
            if (currentOweName.equals(userName)) {
                Integer currentOwe = 0;
                currentOwe = (peopleInfo.getPeopleHMap().get(key)).getOwe();
                System.out.println("Owed $" + currentOwe + " from " + key);
            }
        });
    }

    public static void logout(String loginName) {
        System.out.println("Goodbye, " + loginName + "!");
    }

    public static void deposit(String loginName, int amount, People peopleInfo) {
        Integer currBalance = 0;
        Integer currOwe = 0;
        String oweName = "";
        Integer owedBalance = 0; // untuk mengetahui balance dari orang yang dihutangi

        currBalance = (peopleInfo.getPeopleHMap().get(loginName)).getBalance();
        currOwe = (peopleInfo.getPeopleHMap().get(loginName)).getOwe();
        oweName = (peopleInfo.getPeopleHMap().get(loginName)).getOweName();

        if (oweName != "") {
            owedBalance = (peopleInfo.getPeopleHMap().get(oweName)).getBalance();
        }

        if (amount < currOwe) {
            currOwe -= amount;
            (peopleInfo.getPeopleHMap().get(loginName)).setOwe(currOwe);
            (peopleInfo.getPeopleHMap().get(oweName)).setBalance((owedBalance + amount));
            System.out.println("Transferred $" + amount + " to " + oweName);
            System.out.println("Owed $" + currOwe + " to " + oweName);
        } else {
            amount -= currOwe;
            currBalance += amount;
            (peopleInfo.getPeopleHMap().get(loginName)).setOwe(0);
            (peopleInfo.getPeopleHMap().get(loginName)).setOweName("");
            (peopleInfo.getPeopleHMap().get(loginName)).setBalance(currBalance);

            if (oweName != "") {
                (peopleInfo.getPeopleHMap().get(oweName)).setBalance((owedBalance + currOwe));
            }
        }

        System.out.println("Your Balance is $" + currBalance);
    }

    public static void withdraw(String loginName, int amount, People peopleInfo) {
        Integer currBalance = 0;
        currBalance = (peopleInfo.getPeopleHMap().get(loginName)).getBalance();

        if (amount <= currBalance) {
            System.out.println("Withdraw $" + amount + " Success.");
            currBalance -= amount;
        } else {
            System.out.println("Withdraw $" + currBalance + " Success.");
            currBalance = 0;
        }
        (peopleInfo.getPeopleHMap().get(loginName)).setBalance(currBalance);
        System.out.println("Your Balance is $" + currBalance);
    }

    public static void transfer(String loginName, String targetName, int amount, People peopleInfo) {
        if (peopleInfo.getPeopleHMap().get(targetName) == null) {
            System.out.println("Target Not Found...!!!");
            return;
        }

        if (targetName.equals(loginName)) {
            System.out.println("Target tidak boleh diri sendiri...!!!");
            return;
        }

        Integer currBalance = 0;
        Integer currOwe = 0;
        String oweName = "";
        Integer targetBalance = 0;

        currBalance = (peopleInfo.getPeopleHMap().get(loginName)).getBalance();
        currOwe = (peopleInfo.getPeopleHMap().get(loginName)).getOwe();
        oweName = (peopleInfo.getPeopleHMap().get(loginName)).getOweName();

        if (targetName != oweName && oweName != "") {
            System.out.println("Masih ada hutang dengan " + oweName + " sebesar $" + currOwe);
            return;
        }

        targetBalance = (peopleInfo.getPeopleHMap().get(targetName)).getBalance();

        if (currBalance >= amount) {
            (peopleInfo.getPeopleHMap().get(loginName)).setBalance((currBalance - amount));
            (peopleInfo.getPeopleHMap().get(targetName)).setBalance((targetBalance + amount));
            System.out.println("Transferred $" + amount + " to " + targetName + "Success");
            return;
        } else {
            (peopleInfo.getPeopleHMap().get(loginName)).setBalance(0);
            (peopleInfo.getPeopleHMap().get(targetName)).setBalance((targetBalance + currBalance));
            (peopleInfo.getPeopleHMap().get(loginName)).setOwe((currOwe + amount - currBalance));
            (peopleInfo.getPeopleHMap().get(loginName)).setOweName(targetName);

            System.out.println("Transferred $" + currBalance + " to " + targetName + " Success");
            System.out.println("Owed $" + (amount - currBalance) + " to " + targetName);
        }
    }
}