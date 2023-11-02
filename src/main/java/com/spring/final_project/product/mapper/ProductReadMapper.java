package com.spring.final_project.product.mapper;


import com.spring.final_project.product.ProductDomain;
import com.spring.final_project.product.ProductIncludingOptionVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductReadMapper {

		public List<ProductDomain> findByCategory();

	public ProductDomain findByProductNum(int productNum);
	public ProductDomain forPayByProductNum(int productNum);
	public String getTitleByProductNum(int productNum);

	public int findProductNum(int hostNum);

	public List<ProductDomain> findByHostNum(int hostNum);

	public List<ProductIncludingOptionVo> unitedFindByProductNum(int hostNum);

	public int countByHostNum(int hostNum);
	public int countInThisMonth(Map<String, Object> map);

	public List<ProductDomain> findPopular();
	public List<ProductDomain> findNew();

	public List<ProductDomain> findForHostInfo(int hostNum);
	public List<ProductDomain> findPerCategory(String firstCategoryName);
	public List<ProductDomain> findByRecentSearch(String search);









}
