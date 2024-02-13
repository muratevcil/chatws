package com.ready2.chatws.business.abstract

import com.ready2.chatws.models.concrete.ChatRoom
import org.springframework.stereotype.Service
import java.util.Optional

@Service
interface ChatRoomService {
    fun getChatRoomId(
        senderId:String,
        recipientId:String,
        createNewRoomIfNotExists:Boolean,
    ) : String?;

    fun createChatId(
        senderId:String,
        recipientId:String
    ) : String;

}