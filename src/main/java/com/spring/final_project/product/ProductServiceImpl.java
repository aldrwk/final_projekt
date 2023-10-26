package com.spring.final_project.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.*;

import static com.spring.final_project.util.DateService.LocalToDayTime;

@Service
public class ProductServiceImpl implements ProductService {

	private ProductMapper productMapper;
	private ProductOptionService productOptionService;

	@Autowired
	public ProductServiceImpl(ProductMapper productMapper, ProductOptionService productOptionService) {
		this.productMapper = productMapper;
		this.productOptionService = productOptionService;
	}

	@Override
	@Transactional
	public int insert(ProductDomain product) {
		return productMapper.insert(product);
	}

	@Override
	@Transactional
	public int update(ProductDomain product) {
		return productMapper.update(product);
	}

	@Override
	@Transactional
	public int delete(ProductDomain product) {
		return productMapper.delete(product);
	}

	@Override
	@Transactional
	public List<ProductDomain> findByCategory() {
		return productMapper.findByCategory();
	}

	@Override
	@Transactional
	public ProductDomain findByProductNum(int productNum) {
		return productMapper.findByProductNum(productNum);
	}
	@Override
	@Transactional
	public ProductDomain forPayByProductNum(int productNum) {
		return productMapper.forPayByProductNum(productNum);
	}

	@Override
	@Transactional
	public int findProductNum(int hostNum) {
		return productMapper.findProductNum(hostNum);
	}

	@Override
	public String getTitleByProductNum(int productNum) {
		return productMapper.getTitleByProductNum(productNum);
	}

	@Override
	@Transactional
	public List<ProductDomain> findByHostNum(int hostNum) {
		return productMapper.findByHostNum(hostNum);
	}

	@Override
	@Transactional
	public int countByHostNum(int hostNum) {
		return productMapper.countByHostNum(hostNum);
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
	public List<ProductDomain> findPopular() {
		return productMapper.findPopular();
	}

	@Override
	@Transactional
	public List<ProductDomain> findNew() {
		return productMapper.findNew();
	}

	@Override
	@Transactional
	public List<ProductDomain> findForHostInfo(int hostNum) {
		return productMapper.findForHostInfo(hostNum);
	}

	@Override
	@Transactional
	public List<ProductDomain> findPerCategory(String firstCategoryName) {
		return productMapper.findPerCategory(firstCategoryName);
	}

	@Override
	public List<ProductDomain> findByRecentSearch(String search) {
		return productMapper.findByRecentSearch(search);
	}

	@Override
	public ProductDomain productSet(ProductDomain product, int hostNum, String addressDetail, int SecondCategoryNum) {
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
	public List<Map<String, Object>>  setProductPack(List<ProductDomain> products) {
		List<Map<String, Object>> productpacks = new ArrayList<>();
		for (ProductDomain product : products) {
			Map<String, Object> productpack = new HashMap<>();
			int productNum = product.getProducNum();
			ProductOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
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
