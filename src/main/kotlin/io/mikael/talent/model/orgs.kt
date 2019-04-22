package io.mikael.talent.model

import javax.persistence.*
import javax.persistence.FetchType.LAZY
import javax.persistence.GenerationType.IDENTITY

/**
 * A customer, an employer.
 */
@Entity @Table(name = "organizations")
data class Organization(

    @get:Id @get:GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,

    /**
     * Is this organization subsidiary to a larger one?
     */
    @get:ManyToOne(fetch = LAZY)
    var parent: Organization? = null,

    var name: String = "",

    var description: String = ""

)
