package io.mikael.talent.model

import io.mikael.talent.util.SqlStringArray
import org.hibernate.annotations.Type
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import java.time.ZonedDateTime
import javax.persistence.*
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.IDENTITY

/**
 * People. Can't live with them, but they match poorly with the nicer reds and whites.
 */
@TypeDefs(TypeDef(name = "string-array", typeClass = SqlStringArray::class))
@Entity @Table(name = "people")
data class Person(

    @get:Id @get:GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,

    /** Someone recommended a project to this person in private. */
    @get:OneToMany(mappedBy = "target", fetch = LAZY)
    var privateTargetRecommendations: MutableList<ProjectToPersonRecommendation>? = mutableListOf(),

    /** Someone recommended this person for a project in public. */
    @get:OneToMany(mappedBy = "target", fetch = LAZY)
    var publicTargetRecommendations: MutableList<PersonToProjectRecommendation>? = mutableListOf(),

    /** This person recommended a project to someone in private. */
    @get:OneToMany(mappedBy = "source", fetch = LAZY)
    var privateSourceRecommendations: MutableList<ProjectToPersonRecommendation>? = mutableListOf(),

    /** This person recommended someone for a project in public. */
    @get:OneToMany(mappedBy = "source", fetch = LAZY)
    var publicSourceRecommendations: MutableList<PersonToProjectRecommendation>? = mutableListOf(),

    @get:OneToMany(mappedBy = "target", fetch = LAZY)
    var personalAbilities: MutableList<PersonalAbility>? = mutableListOf(),

    var username: String? = "",

    var name: String? = "",

    var title: String? = "",

    var description: String? = "",

    @get:Column(columnDefinition = "TEXT[]")
    @get:Type(type="string-array")
    var locations: MutableList<String>? = mutableListOf(),

    @get:Column(columnDefinition = "TEXT[]")
    @get:Type(type="string-array")
    var roles: MutableList<String>? = mutableListOf(),

    @get:Column(columnDefinition = "TEXT[]")
    @get:Type(type="string-array")
    var skills: MutableList<String>? = mutableListOf(),

    @get:Column(columnDefinition = "TEXT[]")
    @get:Type(type="string-array")
    var interests: MutableList<String>? = mutableListOf(),

    var pictureUrl: String? = "",

    var lastSeenAt: ZonedDateTime? = ZonedDateTime.now()

) {

    override fun toString(): String {
        return "Person(id=$id, username=$username, name=$name, title=$title, locations=$locations, roles=$roles, skills=$skills, interests=$interests)"
    }

}
