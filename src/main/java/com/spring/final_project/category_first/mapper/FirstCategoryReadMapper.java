package com.spring.final_project.category_first.mapper;

import com.spring.final_project.category_first.FirstCategoryDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FirstCategoryReadMapper {

	public int findByName(String firstCateName);

	public List<FirstCategoryDomain> findAll();

	public String findCategoryName(int productNum);



}
