package com.spring.final_project.product.mapper;

import com.spring.final_project.product.ProductOptionDomain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductOptionWriteMapper {

	public int insert(ProductOptionDomain option);

	public int update(ProductOptionDomain option);

	public int deleteByReservationId(int reservationId);

	public int restDown(Map<String, Object> restDownMap);



}
