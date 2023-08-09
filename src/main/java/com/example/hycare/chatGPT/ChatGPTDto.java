package com.example.hycare.chatGPT;

public class ChatGPTDto {
    String stt; // stt로 변환한 전문
    String summary; // chatGPT로 요약한 내용

    public ChatGPTDto() {}

    public ChatGPTDto(String stt, String summary) {
        this.stt = stt;
        this.summary = summary;
    }

    public String getStt() {
        return stt;
    }

    public void setStt(String stt) {
        this.stt = stt;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
