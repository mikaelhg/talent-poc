package io.mikael.talent.model

import javax.persistence.*

/**
 * A customer, an employer.
 */
@Entity @Table(name = "organizations")
data class Organization (

    @Id @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    var id: Long? = null,

    /**
     * Is this organization subsidiary to a larger one?
     */
    var parent: Organization? = null,

    var name: String? = null,

    var description: String? = null

)
