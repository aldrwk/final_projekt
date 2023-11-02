package com.spring.final_project.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.final_project.product.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@CacheConfig(cacheNames = "layoutCaching")
public class ProductOptionServiceImpl implements ProductOptionService {

	final static int SUCCESS = 1;
	final static int NO_REST = 0;
	final static int REST = 1;
	ProductOptionWriteMapper productOptionWriteMapper;
	ProductOptionReadMapper productOptionReadMapper;

	@Autowired
	public ProductOptionServiceImpl(ProductOptionWriteMapper productOptionWriteMapper, ProductOptionReadMapper productOptionReadMapper) {
		this.productOptionWriteMapper = productOptionWriteMapper;
		this.productOptionReadMapper = productOptionReadMapper;
	}

	@Override
	@Transactional
	public int insert(ProductOptionDomain option) {
		return productOptionWriteMapper.insert(option);
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
	@Transactional(readOnly = true)
	public ProductOptionDomain findByProduct(int num) {
		return productOptionReadMapper.findByProduct(num);
	}

	@Override
	@Transactional
	public int update(ProductOptionDomain option) {
		return productOptionWriteMapper.update(option);
	}

	@Override
	@Transactional
	public int restCheck(int optionId, String quantity) {
		int intQuantity = Integer.parseInt(quantity);
		int rest = productOptionReadMapper.getRestById(optionId);
		System.out.println("현 재고 : " + rest);

		int restResult = rest - intQuantity;
		if (restResult >= NO_REST) {
			Map<String, Object> restDownMap = new HashMap<>();
			restDownMap.put("optionId", optionId);
			restDownMap.put("quantity", intQuantity);
			restDown(restDownMap);
		}
		return restResult;
	}

	@Override
	@Transactional
	public int deleteByReservationId(int reservationId) {
		return productOptionWriteMapper.deleteByReservationId(reservationId);
	}

	@Override
//	@Cacheable(key = "'OneOptionByProduct'")
	@Transactional(readOnly = true)
	public ProductOptionDomain OneOptionByProduct(int productNum) {
		return productOptionReadMapper.OneOptionByProduct(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductOptionDomain> optionsByDay(int productNum) {
		return productOptionReadMapper.optionsByDay(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductOptionDomain> optionsByProduct(int productNum) {
		return productOptionReadMapper.optionsByProduct(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductOptionDomain optionsById(int optionId) {
		return productOptionReadMapper.optionsById(optionId);
	}


//	@Override
//	@Transactional
//	public Integer getRestById(int optionId, String quantity) {
//		return productOptionMapper.getRestById(optionId);
//	}
//
	@Override
	@Transactional(readOnly = true)
	public int restDown(Map<String, Object> restDownMap) {
		return productOptionWriteMapper.restDown(restDownMap);
	}

	@Override
	@Transactional(readOnly = true)
	public Integer findByReservationId(int reservation_id) {
		return productOptionReadMapper.findByReservationId(reservation_id);
	}
}

