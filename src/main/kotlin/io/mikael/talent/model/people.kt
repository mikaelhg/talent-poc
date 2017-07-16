package io.mikael.talent.model

import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import java.time.ZonedDateTime
import javax.persistence.*
import javax.persistence.GenerationType.IDENTITY

/**
 * People. Can't live with them, but they go poorly with the nicer reds and whites.
 */
@TypeDefs(TypeDef(name = "stringarray", typeClass = SqlStringArray::class))
@Entity @Table(name = "people")
data class Person(

    @get:Id @get:GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,

    /** Someone recommended a project to this person in private. */
    @get:OneToMany(mappedBy = "target")
    var privateTargetRecommendations: MutableList<ProjectToPersonRecommendation>? = mutableListOf(),

    /** Someone recommended this person for a project in public. */
    @get:OneToMany(mappedBy = "target")
    var publicTargetRecommendations: MutableList<PersonToProjectRecommendation>? = mutableListOf(),

    /** This person recommended a project to someone in private. */
    @get:OneToMany(mappedBy = "source")
    var privateSourceRecommendations: MutableList<ProjectToPersonRecommendation>? = mutableListOf(),

    /** This person recommended someone for a project in public. */
    @get:OneToMany(mappedBy = "source")
    var publicSourceRecommendations: MutableList<PersonToProjectRecommendation>? = mutableListOf(),

    @get:OneToMany(mappedBy = "target")
    var personalAbilities: MutableList<PersonalAbility>? = mutableListOf(),

    var username: String? = "",

    var name: String? = "",

    var title: String? = "",

    var description: String? = "",

    @get:Type(type="stringarray")
    @get:Column(columnDefinition = "TEXT[]")
    var locations: MutableList<String>? = mutableListOf(),

    @get:Type(type="stringarray")
    @get:Column(columnDefinition = "TEXT[]")
    var roles: MutableList<String>? = mutableListOf(),

    @get:Type(type="stringarray")
    @get:Column(columnDefinition = "TEXT[]")
    var skills: MutableList<String>? = mutableListOf(),

    @get:Type(type="stringarray")
    @get:Column(columnDefinition = "TEXT[]")
    var interests: MutableList<String>? = mutableListOf(),

    var pictureUrl: String? = "",

    var lastSeenAt: ZonedDateTime? = ZonedDateTime.now()

) {

    override fun toString(): String {
        return "Person(id=$id, username=$username, name=$name, title=$title, locations=$locations, roles=$roles, skills=$skills, interests=$interests)"
    }

}
