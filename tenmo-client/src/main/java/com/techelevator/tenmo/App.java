package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.*;
import com.techelevator.util.BasicLogger;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private final AccountService accountService = new AccountService(API_BASE_URL);
    private final UserService userService = new UserService(API_BASE_URL);
    private final TransferService transferService = new TransferService(API_BASE_URL);
    private final TransferTypeService transferTypeService = new TransferTypeService(API_BASE_URL);
    private final TransferStatusService transferStatusService = new TransferStatusService(API_BASE_URL);

    private AuthenticatedUser currentUser;

    public enum TypeEnum {
        SEND,REQUEST
    }

    public enum StatusEnum {
        APPROVED,PENDING,REJECTED
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            accountService.setAuthToken(currentUser.getToken());
            userService.setAuthToken(currentUser.getToken());
            transferService.setAuthToken(currentUser.getToken());
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
		// TODO Auto-generated method stub

        consoleService.printAccountBalance(getCurrentAccount());

		
	}

	private void viewTransferHistory() {
		// TODO Auto-generated method stub
        consoleService.printViewTransferHeader();
        consoleService.printAvailableTransfers(transferService.getAllTransfers(), userService, getCurrentAccount().getId());
        int choice = consoleService.promptForInt("Please enter transfer ID to view details (0 to cancel): ");

        while (!isValidTransferId(choice)) {
            choice = consoleService.promptForInt("Invalid Choice. Select a different ID (0 to cancel): ");
        }
        if(choice == 0) {
            return;
        }
        consoleService.viewTransferDetails(choice, transferService, transferTypeService,transferStatusService, userService);

	}

	private void viewPendingRequests() {
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() {
		// TODO Auto-generated method stub

        consoleService.printSendFundsHeader();
        consoleService.printUsers(userService.listUsers(), currentUser.getUser());
        int userId = consoleService.promptForInt("Please Enter ID of user you are sending to (0 to cancel): ");
        while (!isValidSendId(userId)) {
            userId = consoleService.promptForInt("Invalid Choice. Select a different ID (0 to cancel): ");
        }
        if(userId == 0) {
            return;
        }

        double transferAmount = consoleService.promptForBigDecimal("Enter Amount to Send: ").doubleValue();
        Account toAccount = accountService.getAccountByUserId(userId);
        Account currentAccount = accountService.getAccountByUserId(getCurrentUserId());

        try {
            currentAccount.transferTo(toAccount, transferAmount);
        } catch (IllegalArgumentException e) {
            BasicLogger.log(e.getMessage());
            consoleService.printErrorMessage();
            return;
        }
        accountService.update(getCurrentUserId(),currentAccount);
        accountService.update(userId,toAccount);
        transferService.create(new Transfer(
                getTypeId(TypeEnum.SEND.name()),
                getStatusId(StatusEnum.APPROVED.name()),
                getCurrentAccount().getId(),
                toAccount.getId(),
                transferAmount));

		
	}

	private void requestBucks() {
		// TODO Auto-generated method stub
		
	}

    private int getCurrentUserId() {
        return Math.toIntExact(currentUser.getUser().getId());
    }

    /**
     * Validates whether the ID provided is a valid ID to send to from the user
     * @param id The ID of the User being sent money
     * @return True if id is a valid user ID or 0, False otherwise
     */
    private boolean isValidSendId(int id) {
        if(id == 0) {
            return true;
        }
        if(id == getCurrentUserId()) {
            return false;
        }
        return accountService.getAccountByUserId(id) != null;
    }

    /**
     * Validates whether the ID provided is a valid transfer id the current user can view
     * @param id The Transfer ID entered by current user
     * @return True if id is a valid Transfer ID or 0, False otherwise
     */
    private boolean isValidTransferId(int id) {
        if(id == 0) {
            return true;
        }
        else if (transferService.getById(id) == null){
            return false;
        }
        else return transferService.getById(id).getFromAccountId() == getCurrentAccount().getId() ||
                    transferService.getById(id).getToAccountId() == getCurrentAccount().getId();

    }

    /**
     * Sends a request to retrieve the type ID of the String provided
     * @param type The type associated with the Transfer
     * @return The id of the TransferType
     */
    private int getTypeId(String type) {
        TransferType transferType = transferTypeService.getByType(type);
        return Math.toIntExact(transferType.getTransferTypeId());
    }

    /**
     * Sends a request to retrieve the status ID of the String provided
     * @param status The Status associated with the Transfer
     * @return The id of the TransferStatus
     */
    private int getStatusId(String status) {
        TransferStatus transferStatus = transferStatusService.getByType(status);
        return Math.toIntExact(transferStatus.getTransferStatusId());
    }

    private Account getCurrentAccount() {
        return accountService.getAccountByUserId(getCurrentUserId());
    }



}
