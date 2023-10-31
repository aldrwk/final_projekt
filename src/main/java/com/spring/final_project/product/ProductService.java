package com.spring.final_project.product;

import com.spring.final_project.host.HostDomain;

import java.util.List;
import java.util.Map;

public interface ProductService {

	public int insert(ProductDomain product, HostDomain host, String events, String options);
	public int update(ProductDomain product, HostDomain host, String events, String options);

	public int delete(ProductDomain product);

	public List<ProductDomain> findByCategory();

	public ProductDomain findByProductNum(int productNum);

	public List<ProductIncludingOptionVo> unitedFindByProductNum(int productNum);

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

	public List<ProductDomain> findByRecentSearch(String search);

	public ProductDomain productSet(ProductDomain product, int hostNum, String addressDetail, int SecondCategoryNum);

	public List<Map<String, Object>>  setProductPack(List<ProductDomain> products);
}
