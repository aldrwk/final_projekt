package com.spring.final_project.product;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class productServiceImpl implements productService {

	private productMapper productMapper;

	public productServiceImpl(com.spring.final_project.product.productMapper productMapper) {
		this.productMapper = productMapper;
	}

	@Override
	public int insert(productDomain product) {
		return productMapper.insert(product);
	}

	@Override
	public int update(productDomain product) {
		return productMapper.update(product);
	}

	@Override
	public int delete(productDomain product) {
		return productMapper.delete(product);
	}

	@Override
	public List<productDomain> findByCategory() {
		return productMapper.findByCategory();
	}
}
