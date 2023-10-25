package com.spring.final_project.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ProductOptionServiceImpl implements ProductOptionService {

	ProductOptionMapper productOptionMapper;

	@Autowired
	public ProductOptionServiceImpl(ProductOptionMapper productOptionMapper) {
		this.productOptionMapper = productOptionMapper;
	}

	@Override
	@Transactional
	public int insert(ProductOptionDomain option) {
		return productOptionMapper.insert(option);
	}

	@Override
	public void listInsert(int productNum, int reservId,String options) {
		ObjectMapper objectMapper = new ObjectMapper();
		ProductOptionDomain[] optionArray;
		try {
			optionArray = objectMapper.readValue(options, ProductOptionDomain[].class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		// events 배열은 이제 Event 객체의 배열로 사용할 수 있습니다.
		for (ProductOptionDomain option : optionArray) {
			option.setReservationId(reservId);
			option.setRest(option.getValidPerson());
			option.setProducNum(productNum);
			if (1 == insert(option)) {
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
	public int delete(ProductOptionDomain option) {
		return productOptionMapper.delete(option);
	}

	@Override
	@Transactional
	public ProductOptionDomain OneOptionByProduct(int productNum) {
		return productOptionMapper.OneOptionByProduct(productNum);
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
	public int getRestById(int optionId) {
		return productOptionMapper.getRestById(optionId);
	}
	@Override
	@Transactional
	public int restDown(Map<String, Object> restDownMap) {
		return productOptionMapper.restDown(restDownMap);
	}

}

