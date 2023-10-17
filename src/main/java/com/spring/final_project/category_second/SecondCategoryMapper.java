package com.spring.final_project.category_second;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SecondCategoryMapper {

	public SecondCategoryDomain findById(int id);
	public List<SecondCategoryDomain> findAll();
	public List<SecondCategoryDomain> findByFirstCategory(int num);


}
