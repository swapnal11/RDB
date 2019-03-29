package com.capco.resource.repository;

import java.util.List;

import org.hibernate.annotations.Cache;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.capco.resource.model.FilterResult;
import com.capco.resource.model.Skill;
import com.capco.resource.model.UserInfo;

@Repository

public interface UserRepo extends JpaRepository<UserInfo, Long> {
	
	/*@Query(value="update skills s, employee_info e SET s.skill= :skill,s.skill_experience=:skill_experience where s.id = :id and e.emp_id=s.emp_id")
	Object updadteSkill(@Param("skill") String skill,@Param("skill_experience") int skill_experience,@Param("id")int id);
*/	
	UserInfo findByEmpId(int i);
	
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill) ,e.status status from employee_info e, skills s where( e.emp_id=s.emp_id and flag=1) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findAllUser();

	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.status status,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill) from employee_info e, skills s where (e.emp_id= :emp_id and flag=1) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findById(@Param("emp_id")int emp_id);
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.status status,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill) from employee_info e, skills s where (e.emp_id=s.emp_id and flag =1) and (e.emp_id= :emp_id or e.employee_email = :employee_email) group by s.emp_id",nativeQuery= true)
	List<Object[]> findByEmpIdOrEmployeeEmail(@Param("emp_id") int emp_id,@Param("employee_email") String employee_email);
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.status status,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill) from employee_info e, skills s where (e.emp_id=s.emp_id and flag=1) and (e.emp_id= :emp_id and e.employee_email = :employee_email) group by s.emp_id",nativeQuery= true)
	List<Object[]> findByEmpIdAndEmployeeEmail(@Param("emp_id") int emp_id,@Param("employee_email") String employee_email);
	
	
	
	@Query(value="select *  from employee_info e where (e.emp_id= :emp_id or e.employee_email = :employee_email) and flag=1",nativeQuery= true)
	UserInfo findByEmpIdOrEmail(@Param("emp_id") int emp_id, @Param("employee_email") String employee_email);
	
	
	@Query(value="select *  from employee_info e where (e.emp_id= :emp_id and e.employee_email = :employee_email) and flag=1",nativeQuery= true)
	UserInfo findByEmpIdAndEmail(@Param("emp_id") int emp_id,@Param("employee_email") String employee_email);
	
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill) , e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years = :experience_years and e.status in(:status) )) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findByExperienceYearsAndStatusIn(@Param("experience_years") int experience_years,@Param("status") List<String> status);
	

	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( e.experience_years = :experience_years) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findByExperienceYearsEqualTo(@Param("experience_years") int experience_years);
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( e.experience_years < :experience_years) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findByExperienceYearsLessThan(@Param("experience_years") int experience_years);

	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( e.experience_years > :experience_years) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findByExperienceYearsGreaterThan(@Param("experience_years") int experience_years);

	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years = :experience_years  and s.skill in(:skill))) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInAndExperienceYears(@Param("skill") List<String> skill, @Param("experience_years") int experience_years);
	

	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.status in(:status)  and s.skill in(:skill))) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInAndStatusIn(@Param("skill") List<String> skill, @Param("status") List<String> status);
	

	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years = :experience_years and e.status in(:status)  and s.skill in(:skill)))group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInOrExperienceYearsOrStatusIn(@Param("skill") List<String> skill, @Param("experience_years") int experience_years,@Param("status") List<String> status);
	
	
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years < :experience_years and e.status in(:status) )) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findByExperienceYearsLessThanAndStatusIn(@Param("experience_years") int experience_years,@Param("status") List<String> status);
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years < :experience_years  and s.skill in(:skill))) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInAndExperienceYearsLessThan(@Param("skill") List<String> skill, @Param("experience_years") int experience_years);
	

	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.status in(:status)  and s.skill in(:skill))) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInAndStatusInLessThan(@Param("skill") List<String> skill, @Param("status") List<String> status);
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years < :experience_years and e.status in(:status)  and s.skill in(:skill)) )group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInOrExperienceYearsLessThanOrStatusIn(@Param("skill") List<String> skill, @Param("experience_years") int experience_years,@Param("status") List<String> status);
	

	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years > :experience_years and e.status in(:status) )) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findByExperienceYearsGreaterThanAndStatusIn(@Param("experience_years") int experience_years,@Param("status") List<String> status);
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years > :experience_years  and s.skill in(:skill))) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInAndExperienceYearsGreaterThan(@Param("skill") List<String> skill, @Param("experience_years") int experience_years);
	

	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.status in(:status)  and s.skill in(:skill))) group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInAndStatusInGreaterThan(@Param("skill") List<String> skill, @Param("status") List<String> status);
	
	
	
	@Query(value="select distinct e.emp_id empId ,e.employee_name employeeName,e.experience_years experienceYears ,GROUP_CONCAT(distinct s.skill), e.status status from employee_info e, skills s where  (e.emp_id=s.emp_id and flag=1) and ( (e.experience_years > :experience_years and e.status in(:status)  and s.skill in(:skill)))group by s.emp_Id",nativeQuery= true)
	List<Object[]> findBySkillsSkillNameInOrExperienceYearsGreaterThanOrStatusIn(@Param("skill") List<String> skill, @Param("experience_years") int experience_years,@Param("status") List<String> status);
	
}
