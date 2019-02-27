package com.capco.resource.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capco.resource.model.Skill;
import com.capco.resource.model.UserInfo;


@Repository
public interface SkillRepo extends JpaRepository<Skill, Long> {


}
