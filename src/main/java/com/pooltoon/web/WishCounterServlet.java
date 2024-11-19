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
        // Set CORS headers for POST request
        setCorsHeaders(resp);

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

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Set CORS headers for OPTIONS pre-flight request
        setCorsHeaders(resp);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    private void setCorsHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "https://ohtuleht-digikaart.netlify.app");
        resp.setHeader("Access-Control-Allow-Methods", "POST, OPTIONS");
        resp.setHeader("Access-Control-Allow-Headers", "Content-Type");
    }
}