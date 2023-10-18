package com.spring.final_project.product;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface productMapper {

	public int insert(productDomain product);
	public int update(productDomain product);

	public int delete(productDomain product);

	public List<productDomain> findByCategory();


}
