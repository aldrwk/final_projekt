package com.spring.final_project.category_second;

import java.util.List;
import java.util.Map;

public interface SecondCategoryService {

	public SecondCategoryDomain findById(int id);
	public List<SecondCategoryDomain> findAll();

	public List<SecondCategoryDomain> findByFirstCategory(int num);

	public int findByName(Map<String, Object> data);

	public String findCategoryName(int productNum);


}
