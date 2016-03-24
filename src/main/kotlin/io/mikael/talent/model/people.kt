package io.mikael.talent.model

import java.time.ZonedDateTime
import javax.persistence.*

/**
 * People. Can't live with them, but they go poorly with the nicer reds and whites.
 */
@Entity @Table(name = "people")
data class Person(

        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        /** Someone recommended a project to this person in private. */
        @OneToMany(mappedBy = "target")
        var privateTargetRecommendations: List<ProjectToPersonRecommendation>? = null,

        /** Someone recommended this person to a project in public. */
        @OneToMany(mappedBy = "target")
        var publicTargetRecommendations: List<PersonToProjectRecommendation>? = null,

        /** This person recommended a project to someone in private. */
        @OneToMany(mappedBy = "source")
        var privateSourceRecommendations: List<ProjectToPersonRecommendation>? = null,

        /** This person recommended someone to a project in public. */
        @OneToMany(mappedBy = "source")
        var publicSourceRecommendations: List<PersonToProjectRecommendation>? = null,

        @OneToMany(mappedBy = "target")
        var personalAbilities: List<PersonalAbility>? = null,

        var username: String? = null,

        var lastSeenAt: ZonedDateTime? = null

)
