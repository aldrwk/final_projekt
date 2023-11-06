package com.spring.final_project.category_second;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_second.mapper.SecondCategoryMapper;
import com.spring.final_project.category_second.mapper.SecondCategoryReadMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SecondCategoryServiceImpl implements SecondCategoryService {

	SecondCategoryReadMapper secondCategoryReadMapper;

	@Autowired
	public SecondCategoryServiceImpl(SecondCategoryReadMapper secondCategoryReadMapper) {
		this.secondCategoryReadMapper = secondCategoryReadMapper;
	}

	@Override
	@Transactional
	public SecondCategoryDomain findById(int id) {
		return secondCategoryReadMapper.findById(id);
	}

	@Override
	@Transactional
	public List<SecondCategoryDomain> findAll() {
		return secondCategoryReadMapper.findAll();
	}

	@Override
	@Transactional
	public List<SecondCategoryDomain> findByFirstCategory(int num) {
		return secondCategoryReadMapper.findByFirstCategory(num);
	}

	@Override
	@Transactional
	public int findByName(Map<String, Object> data) {
		return secondCategoryReadMapper.findByName(data);
	}

	@Override
	public String findCategoryName(int productNum) {
		return secondCategoryReadMapper.findCategoryName(productNum);
	}

	@Override
	public UnitedCategoryVo findCategorysName(int productNum) {
		return secondCategoryReadMapper.findCategorysName(productNum);
	}

	@Override
	@Transactional
	public Map<String, List<String>> setCategoryPerFirstCategory(List<FirstCategoryDomain> firtCategory) {
		Map<String, List<String>> categoryMap = new HashMap<>();
		List<SecondCategoryDomain> secondCategory = secondCategoryReadMapper.findAll();
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
