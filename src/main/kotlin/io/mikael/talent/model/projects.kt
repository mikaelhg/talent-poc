package io.mikael.talent.model

import java.time.LocalDate
import java.time.ZonedDateTime
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

enum class ProjectStatus {

    /**
     * Project definition has begun, but the project owner isn't yet ready to start looking for people.
     * A project can also be set back to DRAFT status after having been OPEN or CLOSED.
     *
     * It will not be listed, and the project page is not accessible.
     */
    DRAFT,

    /**
     * The project is actively looking for people.
     *
     * It is listed, and the project page is accessible.
     */
    OPEN,

    /**
     * The project is not looking for people.
     *
     * It will not be listed, but the project page still remains accessible.
     */
    CLOSED

}

@Entity @Table(name = "projects")
data class Project (

        @Id @GeneratedValue(strategy = IDENTITY)
        var id: Long? = null,

        @OneToMany(mappedBy = "project")
        var privateRecommendations: List<ProjectToPersonRecommendation>? = null,

        @OneToMany(mappedBy = "project")
        var publicRecommendations: List<PersonToProjectRecommendation>? = null,

        @ManyToOne
        var source: Person? = null,

        var createdAt: ZonedDateTime? = null,

        var updatedAt: ZonedDateTime? = null,

        var status: ProjectStatus? = null,

        var beginsAt: LocalDate? = null,

        var endsAt: LocalDate? = null,

        var country: String? = null,

        var location: String? = null,

        var name: String? = null,

        var description: String? = null

)

/**
 * Person $SOURCE publicly recommends person $TARGET for project $PROJECT.
 */
@Entity @Table(name = "recommend_people_for_projects")
data class PersonToProjectRecommendation (

        @Id @GeneratedValue(strategy = IDENTITY)
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

        @Id @GeneratedValue(strategy = IDENTITY)
        var id: Long? = null,

        @ManyToOne
        var project: Project? = null,

        @ManyToOne
        var source: Person? = null,

        @ManyToOne
        var target: Person? = null,

        var createdAt: ZonedDateTime? = null

)
