package com.spring.final_project.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface productOptionMapper {

	public int insert(productOptionDomain option);

	public productOptionDomain findByProduct(int num);

	public int update(productOptionDomain option);

	public int delete(productOptionDomain option);

	public productOptionDomain OneOptionByProduct(int productNum);

	public List<productOptionDomain> optionsByProduct(int productNum);
	public productOptionDomain optionsById(int optionId);


}