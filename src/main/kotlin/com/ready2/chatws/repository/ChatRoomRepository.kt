package com.ready2.chatws.repository

import com.ready2.chatws.models.concrete.ChatRoom
import org.springframework.data.repository.CrudRepository
import java.util.Optional

interface ChatRoomRepository:CrudRepository<ChatRoom,String> {
    fun findByChatId(chatId:String):ChatRoom;
    fun findBySenderIdAndRecipientId(senderId:String,recipientId:String):ChatRoom?;
}