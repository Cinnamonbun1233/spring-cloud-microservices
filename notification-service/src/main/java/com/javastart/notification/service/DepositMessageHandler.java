package com.javastart.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javastart.notification.config.RabbitMQConfig;
import com.javastart.notification.dto.DepositResponseDto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class DepositMessageHandler {
    private final JavaMailSender javaMailSender;

    @Autowired
    public DepositMessageHandler(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_DEPOSIT)
    public void receive(Message message) throws JsonProcessingException {
        System.out.println(message);
        byte[] body = message.getBody();
        String jsonBody = new String(body);
        ObjectMapper objectMapper = new ObjectMapper();
        DepositResponseDto depositResponseDto = objectMapper.readValue(jsonBody, DepositResponseDto.class);
        System.out.println(depositResponseDto);

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(depositResponseDto.getEmail());
        simpleMailMessage.setFrom("lori@cat.xyz");

        simpleMailMessage.setSubject("Deposit");
        simpleMailMessage.setText(String.format("Make deposit, sum: %s", depositResponseDto.getAmount()));

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}