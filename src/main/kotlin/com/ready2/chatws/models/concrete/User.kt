package com.ready2.chatws.models.concrete

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import lombok.Data
import lombok.Generated
import lombok.Getter
import lombok.Setter
import org.hibernate.annotations.GenericGenerator


@Data
@Getter
@Setter
@Entity
@Table(name = "\"userr\"") //write table names with double quotes like this
data class User(
    @Id
    var nickName: String?,
    var fullName: String?,
    var status: Status?,
)
