package com.spring.final_project.category_second;

import com.spring.final_project.category_first.FirstCategoryDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SecondCategoryServiceImpl implements SecondCategoryService {

	SecondCategoryMapper secondCategoryMapper;

	@Autowired
	public SecondCategoryServiceImpl(SecondCategoryMapper secondCategoryMapper) {
		this.secondCategoryMapper = secondCategoryMapper;
	}

	@Override
	@Transactional
	public SecondCategoryDomain findById(int id) {
		return secondCategoryMapper.findById(id);
	}

	@Override
	@Transactional
	public List<SecondCategoryDomain> findAll() {
		return secondCategoryMapper.findAll();
	}

	@Override
	@Transactional
	public List<SecondCategoryDomain> findByFirstCategory(int num) {
		return secondCategoryMapper.findByFirstCategory(num);
	}

	@Override
	@Transactional
	public int findByName(Map<String, Object> data) {
		return secondCategoryMapper.findByName(data);
	}

	@Override
	public String findCategoryName(int productNum) {
		return secondCategoryMapper.findCategoryName(productNum);
	}

	@Override
	@Transactional
	public Map<String, List<String>> setCategoryPerFirstCategory(List<FirstCategoryDomain> firtCategory) {
		Map<String, List<String>> categoryMap = new HashMap<>();
		List<SecondCategoryDomain> secondCategory = secondCategoryMapper.findAll();
		int j = 0;
		while (j < firtCategory.size()) {
			List<String> categoryList = new ArrayList<>();
			for (SecondCategoryDomain category : secondCategory) {
				if (category.getFirstCateNum() == j + 1) {
					categoryList.add(category.getSecCateName());
				}
				categoryMap.put(firtCategory.get(j).getFirstCateName(), categoryList);
			}
			j++;
		}
		return categoryMap;
	}
}
