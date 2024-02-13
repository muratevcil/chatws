package com.ready2.chatws.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.converter.DefaultContentTypeResolver
import org.springframework.messaging.converter.MappingJackson2MessageConverter
import org.springframework.messaging.converter.MessageConverter
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.util.MimeTypeUtils
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer


@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig(private val messageConverters: HttpMessageConverters) : WebSocketMessageBrokerConfigurer {

    @Override
    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableSimpleBroker("/topic"); //belirli bir hedefe gönderilen mesajları dinleyen bir broker ayarlar.it configures an endpoint to listen all mesages which sended to that location.
        registry.setApplicationDestinationPrefixes("/app");  // gelen mesajları işleecek uygulama kodunun yerini belirtir.it defines the endpoint where to handle incoming messages.
        registry.setUserDestinationPrefix(("/user/{userId}")); // kullanıcıya özgü hedeflerin ön ekini belirler. specifies the prefix for user-spesific destinations
    //registry.setUserDestinationPrefix("user/{userId}") satırı aracılığıyla hedefleri belirtiyoruz.
    }

    @Override
    override fun registerStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws") //websocket bağlantı noktasını tanımlar. defines the point of connection to the websocket.
            .withSockJS();
    }

    @Override
    override fun configureMessageConverters(messageConverters: MutableList<MessageConverter>): Boolean {
        val resolver:DefaultContentTypeResolver = DefaultContentTypeResolver();
        resolver.defaultMimeType = MimeTypeUtils.APPLICATION_JSON;
        val converter:MappingJackson2MessageConverter = MappingJackson2MessageConverter();
        converter.objectMapper = ObjectMapper();
        converter.contentTypeResolver = resolver;
        messageConverters.add(converter);
        return false;
    }

}
