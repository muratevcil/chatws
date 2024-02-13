package com.ready2.chatws.business.concrete

import com.ready2.chatws.business.abstract.ChatMessageService
import com.ready2.chatws.business.abstract.ChatRoomService
import com.ready2.chatws.models.concrete.ChatMessage
import com.ready2.chatws.repository.ChatMessageRepository
import lombok.RequiredArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Service
@RequiredArgsConstructor
class ChatMessageManager:ChatMessageService {
    @Autowired
    lateinit var chatMessageRepository:ChatMessageRepository;
    @Autowired
    lateinit var chatRoomService:ChatRoomService;

    override fun save(chatMessage: ChatMessage):ChatMessage{
        val chatId = chatRoomService.getChatRoomId(
            chatMessage.senderId.toString(),
            chatMessage.recipientId.toString(),
            true
        );
        chatMessage.chatId = chatId.toString();
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }

    override fun findChatMessages(
        senderId: String,
        recipientId: String
    ): List<ChatMessage> {
        val emptyArrayList:ArrayList<ChatMessage> = ArrayList<ChatMessage>();
        val chatId = chatRoomService.getChatRoomId(
            senderId,
            recipientId,
            false,
        );
        return chatMessageRepository.findByChatId(chatId.toString());
    }
}