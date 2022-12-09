package com.thinkdevs.mspoultrymgt.data.respose;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

@Data
public class BodyHeader {

    private UUID transactionId;

    private Integer responseCode;

    private String responseMessage;

    private String message;

    private Instant timestamp;

    private BodyHeader(Integer responseCode, String responseMessage,
                       String message) {
        this.transactionId = UUID.randomUUID();
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.message = message;
        this.timestamp = Instant.now();
    }

    public static BodyHeader header(Integer responseCode, String responseMessage, String customerMessage) {
        return new BodyHeader(responseCode, responseMessage, customerMessage);
    }
}
