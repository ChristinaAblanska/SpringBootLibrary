package com.example.chat.repository;

import com.example.chat.enumeration.MessageStatus;
import com.example.chat.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class MessageRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRepository userRepository;

//    public Message getMessageBySenderId(long senderId) {
//        return jdbcTemplate.queryForObject("""
//                SELECT id, content, sender_id, receiver_id, status, time_stamp
//                FROM message
//                WHERE sender_id=:senderId;
//                """, new MapSqlParameterSource("senderId", senderId), new RowMapper<Message>() {
//            @Override
//            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Message message = new Message();
//                message.setId(rs.getLong("id"));
//                message.setContent("content");
//                long senderId = rs.getLong("sender_id");
//                message.setSender(userRepository.getById(senderId));
//                long receiverId = rs.getLong("receiver_id");
//                message.setReceiver(userRepository.getById(receiverId));
//                String status = rs.getString("status");
//                message.setStatus(MessageStatus.valueOf(status));
//                return message;
//            }
//        });
//    }
//
//    public Message getMessageByReceiverId(long receiverId) {
//        return jdbcTemplate.queryForObject("""
//                SELECT id, content, sender_id, receiver_id, status, time_stamp
//                FROM message
//                WHERE receiver_id=:receiverId;
//                """, new MapSqlParameterSource("receiverId", receiverId), new RowMapper<Message>() {
//            @Override
//            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Message message = new Message();
//                message.setId(rs.getLong("id"));
//                message.setContent("content");
//                long senderId = rs.getLong("sender_id");
//                message.setSender(userRepository.getById(senderId));
//                long receiverId = rs.getLong("receiver_id");
//                message.setReceiver(userRepository.getById(receiverId));
//                String status = rs.getString("status");
//                message.setStatus(MessageStatus.valueOf(status));
//                return message;
//            }
//        });
//    }

    public List<Message> getAllPendingByReceiverId(long receiverId) {
        return jdbcTemplate.query("""
                SELECT id, content, sender_id, receiver_id, status, time_stamp
                FROM academy_chat.message
                WHERE status = "PENDING" and receiver_id=:receiverId
                """, new MapSqlParameterSource("receiverId", receiverId), new RowMapper<Message>() {
            @Override
            public Message mapRow(ResultSet rs, int rowNum) throws SQLException {
                Message message = new Message();
                message.setId(rs.getLong("id"));
                message.setContent(rs.getString("content"));
                long senderId = rs.getLong("sender_id");
                message.setSender(userRepository.getById(senderId));
                long receiverId = rs.getLong("receiver_id");
                message.setReceiver(userRepository.getById(receiverId));
                String status = rs.getString("status");
                message.setStatus(MessageStatus.valueOf(status));
                return message;
            }
        });
    }

    public void save(String content, long senderId, long receiverId, MessageStatus status, LocalDateTime timestamp) {
        jdbcTemplate.update("""
                INSERT INTO message
                (content, sender_id, receiver_id, status, time_stamp)
                VALUES(:content, :senderId, :receiverId, :status, :timestamp)
                """, new MapSqlParameterSource("content", content)
                .addValue("senderId", senderId)
                .addValue("receiverId", receiverId)
                .addValue("status", status.name())
                .addValue("timestamp", timestamp));
    }

    public void updateMessageById(long id, String status, LocalDateTime timestamp) {
        jdbcTemplate.update("""
                UPDATE message
                SET status=:status,
                time_stamp = :timestamp
                WHERE id=:id
                """, new MapSqlParameterSource("id", id)
                .addValue("status", status)
                .addValue("timestamp", timestamp));
    }

    public void deleteMessage(long id) {
        jdbcTemplate.update("DELETE FROM message WHERE id=:id", new MapSqlParameterSource("id", id));
    }
}