package com.example.hycare.chatGPT;

import io.github.flashvayne.chatgpt.service.ChatgptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/summary")
public class testController {
    private final ChatService chatService;
    public String summary;

    //chat-gpt API 호출
     @PostMapping("")
     public String summary(@RequestBody Map<String, String> stt) {

        summary = chatService.getChatResponse(stt);

        return stt.get(summary);

    }

}
