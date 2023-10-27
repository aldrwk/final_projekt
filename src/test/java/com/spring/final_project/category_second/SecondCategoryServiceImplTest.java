package com.spring.final_project.category_second;

import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.*;

class SecondCategoryServiceImplTest {

	@Autowired
	private SecondCategoryMapper secondCategoryMapper;

	List<SecondCategoryDomain> secondCategory = new ArrayList<>();

	@Test
	void setCategoryPerFirstCategory() {
		int length = 8;
		Map<String, List<String>> categoryMap = new HashMap<>();
		secondCategory = secondCategoryMapper.findAll();
		for (SecondCategoryDomain category : secondCategory) {
			for (int i = 0; i < length; i++) {
				List<String> categoryList = new ArrayList<>();
				if (category.getFirstCateNum() == i) {
					categoryList.add(category.getSecCateName());
				}
				categoryMap.put(String.valueOf(i), categoryList);
			}
		}

		System.out.println(categoryMap.get("1"));
	}
}