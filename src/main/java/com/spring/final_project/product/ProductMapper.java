package com.spring.final_project.product;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductMapper {

	public int insert(ProductDomain product);
	public int update(ProductDomain product);
	public int delete(ProductDomain product);
	public List<ProductDomain> findByCategory();

	public ProductDomain findByProductNum(int productNum);
	public ProductDomain forPayByProductNum(int productNum);
	public String getTitleByProductNum(int productNum);

	public int findProductNum(int hostNum);

	public List<ProductDomain> findByHostNum(int hostNum);

	public int countByHostNum(int hostNum);

	public int countInThisMonth(Map<String, Object> map);



	public int viewCountUp(int productNum);

	public List<ProductDomain> findPopular();
	public List<ProductDomain> findNew();

	public List<ProductDomain> findForHostInfo(int hostNum);
	public List<ProductDomain> findPerCategory(String firstCategoryName);









}
