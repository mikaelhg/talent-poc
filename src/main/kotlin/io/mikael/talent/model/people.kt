package io.mikael.talent.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity @Table(name = "people")
data class Person(

        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        @OneToMany(mappedBy = "target")
        var privateTargetRecommendations: List<ProjectToPersonRecommendation>? = null,

        @OneToMany(mappedBy = "target")
        var publicTargetRecommendations: List<PersonToProjectRecommendation>? = null,

        @OneToMany(mappedBy = "source")
        var privateSourceRecommendations: List<ProjectToPersonRecommendation>? = null,

        @OneToMany(mappedBy = "source")
        var publicSourceRecommendations: List<PersonToProjectRecommendation>? = null,

        @OneToMany(mappedBy = "target")
        var personalAbilities: List<PersonalAbility>? = null,

        var username: String? = null,

        var lastSeenAt: ZonedDateTime? = null

)
