package com.ecommerce.patterns.command;

public class AdminActionInvoker {
    private AdminCommand command;

    public void setCommand(AdminCommand command) {
        this.command = command;
    }

    public void executeCommand() {
        if (command != null) {
            command.execute();
        } else {
            System.out.println("No command set to execute.");
        }
    }
}