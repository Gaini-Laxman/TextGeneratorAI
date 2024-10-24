package com.klinnovations.service;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenAIResponse {
    @JsonProperty("choices")
    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public static class Choice {
        @JsonProperty("message")
        private Message message;

        public Message getMessage() {
            return message;
        }
    }

    public static class Message {
        @JsonProperty("content")
        private String content;

        public String getContent() {
            return content;
        }
    }
}
