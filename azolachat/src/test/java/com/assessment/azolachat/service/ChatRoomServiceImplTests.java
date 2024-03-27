package com.assessment.azolachat.service;

import com.assessment.azolachat.entity.Chatroom;
import com.assessment.azolachat.entity.Message;
import com.assessment.azolachat.entity.User;
import com.assessment.azolachat.exception.ChatRoomException;
import com.assessment.azolachat.repo.ChatRoomRepository;
import com.assessment.azolachat.repo.MessageRepository;
import com.assessment.azolachat.repo.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ChatRoomServiceImplTests {

    private ChatRoomRepository chatRoomRepository;
    private MessageRepository messageRepository;
    private UserRepository userRepository;
    private ChatRoomService chatRoomService;

    @Before
    public void init() {
        chatRoomRepository = mock(ChatRoomRepository.class);
        messageRepository = mock(MessageRepository.class);
        userRepository = mock(UserRepository.class);
        chatRoomService = new ChatRoomServiceImpl(chatRoomRepository, userRepository, messageRepository);
    }

    @Test
    public void testCreateChatroomSuccess() throws ChatRoomException {
        Chatroom testChatRoom = chatRoomService.createChatRoom("TestChatRoom");
        verify(chatRoomRepository).save(any());
        assertEquals("TestChatRoom", testChatRoom.getChatRoomName());
    }

    @Test
    public void testCreateChatRoomFail_NameNotProvided() {
        try {
            chatRoomService.createChatRoom(null);
        } catch (ChatRoomException e) {
            assertEquals("Chatroom name not provided", e.getMessage());
        }
    }

    @Test
    public void testSendMessageSuccess() throws ChatRoomException {
        when(chatRoomRepository.findById(anyLong())).thenReturn(Optional.of(new Chatroom()));
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(new User()));
        Message message = chatRoomService.sendMessage(1L, 1L, "new message");
        verify(messageRepository).save(any());
        assertEquals("new message", message.getMessageContent());
        assertEquals(1L, message.getUserId());
        assertEquals(1L, message.getChatRoomId());
    }

    @Test
    public void testSendMessageFail_NoChatRoomProvided() {
        when(chatRoomRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            chatRoomService.sendMessage(1L, 1L, "new message");
        } catch (ChatRoomException e) {
            assertEquals("Cannot send a message to a chat room that does not exist", e.getMessage());
        }
    }

    @Test
    public void testSendMessageFail_NoUserProvided() {
        when(chatRoomRepository.findById(anyLong())).thenReturn(Optional.of(new Chatroom()));
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        try {
            chatRoomService.sendMessage(1L, 1L, "new message");
        } catch (ChatRoomException e) {
            assertEquals("Cannot send a message from a user that does not exist", e.getMessage());
        }
    }

    @Test
    public void testGetChatRoomSuccess() throws ChatRoomException {
        when(chatRoomRepository.findById(anyLong())).thenReturn(Optional.of(new Chatroom()));
        Chatroom chatRoom = chatRoomService.getChatRoom(1L);
        assertNotNull(chatRoom);

    }

    @Test
    public void testReadMessageSuccess() throws ChatRoomException {
        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(new Message()));
        Message message = chatRoomService.readMessage(1L);
        assertNotNull(message);

    }

    @Test
    public void testDeleteMessageSuccess() throws ChatRoomException {
        when(messageRepository.findById(anyLong())).thenReturn(Optional.of(new Message()));
        chatRoomService.deleteMessage(1L);
        verify(messageRepository).delete(any());
    }
}
