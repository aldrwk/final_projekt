package com.spring.final_project.product;

import java.util.List;
import java.util.Map;

public interface ProductOptionService {

	public int insert(ProductOptionDomain options);

	public void listInsert(int productNum, int reservationDateId, String options);

	public ProductOptionDomain findByProduct(int num);

	public int update(ProductOptionDomain option);

	public int restCheck(int optionId, String quantity);

	public int deleteByReservationId(int reservationId);

	public ProductOptionDomain OneOptionByProduct(int productNum);
	public List<ProductOptionDomain> optionsByDay(int productNum);

	public List<ProductOptionDomain> optionsByProduct(int productNum);

	public ProductOptionDomain optionsById(int optionId);
//	public Integer getRestById(int optionId, String quantity);
//	public int restDown(Map<String, Object> restDownMap);

	public Integer findByReservationId(int reservation_id);

}
