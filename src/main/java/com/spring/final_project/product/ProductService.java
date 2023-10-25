package com.spring.final_project.product;

import java.util.List;
import java.util.Map;

public interface ProductService {

	public int insert(ProductDomain product);
	public int update(ProductDomain product);

	public int delete(ProductDomain product);

	public List<ProductDomain> findByCategory();

	public ProductDomain findByProductNum(int productNum);
	public ProductDomain forPayByProductNum(int productNum);

	public String getTitleByProductNum(int productNum);
	public List<ProductDomain> findByHostNum(int hostNum);

	public int findProductNum(int hostNum);

	public int countByHostNum(int hostNum);

	public int countInThisMonth(Map<String, Object> map);


	public int viewCountUp(int productNum);



	public List<ProductDomain> findPopular();

	public List<ProductDomain> findNew();

	public List<ProductDomain> findForHostInfo(int hostNum);

	public List<ProductDomain> findPerCategory(String firstCategoryName);

	public ProductDomain productSet(ProductDomain product, int hostNum, String addressDetail, int SecondCategoryNum);

	List<Map<String, Object>>  setProductPack(List<ProductDomain> products);
}
