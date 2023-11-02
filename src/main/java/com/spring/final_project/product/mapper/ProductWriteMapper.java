package com.spring.final_project.product.mapper;


import com.spring.final_project.product.ProductDomain;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductWriteMapper {

	public int insert(ProductDomain product);
	public int update(ProductDomain product);
	public int delete(ProductDomain product);
	public int viewCountUp(int productNum);}
