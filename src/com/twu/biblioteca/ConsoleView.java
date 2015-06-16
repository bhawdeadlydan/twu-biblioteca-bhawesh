package com.twu.biblioteca;


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
        return Integer.parseInt(bufferedReader.readLine());
    }

    public String getBookName() throws IOException {
        return bufferedReader.readLine();
    }
}
