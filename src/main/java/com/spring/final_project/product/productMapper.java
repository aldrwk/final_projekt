package com.spring.final_project.product;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface productMapper {

	public int insert(productDomain product);
	public int update(productDomain product);
	public int delete(productDomain product);
	public List<productDomain> findByCategory();

	public productDomain findByProductNum(int productNum);

	public int findProductNum(int hostNum);

	public int countInThisMonth(Map<String, Object> map);

	public int viewCountUp(int productNum);

	public List<productDomain> findPopular();


}
