package com.spring.final_project.product;

import java.util.List;
import java.util.Map;

public interface ProductOptionService {

	public int insert(ProductOptionDomain options);

	public void listInsert(int productNum, int reservId, String options);

	public ProductOptionDomain findByProduct(int num);

	public int update(ProductOptionDomain option);

	public int delete(ProductOptionDomain option);

	public ProductOptionDomain OneOptionByProduct(int productNum);

	public List<ProductOptionDomain> optionsByProduct(int productNum);

	public ProductOptionDomain optionsById(int optionId);
	public int getRestById(int optionId);
	public int restDown(Map<String, Object> restDownMap);


}
