package com.assessment.azolachat.service;

import com.assessment.azolachat.entity.Chatroom;
import com.assessment.azolachat.entity.Message;
import com.assessment.azolachat.exception.ChatRoomException;
import com.assessment.azolachat.repo.ChatRoomRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository) {
        this.chatRoomRepository = chatRoomRepository;
    }

    @Override
    public Chatroom createChatRoom(String chatRoomName) throws ChatRoomException{
        if(chatRoomName == null || chatRoomName.isBlank()) {
            log.error("Exception occurred in createChatRoom: ");
            throw new ChatRoomException("Chatroom name not provided");
        }
        Chatroom newChatRoom = new Chatroom();
        newChatRoom.setChatRoomName(chatRoomName);
        chatRoomRepository.save(newChatRoom);
        return newChatRoom;
    }

    @Override
    public Message sendMessage(long userId, String messageContent) {
        return null;
    }

    @Override
    public Message readMessage(long messageId) {
        return null;
    }

    @Override
    public void deleteMessage(long messageId) {

    }
}
