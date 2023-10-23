package com.spring.final_project.product;

import java.util.List;
import java.util.Map;

public interface productService {

	public int insert(productDomain product);
	public int update(productDomain product);

	public int delete(productDomain product);

	public List<productDomain> findByCategory();

	public productDomain findByProductNum(int productNum);

	public List<productDomain> findByHostNum(int hostNum);

	public int findProductNum(int hostNum);

	public int countInThisMonth(Map<String, Object> map);

	public productDomain productSet(productDomain product, int hostNum, String addressDetail, int SecondCategoryNum);

	public int viewCountUp(int productNum);



	public List<productDomain> findPopular();

	public List<productDomain> findNew();

	List<Map<String, Object>>  setProductPack(List<productDomain> products);
}
