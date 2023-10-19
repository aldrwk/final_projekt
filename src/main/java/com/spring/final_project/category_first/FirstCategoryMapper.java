package com.spring.final_project.category_first;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FirstCategoryMapper {

	public int findByName(String firstCateName);
	public List<FirstCategoryDomain> findAll();



}
