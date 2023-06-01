package com.example.ccdemo.Model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PineconeUpsertFailedResponse {
    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("details")
    private List<Details> details;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<Details> getDetails() {
        return details;
    }

    public PineconeUpsertFailedResponse(int code, String message, List<Details> details) {
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public PineconeUpsertFailedResponse() {
    }

    public static class Details {
        @SerializedName("typeUrl")
        private String typeUrl;

        @SerializedName("value")
        private String value;

        public String getTypeUrl() {
            return typeUrl;
        }

        public String getValue() {
            return value;
        }

        public Details(String typeUrl, String value) {
            this.typeUrl = typeUrl;
            this.value = value;
        }

        public Details() {
        }
    }
}

