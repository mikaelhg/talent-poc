package io.mikael.talent

import io.mikael.talent.model.Person
import io.mikael.talent.model.Project
import io.mikael.talent.model.Skill
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource
interface ProjectRepository : JpaRepository<Project, Long>

@RepositoryRestResource(path = "people", collectionResourceRel = "people")
interface PersonRepository : JpaRepository<Person, Long>

@RepositoryRestResource
interface SkillRepository : JpaRepository<Skill, Long>
