package com.thinkdevs.mspoultrymgt.data.respose;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {

    private BodyHeader header;

    @JsonIgnore
    private Integer responseCode;

    private T body;

    private Response(T body) {
        this.body = body;
    }

    private Response(T body, Integer responseCode) {
        this.body = body;
        this.responseCode = responseCode;
    }

    public Response(BodyHeader header, T body) {
        this.header = header;
        this.body = body;
    }

    public static <T> Response<T> ofData(T body) {
        return new Response<T>(body);
    }

    public static <T> Response<T> ofData(T body, Integer responseCode) {
        return new Response<T>(body, responseCode);
    }

}
