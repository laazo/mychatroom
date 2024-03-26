package com.assessment.azolachat.service;

import com.assessment.azolachat.entity.Chatroom;
import com.assessment.azolachat.entity.Message;
import com.assessment.azolachat.exception.ChatRoomException;

public interface ChatRoomService {
    Chatroom createChatRoom(String chatRoomName) throws ChatRoomException;
    Message sendMessage(long userId, long chatRoomId, String messageContent) throws ChatRoomException;
    Message readMessage(long messageId) throws ChatRoomException;
    void deleteMessage(long messageId) throws ChatRoomException;
}
