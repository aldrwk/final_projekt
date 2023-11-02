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

		int optionId = 208;
		String quantity = "1";
		CountDownLatch latch = new CountDownLatch(10);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			int finalI = i;
			executorService.execute(() -> {
				System.out.println("동시성 테스트" + finalI);
				ConcurrencyTest(finalI,optionId, quantity, productOptionService);
				latch.countDown();
			});
		}
		latch.await();
	}

	void ConcurrencyTest(int finalI,int optionId, String quantity, ProductOptionService productOptionService) {
		int restCheckResult = productOptionService.restCheck(optionId, quantity);
		System.out.println(finalI+"번의 재고 - 요청 수량 : "+ restCheckResult);
	}
}



