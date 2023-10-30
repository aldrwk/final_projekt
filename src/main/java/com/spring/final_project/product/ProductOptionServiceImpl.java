package com.spring.final_project.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProductOptionServiceImpl implements ProductOptionService {

	final static int SUCCESS = 1;
	final static int NO_REST = 0;
	final static int REST = 1;
	ProductOptionMapper productOptionMapper;

	@Autowired
	public ProductOptionServiceImpl(ProductOptionMapper productOptionMapper) {
		this.productOptionMapper = productOptionMapper;
	}

	public ProductOptionServiceImpl() {

	}

	@Override
	@Transactional
	public int insert(ProductOptionDomain option) {
		return productOptionMapper.insert(option);
	}

	@Override
	public void listInsert(int productNum, int reservationDateId, String options) {
		ObjectMapper objectMapper = new ObjectMapper();
		ProductOptionDomain[] optionArray;
		try {
			optionArray = objectMapper.readValue(options, ProductOptionDomain[].class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		// events 배열은 이제 Event 객체의 배열로 사용할 수 있습니다.
		for (ProductOptionDomain option : optionArray) {
			option.setReservationId(reservationDateId);
			option.setRest(option.getValidPerson());
			option.setProducNum(productNum);
			if (insert(option) == SUCCESS) {
				System.out.println("옵션 등록 완료!");

			}
		}
	}

	@Override
	@Transactional
	public ProductOptionDomain findByProduct(int num) {
		return productOptionMapper.findByProduct(num);
	}

	@Override
	@Transactional
	public int update(ProductOptionDomain option) {
		return productOptionMapper.update(option);
	}

	@Override
	@Transactional
	public int restCheck(int optionId, String quantity) {
		System.out.println(quantity);
		int rest = 0;

//		rest = getRestById(optionId, rest, quantity);
		rest = productOptionMapper.getRestById(optionId);
		System.out.println("testtest");
		int intQuantity = Integer.parseInt(quantity);
		Map<String, Object> restDownMap = new HashMap<>();
		restDownMap.put("optionId", optionId);
		restDownMap.put("quantity", intQuantity);
		if (productOptionMapper.restDown(restDownMap) == 1) {
			rest--;
			System.out.println("남은 재고 : " + rest);
		}

		return rest;
//		return rest - intQuantity >= 0 ? REST : NO_REST;
	}

	@Override
	public int deleteByReservationId(int reservationId) {
		return productOptionMapper.deleteByReservationId(reservationId);
	}

	@Override
	@Transactional
	public ProductOptionDomain OneOptionByProduct(int productNum) {
		return productOptionMapper.OneOptionByProduct(productNum);
	}

	@Override
	@Transactional
	public List<ProductOptionDomain> optionsByDay(int productNum) {
		return productOptionMapper.optionsByDay(productNum);
	}

	@Override
	@Transactional
	public List<ProductOptionDomain> optionsByProduct(int productNum) {
		return productOptionMapper.optionsByProduct(productNum);
	}

	@Override
	@Transactional
	public ProductOptionDomain optionsById(int optionId) {
		return productOptionMapper.optionsById(optionId);
	}


	@Override
	@Transactional
	public Integer getRestById(int optionId, int rest, String quantity) {
		productOptionMapper.getRestById(optionId);
		System.out.println("testtest");
		int intQuantity = Integer.parseInt(quantity);
		Map<String, Object> restDownMap = new HashMap<>();
		restDownMap.put("optionId", optionId);
		restDownMap.put("quantity", intQuantity);
		if (productOptionMapper.restDown(restDownMap) == 1) {
			rest--;
			System.out.println("남은 재고 : " + rest);
		}
		return rest;
	}

	@Override
	@Transactional
	public int restDown(Map<String, Object> restDownMap) {
		return productOptionMapper.restDown(restDownMap);
	}

	@Override
	@Transactional
	public Integer findByReservationId(int reservation_id) {
		return productOptionMapper.findByReservationId(reservation_id);
	}
}

