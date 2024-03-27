package com.assessment.azolachat.service;

import com.assessment.azolachat.entity.Chatroom;
import com.assessment.azolachat.entity.Message;
import com.assessment.azolachat.entity.User;
import com.assessment.azolachat.exception.ChatRoomException;
import com.assessment.azolachat.repo.ChatRoomRepository;
import com.assessment.azolachat.repo.MessageRepository;
import com.assessment.azolachat.repo.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public ChatRoomServiceImpl(ChatRoomRepository chatRoomRepository, UserRepository userRepository, MessageRepository messageRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public Chatroom createChatRoom(String chatRoomName) throws ChatRoomException {
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
    public Chatroom getChatRoom(long chatRoomId) throws ChatRoomException {
        Optional<Chatroom> chatroom = chatRoomRepository.findById(chatRoomId);
        if(chatroom.isEmpty()) {
            log.error("Exception occurred in getChatroom: {}", chatRoomId);
            throw new ChatRoomException("Chat room does not exist");
        }
        return chatroom.get();
    }

    @Override
    public Message sendMessage(long userId, long chatRoomId, String messageContent) throws ChatRoomException {
        Optional<Chatroom> chatroom = chatRoomRepository.findById(chatRoomId);
        if(chatroom.isEmpty()) {
            log.error("Exception occurred in sendMessage: {}", messageContent);
            throw new ChatRoomException("Cannot send a message to a chat room that does not exist");
        }

        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()) {
            log.error("Exception occurred in sendMessage: {}", messageContent);
            throw new ChatRoomException("Cannot send a message from a user that does not exist");
        }
        Message message = new Message();
        message.setUserId(userId);
        message.setChatRoomId(chatRoomId);
        message.setMessageContent(messageContent);
        messageRepository.save(message);
        return message;
    }

    @Override
    public Message readMessage(long messageId) throws ChatRoomException {
        Optional<Message> message = messageRepository.findById(messageId);
        if(message.isEmpty()) {
            log.error("Exception occurred in readMessage: ");
            throw new ChatRoomException("Message does not exist");
        }
        return message.get();
    }

    @Override
    public void deleteMessage(long messageId) throws ChatRoomException {
        Optional<Message> message = messageRepository.findById(messageId);
        if(message.isEmpty()) {
            log.error("Exception occurred in deleteMessage: ");
            throw new ChatRoomException("Message does not exist");
        }
        messageRepository.delete(message.get());
    }
}
