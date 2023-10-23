package com.spring.final_project.main;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.product.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Controller
public class mainController {

	private FirstCategoryService firstCategoryService;
	private productService productService;

	@Autowired
	public mainController(FirstCategoryService firstCategoryService, productService productService) {
		this.firstCategoryService = firstCategoryService;
		this.productService = productService;
	}

	@GetMapping("/")
	public String root(Model model) {
		CompletableFuture<List<FirstCategoryDomain>> firstCategorys = CompletableFuture.supplyAsync(() ->
				firstCategoryService.findAll());

		CompletableFuture<List<Map<String, Object>>> products = CompletableFuture.supplyAsync(() ->
				productService.setProductPack(productService.findPopular()));

		CompletableFuture<List<Map<String, Object>>> newProducts = CompletableFuture.supplyAsync(() ->
				productService.setProductPack(productService.findNew()));
		CompletableFuture<Void> combinedFutureAllof = CompletableFuture.allOf(firstCategorys, products, newProducts);
		combinedFutureAllof.thenRun(() -> {
			try {
				model.addAttribute("categorys", firstCategorys.get());
				model.addAttribute("productpacks", products.get());
				model.addAttribute("newproductpacks", newProducts.get());
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} catch (ExecutionException e) {
				throw new RuntimeException(e);
			}
		}).join();
//		List<FirstCategoryDomain> firstCategory = firstCategoryService.findAll();
//		List<productDomain> products = productService.findPopular();
//		List<productDomain> newProducts = productService.findNew();
//		List<Map<String, Object>> productpacks = new ArrayList<>();
//				model.addAttribute("categorys", firstCategory);
//				model.addAttribute("productpacks", productService.setProductpack(products));
//				model.addAttribute("newproductpacks", productService.setProductpack(newProducts));
		return "main";
	}

	@GetMapping("/calendar")
	public String calender() {
		return "calendar";
	}

	@GetMapping("/test")
	public String test(Model model) {

		return "test";
	}

	@GetMapping("/write")
	public String writeTest() {
		return "/product/write";
	}

	@PostMapping("/error")
	public String error() {
		return "error/403";
	}
}
