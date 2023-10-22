package com.example.hycare.chatGPT;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatgptService chatgptService;


    @GetMapping("")
    public String getChatResponse(Map<String, String> stt) {

        // ChatGPT에게 질문
        return chatgptService.sendMessage(stt.values() + "병원 진료 대화 내용 3줄로 요약해줘.");

    }

}
