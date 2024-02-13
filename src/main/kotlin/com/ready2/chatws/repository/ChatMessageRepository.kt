package com.ready2.chatws.repository

import com.ready2.chatws.models.concrete.ChatMessage
import org.springframework.data.repository.CrudRepository

interface ChatMessageRepository:CrudRepository<ChatMessage,String> {
    fun findByChatId(chatId: String): List<ChatMessage>;
}