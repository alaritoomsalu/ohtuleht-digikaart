package com.pooltoon.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.concurrent.locks.ReentrantLock;

@WebServlet("/count-wish")
public class WishCounterServlet extends HttpServlet {

    private static final Path FILE_PATH = Path.of("data/wish_count.txt");
    private final ReentrantLock lock = new ReentrantLock();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        lock.lock();
        try {
            // Read the current count
            long count = Long.parseLong(Files.readString(FILE_PATH).trim());

            // Increment and write back
            count++;
            Files.writeString(FILE_PATH, String.valueOf(count), StandardOpenOption.WRITE);

            // Respond with the updated count
            resp.setContentType("application/json");
            resp.getWriter().write("{\"totalWishes\":" + count + "}");
        } catch (IOException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update wish count");
        } finally {
            lock.unlock();
        }
    }
}