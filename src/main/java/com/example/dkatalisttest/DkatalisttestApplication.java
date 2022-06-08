package com.example.dkatalisttest;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import com.example.dkatalisttest.methodFolder.mainMethod;
import com.example.dkatalisttest.interfaceFolder.peopleAction;
import com.example.dkatalisttest.model.People;
//import com.example.dkatalisttest.model.People.peopleData;

@SpringBootApplication

public class DkatalisttestApplication
		implements
		CommandLineRunner,
		peopleAction {
	public static void main(String[] args) {
		SpringApplication.run(DkatalisttestApplication.class, args);
	}

	public static String loginName = ""; // user yang sedang login

	@Override
	public void run(String... args) {
		People peopleInfo = new People();

		String userInput = ""; // inputan user
		String[] splitUserInput; // untuk mengetahui action dari inputan user
		String command = ""; // perintah yang akan dijalankan
		String userName = ""; // username untuk login
		String targetName = ""; // loginid target untuk ditransfer
		Integer amount = 0; // angka yang dimasukan untuk suatu action

		Scanner scanUserInput = new Scanner(System.in);

		while (userInput != "exit") {
			userInput = scanUserInput.nextLine();
			splitUserInput = userInput.split(" ");
			command = splitUserInput[0].trim();

			if (command.trim().equals("login")) {
				if (loginName != "") {
					System.out.println("There is user still login...!!!");
					continue;
				}

				if (splitUserInput.length < 2) {
					System.out.println("Format Error...!!!");
					continue;
				}

				userName = splitUserInput[1].toLowerCase();

				if (userName == "") {
					System.out.println("Need username for login...!!!");
					continue;
				}

				loginName = userName;

				peopleAction.login(userName, peopleInfo);
				continue;
			} else if (command.trim().equals("deposit")) {
				if (loginName.equals("")) {
					System.out.println("You must login first...!!!");
					continue;
				}

				if (splitUserInput.length < 2) {
					System.out.println("Format Error...!!!");
					continue;
				}

				if (splitUserInput[1].trim().equals("")) {
					System.out.println("Format Error...(No Amount)!!!");
					continue;
				}
				amount = Integer.parseInt(splitUserInput[1].trim());
				peopleAction.deposit(loginName, amount, peopleInfo);
				continue;
			} else if (command.trim().equals("withdraw")) {
				if (loginName.equals("")) {
					System.out.println("You must login first...!!!");
					continue;
				}

				if (splitUserInput.length < 2) {
					System.out.println("Format Error...!!!");
					continue;
				}

				if (splitUserInput[1].trim().equals("")) {
					System.out.println("Format Error...(No Amount)!!!");
					continue;
				}

				amount = Integer.parseInt(splitUserInput[1].trim());

				peopleAction.withdraw(loginName, amount, peopleInfo);
				continue;
			} else if (command.trim().equals("transfer")) {
				if (loginName.equals("")) {
					System.out.println("You must login first...!!!");
					continue;
				}

				if (splitUserInput.length < 3) {
					System.out.println("Format Error...!!!");
					continue;
				}

				if (splitUserInput[1].trim().isEmpty()) {
					System.out.println("Format Error...(No Target to Transfer)!!!");
					continue;
				}

				if (splitUserInput[2].trim().isEmpty()) {
					System.out.println("Format Error...(No Amount)!!!");
					continue;
				}

				targetName = splitUserInput[1].trim();
				amount = Integer.parseInt(splitUserInput[2].trim());

				peopleAction.transfer(loginName, targetName, amount, peopleInfo);
				continue;
			} else if (command.trim().equals("logout")) {
				if (loginName.equals("")) {
					System.out.println("You must login first...!!!");
					continue;
				}

				peopleAction.logout(loginName);
				loginName = "";
				continue;
			} else if (command.trim().equals("exit")) {
				System.out.println("System Exit");
				System.exit(0);
			} else if (command.trim().equals("check")) {
				System.out.println(peopleInfo.getPeopleHMap());
				System.out.println(peopleInfo.getPeopleHMap().get(loginName).getBalance());
				System.out.println(peopleInfo.getPeopleHMap().get(loginName).getOwe());
				System.out.println(peopleInfo.getPeopleHMap().get(loginName).getOweName());
				continue;
			} else {
				System.out.println("Format Error...!!!");
				continue;
			}
		}

		scanUserInput.close();
	}
}
