package com.mcgui;

public record ApplicationConfig(int port, String host, int boardSize) {
    public static final ApplicationConfig DEFAULT =
            new ApplicationConfig(8080,"localhost", 10);
}
