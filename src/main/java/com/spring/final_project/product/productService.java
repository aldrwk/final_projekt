package com.spring.final_project.product;

import java.util.List;

public interface productService {

	public int insert(productDomain product);
	public int update(productDomain product);

	public int delete(productDomain product);

	public List<productDomain> findByCategory();
}
