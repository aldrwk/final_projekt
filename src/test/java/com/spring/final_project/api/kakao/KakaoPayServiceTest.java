package com.spring.final_project.api.kakao;

import com.spring.final_project.payment.PaymentService;
import com.spring.final_project.product.ProductOptionMapper;
import com.spring.final_project.product.ProductOptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class KakaoPayServiceTest {

	private ProductOptionService productOptionService;

	private PaymentService paymentService;

	@Autowired
	public KakaoPayServiceTest(ProductOptionService productOptionService, PaymentService paymentService) {
		this.productOptionService = productOptionService;
		this.paymentService = paymentService;
	}


	@Test
	void 동시성테스트() {

		int optionId = 208;
		String quantity = "1";
		AtomicInteger rest = new AtomicInteger(10);

		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executorService.execute(() -> {
				System.out.println("동시성 테스트");
				ConcurrencyTest(optionId, quantity, rest, productOptionService);
			});
		}

	}

	void ConcurrencyTest(int optionId, String quantity, AtomicInteger rest, ProductOptionService productOptionService) {
		int result = productOptionService.restCheck(optionId, quantity);
		System.out.println(result);


//		int restCheckResult = productOptionService.restCheck(optionId, quantity);
//		System.out.println("testtest");
//		int intQuantity = Integer.parseInt(quantity);
//		System.out.println("재고 체크 : " + restCheckResult);
//		if (restCheckResult > 0) {
//			Map<String, Object> restDownMap = new HashMap<>();
//			restDownMap.put("optionId", optionId);
//			restDownMap.put("quantity", intQuantity);
//			if (productOptionService.restDown(restDownMap) == 1) {
//				rest.set(rest.get() - 1);
//				System.out.println("남은 재고 : " + rest);
//			}
//		}
	}
}



