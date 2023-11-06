package com.spring.final_project.api.kakao;

import com.spring.final_project.payment.PaymentService;
import com.spring.final_project.product.ProductOptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.*;

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
	void 동시성테스트() throws InterruptedException {

		int optionId = 257;
		String quantity = "1";
		CountDownLatch latch = new CountDownLatch(15);
		ExecutorService executorService = Executors.newFixedThreadPool(15);
		for (int i = 1; i <= 15; i++) {
			int finalI = i;
			executorService.execute(() -> {
				ConcurrencyTest(finalI, optionId, quantity, productOptionService);
				latch.countDown();
			});
		}
		latch.await();
	}

	void ConcurrencyTest(int finalI, int optionId, String quantity, ProductOptionService productOptionService) {
		int restCheckResult = productOptionService.restCheck(optionId, quantity);
		if (restCheckResult == -1) {
			System.out.println(finalI + "번의  요청전 재고 소진");
		} else {
			System.out.println(finalI + "번의 요청후 재고량 : " + restCheckResult);
		}
	}
}



