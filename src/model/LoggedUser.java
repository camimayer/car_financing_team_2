package model;

import model.Client;
import model.Investor;

public class LoggedUser {
    private static LoggedUser instance;
    private int loggedUserId;
    private boolean isInvestor;

    private LoggedUser() {
    }

    public static LoggedUser getInstance() {
        if (instance == null) {
            synchronized (LoggedUser.class) {
                if (instance == null) {
                    instance = new LoggedUser();
                }
            }
        }
        return instance;
    }

    // Getters e setters para loggedUserId e isInvestor
    public int getLoggedUserId() {
        return loggedUserId;
    }

    public void setLoggedUserId(int loggedUserId) {
        this.loggedUserId = loggedUserId;
    }

    public boolean isInvestor() {
        return isInvestor;
    }

    public void setInvestor(boolean investor) {
        isInvestor = investor;
    }
}
