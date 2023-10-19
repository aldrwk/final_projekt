package com.spring.final_project.product;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface productOptionMapper {

	public int insert(productOptionDomain option);

	public productOptionDomain findByProduct(int num);

	public int update(productOptionDomain option);

	public int delete(productOptionDomain option);
}
