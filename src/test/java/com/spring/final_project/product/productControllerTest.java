package com.spring.final_project.product;

import com.spring.final_project.host.hostDomain;
import com.spring.final_project.reservation_dates.reservationDatesDomain;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.spring.final_project.util.dateService.LocalToDayTime;
import static com.spring.final_project.util.dateService.toDay;
import static com.spring.final_project.util.fileUploadService.imageUpload;
import static com.spring.final_project.util.schaduleService.DatesRetouch;
import static org.junit.jupiter.api.Assertions.*;

class productControllerTest {

	@Test
	void productRegist() {
			Locale locale = new Locale("ko", "KR"); // 한국 로케일 (한국어, 대한민국)
			NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
			String newPrice = String.valueOf(numberFormat.format(Integer.parseInt("50000")));
		System.out.println(newPrice);

	}
}