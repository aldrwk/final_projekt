//package com.spring.final_project.payment;
//
//import com.spring.final_project.product.*;
//import com.spring.final_project.reservation_dates.reservationDatesDomain;
//import com.spring.final_project.reservation_dates.reservationDatesService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//import javax.servlet.http.HttpSession;
//
//@Controller
//@RequestMapping(")
//public class paymentController {
//
//	private ProductService productService;
//	private ProductOptionService productOptionService;
//	private reservationDatesService reservationDatesService;
//
//
//	@Autowired
//	public paymentController(ProductService productService, ProductOptionService productOptionService, reservationDatesService reservationDatesService) {
//		this.productService = productService;
//		this.productOptionService = productOptionService;
//		this.reservationDatesService = reservationDatesService;
//	}
//
//	@PostMapping("/pay/pay-method")
//	public String payment(Model model, HttpSession session, String totalPrice, int reservId, int optionId, int productNum, int quantity) {
//		if (session.getAttribute("user_info") == null) {
//			return "redirect:/login?redirectPath=/product/"+productNum+"/participate";
//		}
//		ProductDomain product = productService.forPayByProductNum(productNum);
//		ProductOptionDomain productOptions = productOptionService.optionsById(optionId);
//		reservationDatesDomain reservationDate = reservationDatesService.findById(reservId);
//		session.setAttribute("product", product);
//		model.addAttribute("productNum", productNum);
//		model.addAttribute("quantity", quantity);
//		model.addAttribute("product", product);
//		model.addAttribute("totalPrice", totalPrice);
//		model.addAttribute("productoptions", productOptions);
//		model.addAttribute("optionId", optionId);
//		model.addAttribute("reservationDate", reservationDate);
//		return "/payment/pay-method";
//	}
//
//}
