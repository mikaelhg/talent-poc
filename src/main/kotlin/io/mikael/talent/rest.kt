package io.mikael.talent

import io.mikael.talent.model.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

/*
 * These interfaces will be magically transformed into fully functional REST endpoints. Maaaagic.
 */

@RepositoryRestResource
interface ProjectRepository : JpaRepository<Project, Long>

@RepositoryRestResource(path = "people", collectionResourceRel = "people")
interface PersonRepository : JpaRepository<Person, Long>

@RepositoryRestResource
interface SkillRepository : JpaRepository<Skill, Long>
