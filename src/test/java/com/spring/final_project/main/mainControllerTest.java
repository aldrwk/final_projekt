package com.spring.final_project.main;

import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.product.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class mainControllerTest {

	private FirstCategoryService firstCategoryService;
	private ProductService productService;
	private ProductOptionService productOptionService;

	public mainControllerTest(FirstCategoryService firstCategoryService, ProductService productService, ProductOptionService productOptionService) {
		this.firstCategoryService = firstCategoryService;
		this.productService = productService;
		this.productOptionService = productOptionService;
	}
	@Test
	void root() {
		List<ProductDomain> products = productService.findPopular();
		List<ProductOptionDomain> productsOption = new ArrayList<>();
		for (ProductDomain product : products) {
			int productNum = product.getProducNum();
			ProductOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
			productsOption.add(productOption);
		}
		System.out.println(products);
		System.out.println(productsOption);

	}
}