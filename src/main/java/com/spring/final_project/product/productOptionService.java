package com.spring.final_project.product;

import java.util.List;

public interface productOptionService {

	public int insert(productOptionDomain option);

	public productOptionDomain findByProduct(int num);

	public int update(productOptionDomain option);

	public int delete(productOptionDomain option);

}
