package com.assessment.azolachat.api.controller;

import com.assessment.azolachat.api.model.*;
import com.assessment.azolachat.exception.ChatRoomException;
import com.assessment.azolachat.service.ChatRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static com.assessment.azolachat.api.util.ModelMapper.buildResponse;

@RestController
@RequestMapping("chat")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    @Secured("ROLE_CHAT_USER")
    @PostMapping(path = "/create-chatroom", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatRoomResponse createChatRoom(@RequestBody CreateChatRoomRequest createChatRoomRequest) throws ChatRoomException {
        var chatRoom = chatRoomService.createChatRoom(createChatRoomRequest.getChatRoomName());
        return buildResponse(chatRoom);
    }

    @Secured("ROLE_CHAT_USER")
    @GetMapping(path = "/get-chatroom/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ChatRoomResponse getChatRoom(@PathVariable("id") long chatRoomId) throws ChatRoomException {
        var chatRoom = chatRoomService.getChatRoom(chatRoomId);
        return buildResponse(chatRoom);
    }

    @Secured("ROLE_CHAT_USER")
    @PostMapping(path = "/send-message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SendMessageResponse sendMessage(@RequestBody SendMessageRequest sendMessageRequest) throws ChatRoomException {
        var message = chatRoomService.sendMessage(sendMessageRequest.getUserId(), sendMessageRequest.getChatRoomId(), sendMessageRequest.getMessageContent());
        return buildResponse(message);
    }

    @Secured("ROLE_CHAT_USER")
    @PostMapping(path = "/read-message", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public SendMessageResponse readMessage(@RequestBody MessageRequest readMessageRequest) throws ChatRoomException {
        var message = chatRoomService.readMessage(readMessageRequest.getMessageId());
        return buildResponse(message);
    }

    @Secured("ROLE_CHAT_USER")
    @PostMapping(path = "/delete-message", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteMessage(@RequestBody MessageRequest deleteMessageRequest) throws ChatRoomException {
        chatRoomService.deleteMessage(deleteMessageRequest.getMessageId());
        return new ResponseEntity<>("Message deleted successfully", HttpStatus.OK);
    }
}
