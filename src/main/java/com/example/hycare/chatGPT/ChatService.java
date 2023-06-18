package com.example.hycare.chatGPT;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatgptService chatgptService;

    @PostMapping("")
    public String getChatResponse(String prompt) {

        // ChatGPT에게 질문
        return chatgptService.sendMessage(prompt);
    }

}
