package com.spring.final_project.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class productOptionServiceImpl implements productOptionService {

	productOptionMapper productOptionMapper;

	@Autowired
	public productOptionServiceImpl(productOptionMapper productOptionMapper) {
		this.productOptionMapper = productOptionMapper;
	}

	@Override
	@Transactional
	public int insert(productOptionDomain option) {
		return productOptionMapper.insert(option);
	}

	@Override
	@Transactional
	public productOptionDomain findByProduct(int num) {
		return productOptionMapper.findByProduct(num);
	}

	@Override
	@Transactional
	public int update(productOptionDomain option) {
		return productOptionMapper.update(option);
	}

	@Override
	@Transactional
	public int delete(productOptionDomain option) {
		return productOptionMapper.delete(option);
	}

	@Override
	@Transactional
	public productOptionDomain OneOptionByProduct(int productNum) {
		return productOptionMapper.OneOptionByProduct(productNum);
	}

	@Override
	@Transactional
	public productOptionDomain optionsByProduct(int productNum) {
		return productOptionMapper.optionsByProduct(productNum);
	}
}
