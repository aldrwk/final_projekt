package com.spring.final_project.product;

import com.spring.final_project.category_first.FirstCategoryDomain;
import com.spring.final_project.category_first.FirstCategoryService;
import com.spring.final_project.category_second.SecondCategoryDomain;
import com.spring.final_project.category_second.SecondCategoryService;
import com.spring.final_project.member.memberController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Transactional
public class productController {
	private static final Logger log = LoggerFactory.getLogger(memberController.class);


	FirstCategoryService firstCategoryService;
	SecondCategoryService secondCategoryService;

	public productController(FirstCategoryService firstCategoryService, SecondCategoryService secondCategoryService) {
		this.firstCategoryService = firstCategoryService;
		this.secondCategoryService = secondCategoryService;
	}

	@GetMapping("products/{productNum}")
	public String productList(@PathVariable("productNum") int productNum, Model model) {

		return "product/detail";
	}

	@GetMapping("product/{productNum}/participate")
	public String productCalender(@PathVariable("productNum") int productNum, Model model) {
		return "product/participate";
	}

	@GetMapping("/search")
	public String search(String search_data, Model model) {
		model.addAttribute("search_data", search_data);
		return "product/search";
	}

	@PostMapping("/product/regist")
	public String regist(Model model) {
		List<FirstCategoryDomain> firtCategory = firstCategoryService.findAll();
		model.addAttribute("firstcategory", firtCategory);
		return "/product/regist ::regist";
	}

	@GetMapping("/product/secondcategory")
	public String secondCategory(String name, Model model) {
		int firstCategoryNum = firstCategoryService.findByfindByName(name).getFirstCateNum();
		List<SecondCategoryDomain> secondCategory = secondCategoryService.findByFirstCategory(firstCategoryNum);

		model.addAttribute("secondCategory", secondCategory);
		String secondCate = "<li class=\"category categorys\" th:each=\"category: ${secondCategory}\">\n" +
				"<span th:text=\"${category.secCateName}\"></span>" +
				"</li>";
		return "/product/regist ::secondcategorys";
	}
}
