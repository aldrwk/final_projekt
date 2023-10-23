package com.spring.final_project.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.*;

import static com.spring.final_project.util.dateService.LocalToDayTime;

@Service
public class productServiceImpl implements productService {

	private productMapper productMapper;
	private productOptionService productOptionService;

	@Autowired
	public productServiceImpl(com.spring.final_project.product.productMapper productMapper,productOptionService productOptionService) {
		this.productMapper = productMapper;
		this.productOptionService = productOptionService;
	}

	@Override
	@Transactional
	public int insert(productDomain product) {
		return productMapper.insert(product);
	}

	@Override
	@Transactional
	public int update(productDomain product) {
		return productMapper.update(product);
	}

	@Override
	@Transactional
	public int delete(productDomain product) {
		return productMapper.delete(product);
	}

	@Override
	@Transactional
	public List<productDomain> findByCategory() {
		return productMapper.findByCategory();
	}

	@Override
	public productDomain findByProductNum(int productNum) {
		return productMapper.findByProductNum(productNum);
	}

	@Override
	@Transactional
	public int findProductNum(int hostNum) {
		return productMapper.findProductNum(hostNum);
	}

	@Override
	public List<productDomain> findByHostNum(int hostNum) {
		return productMapper.findByHostNum(hostNum);
	}

	@Override
	@Transactional
	public int countInThisMonth(Map<String, Object> map) {
		return productMapper.countInThisMonth(map);
	}

	@Override
	@Transactional
	public int viewCountUp(int productNum) {
		return productMapper.viewCountUp(productNum);
	}

	@Override
	@Transactional
	public List<productDomain> findPopular() {
		return productMapper.findPopular();
	}

	@Override
	@Transactional
	public List<productDomain> findNew() {
		return productMapper.findNew();
	}

	@Override
	public productDomain productSet(productDomain product, int hostNum, String addressDetail, int SecondCategoryNum) {
		String[] addressArray = product.getAddress().split(" ");
		product.setHostNum(hostNum);
		product.setAddressDetail(addressDetail);
		product.setArea(addressArray[0]);
		product.setAreaDetail(addressArray[1]);
		product.setSecCateNum(SecondCategoryNum);
		product.setProducRegitDate(LocalToDayTime());
		product.setProducUpdateDate(LocalToDayTime());
		return product;
	}

	@Override
	public List<Map<String, Object>>  setProductPack(List<productDomain> products) {
		List<Map<String, Object>> productpacks = new ArrayList<>();
		for (productDomain product : products) {
			Map<String, Object> productpack = new HashMap<>();
			int productNum = product.getProducNum();
			productOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
			Locale locale = new Locale("ko", "KR"); // 한국 로케일 (한국어, 대한민국)
			NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
			String newPrice = String.valueOf(numberFormat.format(Integer.parseInt(productOption.getPrice())));
			productOption.setPrice(newPrice);
			productpack.put("product", product);
			productpack.put("productoption", productOption);
			productpacks.add(productpack);
		}
		return productpacks;
	}
}
