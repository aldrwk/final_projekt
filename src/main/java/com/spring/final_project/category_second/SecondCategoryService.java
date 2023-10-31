package com.spring.final_project.category_second;

import com.spring.final_project.category_first.FirstCategoryDomain;

import java.util.List;
import java.util.Map;

public interface SecondCategoryService {

	public SecondCategoryDomain findById(int id);
	public List<SecondCategoryDomain> findAll();

	public List<SecondCategoryDomain> findByFirstCategory(int num);

	public int findByName(Map<String, Object> data);

	public String findCategoryName(int productNum);

	public UnitedCategoryVo findCategorysName(int productNum);

	public Map<String, List<String>> setCategoryPerFirstCategory(List<FirstCategoryDomain> firtCategory);

}
