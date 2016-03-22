package io.mikael.talent.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity @Table(name = "projects")
data class Project (

        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        @OneToMany(mappedBy = "project")
        var privateRecommendations: List<ProjectToPersonRecommendation>? = null,

        @OneToMany(mappedBy = "project")
        var publicRecommendations: List<PersonToProjectRecommendation>? = null,

        @ManyToOne
        var creator: Person? = null,

        var created: ZonedDateTime? = null,

        var updated: ZonedDateTime? = null

)

/**
 * Person $SOURCE publicly recommends person $TARGET for project $PROJECT.
 */
@Entity @Table(name = "recommend_people_for_projects")
data class PersonToProjectRecommendation (

        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne
        var project: Project? = null,

        @ManyToOne
        var source: Person? = null,

        @ManyToOne
        var target: Person? = null,

        var createdAt: ZonedDateTime? = null
)

/**
 * Person $SOURCE privately recommends project $PROJECT to person $TARGET.
 */
@Entity @Table(name = "recommend_projects_to_people")
data class ProjectToPersonRecommendation (

        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne
        var project: Project? = null,

        @ManyToOne
        var source: Person? = null,

        @ManyToOne
        var target: Person? = null,

        var createdAt: ZonedDateTime? = null

)
