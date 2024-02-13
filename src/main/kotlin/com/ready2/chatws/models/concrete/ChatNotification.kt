package com.ready2.chatws.models.concrete

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
data class ChatNotification(
    var id:String,
    var senderId:String,
    var recipientId:String,
    var content:String,
)