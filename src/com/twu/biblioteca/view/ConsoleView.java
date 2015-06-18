package com.twu.biblioteca.view;


import java.io.BufferedReader;
import java.io.IOException;

public class ConsoleView {

    private BufferedReader bufferedReader;

    public ConsoleView(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public void print(String message) {
        System.out.println(message);
    }

    public int read() throws IOException {
        int userInput = -1;
        try {
            userInput = Integer.parseInt(bufferedReader.readLine());
        } catch (NumberFormatException e) {
        }
        return userInput;
    }

    public String getName() throws IOException {
        return bufferedReader.readLine();
    }
}
