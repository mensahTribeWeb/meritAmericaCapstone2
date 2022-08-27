package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);
    private final NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    public void printUsers(User[] listUsers, User currentUser) {
        for (User listUser : listUsers) {
            if(listUser.equals(currentUser)) {
                continue;
            }
            System.out.printf("%-12d%s\n", listUser.getId(), listUser.getUsername());
        }
        System.out.println("-------------------------------------------");
    }

    public void printAccountBalance(Account account) {
        System.out.printf("Your current account balance is: %s\n", getFormattedAmount(account.getBalance()));
    }

    public void printSendFundsHeader() {
        System.out.println("-------------------------------------------");
        System.out.println("Users");
        System.out.println("ID          Name");
        System.out.println("-------------------------------------------");
    }

    public void printViewTransferHeader() {
        System.out.println("-------------------------------------------");
        System.out.println("Transfers");
        System.out.println("ID          From/To                 Amount");
        System.out.println("-------------------------------------------");

    }

    public void printAvailableTransfers(Transfer[] transfers, UserService service, int currentAccountId) {
        for (Transfer transfer : transfers) {
            String toFrom;
            int toFromId;
            if(transfer.getFromAccountId() == currentAccountId) {
                toFrom = "To";
                toFromId = transfer.getToAccountId();
            }
            else if(transfer.getToAccountId() == currentAccountId) {
                toFrom = "From";
                toFromId = transfer.getFromAccountId();
            }
            else {
                continue;
            }
            System.out.printf("%-11d %-5s: %-15s %s\n",
                    transfer.getId(),
                    toFrom, service.getUserByAccountId(toFromId).getUsername(),
                    getFormattedAmount(transfer.getTransferAmount()));
        }
    }

    private String getFormattedAmount(double amount) {
        return numberFormat.format(amount);
    }

    public void viewTransferDetails(
            int choice,
            TransferService transferService,
            TransferTypeService typeService,
            TransferStatusService statusService,
            UserService userService) {
        Transfer currentTransfer = transferService.getById(choice);

        System.out.println("--------------------------------------------");
        System.out.println("Transfer Details");
        System.out.println("--------------------------------------------");
        System.out.printf("ID:       %d\n",choice);
        System.out.printf("From:     %s\n", userService.getUserByAccountId(currentTransfer.getFromAccountId()).getUsername());
        System.out.printf("To:       %s\n", userService.getUserByAccountId(currentTransfer.getToAccountId()).getUsername());
        System.out.printf("Type:     %s\n", typeService.getById(currentTransfer.getTransferTypeId()).getTransferTypeDesc());
        System.out.printf("Status:   %s\n", statusService.getById(currentTransfer.getTransferStatusId()).getTransferStatusDesc());
        System.out.printf("Amount:   %s\n", getFormattedAmount(currentTransfer.getTransferAmount()));
    }
}
