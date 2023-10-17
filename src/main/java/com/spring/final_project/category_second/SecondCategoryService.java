package com.spring.final_project.category_second;

import java.util.List;

public interface SecondCategoryService {

	public SecondCategoryDomain findById(int id);
	public List<SecondCategoryDomain> findAll();

	public List<SecondCategoryDomain> findByFirstCategory(int num);

}
