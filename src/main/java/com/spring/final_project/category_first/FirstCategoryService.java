package com.spring.final_project.category_first;

import java.util.List;

public interface FirstCategoryService {

	public int findByName(String firstCateName);
	public List<FirstCategoryDomain> findAll();
}
