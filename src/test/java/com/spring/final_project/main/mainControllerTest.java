package com.spring.final_project.main;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.product.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class mainControllerTest {

	private FirstCategoryService firstCategoryService;
	private com.spring.final_project.product.productService productService;
	private productOptionService productOptionService;

	public mainControllerTest(FirstCategoryService firstCategoryService, productService productService,productOptionService productOptionService) {
		this.firstCategoryService = firstCategoryService;
		this.productService = productService;
		this.productOptionService = productOptionService;
	}
	@Test
	void root() {
		List<productDomain> products = productService.findPopular();
		List<productOptionDomain> productsOption = new ArrayList<>();
		for (productDomain product : products) {
			int productNum = product.getProducNum();
			productOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
			productsOption.add(productOption);
		}
		System.out.println(products);
		System.out.println(productsOption);

	}
}