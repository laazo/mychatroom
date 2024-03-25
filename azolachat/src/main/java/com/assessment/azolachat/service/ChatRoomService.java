package com.assessment.azolachat.service;

import com.assessment.azolachat.entity.Chatroom;
import com.assessment.azolachat.entity.Message;
import com.assessment.azolachat.exception.ChatRoomException;

public interface ChatRoomService {
    Chatroom createChatRoom(String chatRoomName) throws ChatRoomException;
    Message sendMessage(long userId, String messageContent);
    Message readMessage(long messageId);
    void deleteMessage(long messageId);
}
