package com.spring.final_project.category_second.mapper;

import com.spring.final_project.category_second.SecondCategoryDomain;
import com.spring.final_project.category_second.UnitedCategoryVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface SecondCategoryReadMapper {

	public SecondCategoryDomain findById(int id);
	public List<SecondCategoryDomain> findAll();
	public List<SecondCategoryDomain> findByFirstCategory(int num);

	public int findByName(Map<String, Object> data);

	public String findCategoryName(int productNum);

	public UnitedCategoryVo findCategorysName(int productNum);

}
