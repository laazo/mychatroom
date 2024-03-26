package com.assessment.azolachat.api.controller;

import com.assessment.azolachat.api.model.CreateChatRoomRequest;
import com.assessment.azolachat.api.model.CreateChatRoomResponse;
import com.assessment.azolachat.api.model.SendMessageRequest;
import com.assessment.azolachat.api.model.SendMessageResponse;
import com.assessment.azolachat.entity.Message;
import com.assessment.azolachat.exception.ChatRoomException;
import com.assessment.azolachat.service.ChatRoomService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.assessment.azolachat.api.util.ModelMapper.buildResponse;

@RestController
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @PostMapping(path = "chat/create-chatroom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public CreateChatRoomResponse createChatRoom(CreateChatRoomRequest createChatRoomRequest) throws ChatRoomException {
        var chatRoom = chatRoomService.createChatRoom(createChatRoomRequest.getChatRoomName());
        return buildResponse(chatRoom);
    }

    @PostMapping(path = "chat/send-message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SendMessageResponse sendMessage(SendMessageRequest sendMessageRequest) throws ChatRoomException {
        var message = chatRoomService.sendMessage(sendMessageRequest.getUserId(), sendMessageRequest.getChatRoomId(), sendMessageRequest.getMessageContent());
        return buildResponse(message);
    }
}
