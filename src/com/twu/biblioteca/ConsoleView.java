package com.twu.biblioteca;


import java.util.Scanner;

public class ConsoleView {

    private Scanner scanner;

    public ConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void print(String message) {
        System.out.println(message);
    }

    public int read() {
        return scanner.nextInt();
    }
}
