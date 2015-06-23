package com.twu.biblioteca.action;

import com.twu.biblioteca.model.UserHistory;
import com.twu.biblioteca.view.ConsoleView;

public class UserHistoryAction implements MenuAction {
    private UserHistory userHistory;
    private ConsoleView consoleView;

    public UserHistoryAction(UserHistory userHistory, ConsoleView consoleView) {
        this.userHistory = userHistory;
        this.consoleView = consoleView;
    }

    @Override
    public void performAction() {
        consoleView.print(userHistory.toString());
    }
}
