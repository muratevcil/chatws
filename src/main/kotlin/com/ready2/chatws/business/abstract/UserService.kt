package com.ready2.chatws.business.abstract

import com.ready2.chatws.models.concrete.Status
import com.ready2.chatws.models.concrete.User
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service


@Service
@RequiredArgsConstructor
interface UserService {
    public fun saveUser(user:User);
    public fun disconnect(user:User);
    public fun findUser(status: Status) : List<User>;

}