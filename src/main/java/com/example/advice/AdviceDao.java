package com.example.advice;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AdviceDao {

    private static final String INSERT_SQL =
            "INSERT INTO advice (content, likes, user_id, created_at) VALUES (?, 0, ?, NOW())";

    public boolean createAdvice(String content, int userId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            ps.setString(1, content);
            ps.setInt(2, userId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
