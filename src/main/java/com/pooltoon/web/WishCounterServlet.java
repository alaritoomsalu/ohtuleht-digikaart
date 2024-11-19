package com.pooltoon.web;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@WebServlet("/count-wish")
public class WishCounterServlet extends HttpServlet {

    private static final Path FILE_PATH = Path.of("wish_count.txt");

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Access-Control-Allow-Origin", "https://ohtuleht-digikaart.netlify.app");
        resp.setHeader("Access-Control-Allow-Methods", "POST");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");

        if (!Files.exists(FILE_PATH)) {
            Files.createFile(FILE_PATH);
            Files.writeString(FILE_PATH, "0", StandardOpenOption.WRITE);
        }

        synchronized (this) { // Optional synchronization for thread-safety
            long count = Long.parseLong(Files.readString(FILE_PATH).trim());
            count++;
            Files.writeString(FILE_PATH, String.valueOf(count), StandardOpenOption.WRITE);
            resp.setContentType("application/json");
            resp.getWriter().write("{\"totalWishes\":" + count + "}");
        }
    }
}