package com.ready2.chatws.business.abstract

import com.ready2.chatws.models.concrete.ChatMessage
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
interface ChatMessageService {
    fun save(chatMessage:ChatMessage):ChatMessage;
    fun findChatMessages(senderId:String,recipientId:String):List<ChatMessage>;
}