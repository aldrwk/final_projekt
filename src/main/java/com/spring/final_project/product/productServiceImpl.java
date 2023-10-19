package com.spring.final_project.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static com.spring.final_project.util.dateService.LocalToDayTime;

@Service
public class productServiceImpl implements productService {

	private productMapper productMapper;

	@Autowired
	public productServiceImpl(com.spring.final_project.product.productMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	@Transactional
	public int insert(productDomain product) {
		return productMapper.insert(product);
	}

	@Override
	@Transactional
	public int update(productDomain product) {
		return productMapper.update(product);
	}

	@Override
	@Transactional
	public int delete(productDomain product) {
		return productMapper.delete(product);
	}

	@Override
	@Transactional
	public List<productDomain> findByCategory() {
		return productMapper.findByCategory();
	}

	@Override
	@Transactional
	public int findProductNum(int hostNum) {
		return productMapper.findProductNum(hostNum);
	}

	@Override
	@Transactional
	public int countInThisMonth(Map<String, Object> map) {
		return productMapper.countInThisMonth(map);
	}

	@Override
	public productDomain productSet(productDomain product, int hostNum, String addressDetail, int SecondCategoryNum) {
		product.setHostNum(hostNum);
		product.setAddressDetail(addressDetail);
		product.setSecCateNum(SecondCategoryNum);
		product.setProducRegitDate(LocalToDayTime());
		product.setProducUpdateDate(LocalToDayTime());
		return product;
	}
}
