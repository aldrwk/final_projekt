package com.spring.final_project.product.mapper;

import com.spring.final_project.product.ProductOptionDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductOptionReadMapper {

	public ProductOptionDomain findByProduct(int num);

	public ProductOptionDomain OneOptionByProduct(int productNum);
	public List<ProductOptionDomain> optionsByDay(int productNum);
	public List<ProductOptionDomain> optionsByProduct(int productNum);
	public ProductOptionDomain optionsById(int optionId);
	public Integer getRestById(int optionId);

	public Integer findByReservationId(int reservation_id);




}
