package com.example.spring_boot_rabbit_mq.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagePayload {
    private String id;
    private String content;
    private LocalDateTime timestamp;
}
