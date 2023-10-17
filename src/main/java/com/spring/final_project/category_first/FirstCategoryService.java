package com.spring.final_project.category_first;

import java.util.List;

public interface FirstCategoryService {

	public FirstCategoryDomain findByfindByName(String name);
	public List<FirstCategoryDomain> findAll();
}
