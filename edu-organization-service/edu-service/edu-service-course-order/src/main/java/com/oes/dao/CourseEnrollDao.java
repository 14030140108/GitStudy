package com.oes.dao;


import com.oes.dto.EnrollCoursesDTO;
import com.oes.entity.CourseEnroll;
import com.oes.model.dto.CourseStudentDTO;
import com.oes.vo.EnrollCoursesVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CourseEnrollDao {

    List<EnrollCoursesVO> getUncheckedEnrollCourses(long orgId);

    CourseEnroll queryByUserIdAndCourseId(@Param("userId") long userId, @Param("courseId") long courseId);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseEnroll record);

    List<EnrollCoursesDTO> getEnrollCoursesInfo(@Param("org_id") long org_id, @Param("stu_id") long stu_id, @Param("status") int status);

    int insertSelective(CourseEnroll record);

    CourseEnroll selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseEnroll record);

    int updateByPrimaryKey(CourseEnroll record);

    int deleteByStuIdAndCourseId(@Param("stuId") long stuId, @Param("courseId") int courseId);

    //根据courseId 得到选修学生名单
    List<CourseStudentDTO> getStudentInfoByCourseId(Integer courseId);

    //根据courseId 得到试听学生名单
    List<CourseStudentDTO> getTryStudentInfoByCourseId(Integer courseId);
}