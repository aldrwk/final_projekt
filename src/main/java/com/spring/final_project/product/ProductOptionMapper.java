package com.spring.final_project.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductOptionMapper {

	public int insert(ProductOptionDomain option);

	public ProductOptionDomain findByProduct(int num);

	public int update(ProductOptionDomain option);

	public int delete(ProductOptionDomain option);

	public ProductOptionDomain OneOptionByProduct(int productNum);
	public List<ProductOptionDomain> optionsByDay(int productNum);

	public List<ProductOptionDomain> optionsByProduct(int productNum);
	public ProductOptionDomain optionsById(int optionId);
	public int getRestById(int optionId);
	public int restDown(Map<String, Object> restDownMap);




}
