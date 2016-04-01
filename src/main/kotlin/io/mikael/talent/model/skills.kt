package io.mikael.talent.model

import java.time.ZonedDateTime
import javax.persistence.*

/**
 * The abstract "Java Programming" is a skill.
 */
@Entity @Table(name = "skills")
data class Skill (

        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        @OneToMany(mappedBy = "skill")
        var personalAbilities: List<PersonalAbility>? = null,

        var tag: String? = null,

        var name: String? = null,

        var description: String? = null

)

/**
 * The concrete "Jack is a good (10) Java Programmer" is an indicator of personal ability.
 */
@Entity @Table(name = "personal_abilities")
data class PersonalAbility (

        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne
        var target: Person? = null,

        @ManyToOne
        var skill: Skill? = null,

        /** How interested the person is in applying this skill in their next project. */
        var personalInterestLevel: Double? = 0.0,

        var createdAt: ZonedDateTime? = null,

        var modifiedAt: ZonedDateTime? = null,

        var level: Int? = null,

        /** A little story about the relationship between the person and their ability. */
        var comment: String? = null

)

/**
 * Ben gives a "thumbs up" to the "Jack is a good Java Programmer" indicator of personal ability,
 * supporting the indicator.
 *
 * This might also mean that in Ben's opinion, Jack's ability is greater than Jack actually indicates.
 */
@Entity @Table(name = "thumb_personal_abilities")
data class PersonalAbilityThumbs (

        @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
        var id: Long? = null,

        @ManyToOne
        var source: Person? = null,

        @ManyToOne
        var target: PersonalAbility? = null,

        var createdAt: ZonedDateTime? = null

)

