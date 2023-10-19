package com.spring.final_project.product;

import java.util.List;
import java.util.Map;

public interface productService {

	public int insert(productDomain product);
	public int update(productDomain product);

	public int delete(productDomain product);

	public List<productDomain> findByCategory();

	public int findProductNum(int hostNum);

	public int countInThisMonth(Map<String, Object> map);

	public productDomain productSet(productDomain product, int hostNum, String addressDetail, int SecondCategoryNum);
}
