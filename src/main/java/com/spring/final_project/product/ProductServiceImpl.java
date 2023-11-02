package com.spring.final_project.product;

import com.spring.final_project.host.HostDomain;
import com.spring.final_project.member.MemberController;
import com.spring.final_project.product.mapper.*;
import com.spring.final_project.reservation_dates.ReservationDatesDomain;
import com.spring.final_project.reservation_dates.ReservationDatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.spring.final_project.util.DateService.LocalToDayTime;
import static com.spring.final_project.util.SchaduleService.DatesRetouch;

@Service
@CacheConfig(cacheNames = "layoutCaching")
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);


	private ProductWriteMapper productWriteMapper;
	private ProductReadMapper productReadMapper;
	private ProductOptionService productOptionService;

	private ReservationDatesService reservationDatesService;

	final static int SUCCESS = 1;
	final static int Fail = 0;
	final static int NOTHING = 0;

	@Autowired
	public ProductServiceImpl(ProductWriteMapper productWriteMapper,ProductReadMapper productReadMapper, ProductOptionService productOptionService, ReservationDatesService reservationDatesService) {
		this.productWriteMapper = productWriteMapper;
		this.productReadMapper = productReadMapper;
		this.productOptionService = productOptionService;
		this.reservationDatesService = reservationDatesService;
	}

	@Override
	@Transactional
	public int insert(ProductDomain product, HostDomain host, String events, String options) {
		int result = productWriteMapper.insert(product);
		int productNum = findProductNum(host.getHostNum());
		List<ReservationDatesDomain> dates = DatesRetouch(events);
		log.info(dates.toString());

		for (ReservationDatesDomain date : dates) {
			date.setProducNum(productNum);
			LocalDateTime closeDate = date.getReservDate().minus(1, ChronoUnit.DAYS).withHour(23).withMinute(59);
			date.setCloseDate(closeDate);
			date.setRegistDate(LocalToDayTime());
			date.setUpdateDate(LocalToDayTime());
			if (reservationDatesService.insert(date) == SUCCESS) {
				int reservationDateId = reservationDatesService.getId(productNum);
				productOptionService.listInsert(productNum, reservationDateId, options);
				log.info("등록");
			}
		}
		return result == SUCCESS ? SUCCESS : Fail;
	}

	@Override
	@Transactional
	public int update(ProductDomain product, HostDomain host, String events, String options) {
		product.setProducUpdateDate(LocalToDayTime());
		int result = productWriteMapper.update(product);
		int productNum = product.getProducNum();
		List<ReservationDatesDomain> dates = DatesRetouch(events);
		log.info(dates.toString());

		for (ReservationDatesDomain date : dates) {
			date.setProducNum(productNum);
			LocalDateTime closeDate = date.getReservDate().minus(1, ChronoUnit.DAYS).withHour(23).withMinute(59);
			date.setCloseDate(closeDate);
			date.setUpdateDate(LocalToDayTime());
			int reservationDateId = date.getReservationId();
			//업데이트로 바꾸기
			if (reservationDateId == NOTHING) {
				date.setRegistDate(LocalToDayTime());
				if (reservationDatesService.insert(date) == SUCCESS) {
					reservationDateId = reservationDatesService.getId(productNum);
					productOptionService.listInsert(productNum, reservationDateId, options);
					log.info("등록");
				}
			} else {
				reservationDatesService.update(date, reservationDateId);
				productOptionService.listInsert(productNum, reservationDateId, options);
				log.info("업데이트");
			}
		}
		return result == SUCCESS ? SUCCESS : Fail;
	}

	@Override
	@Transactional
	public int delete(ProductDomain product) {
		return productWriteMapper.delete(product);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDomain> findByCategory() {
		return productReadMapper.findByCategory();
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDomain findByProductNum(int productNum) {
		return productReadMapper.findByProductNum(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public ProductDomain forPayByProductNum(int productNum) {
		return productReadMapper.forPayByProductNum(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public int findProductNum(int hostNum) {
		return productReadMapper.findProductNum(hostNum);
	}

	@Override
	@Transactional(readOnly = true)
	public String getTitleByProductNum(int productNum) {
		return productReadMapper.getTitleByProductNum(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDomain> findByHostNum(int hostNum) {
		return productReadMapper.findByHostNum(hostNum);
	}

	@Override
	@Transactional(readOnly = true)
	public int countByHostNum(int hostNum) {
		return productReadMapper.countByHostNum(hostNum);
	}

	@Override
	@Transactional(readOnly = true)
	public int countInThisMonth(Map<String, Object> map) {
		return productReadMapper.countInThisMonth(map);
	}

	@Override
	@Transactional
	public int viewCountUp(int productNum) {
		return productWriteMapper.viewCountUp(productNum);
	}

	@Override
	@Cacheable(key="'findPopular'")
	@Transactional(readOnly = true)
	public List<ProductDomain> findPopular() {
		return productReadMapper.findPopular();
	}

	@Override
	@Cacheable(key="'findNew'")
	@Transactional(readOnly = true)
	public List<ProductDomain> findNew() {
		return productReadMapper.findNew();
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDomain> findForHostInfo(int hostNum) {
		return productReadMapper.findForHostInfo(hostNum);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDomain> findPerCategory(String firstCategoryName) {
		return productReadMapper.findPerCategory(firstCategoryName);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ProductDomain> findByRecentSearch(String search) {
		return productReadMapper.findByRecentSearch(search);
	}

	@Override
	public ProductDomain productSet(ProductDomain product, int hostNum, String addressDetail, int SecondCategoryNum) {
		String[] addressArray = product.getAddress().split(" ");
		product.setHostNum(hostNum);
		product.setAddressDetail(addressDetail);
		product.setArea(addressArray[0]);
		product.setAreaDetail(addressArray[1]);
		product.setSecCateNum(SecondCategoryNum);
		log.info(product.toString());
		if (product.getProducNum() == 0) {
			product.setProducRegitDate(LocalToDayTime());
		}
		product.setProducUpdateDate(LocalToDayTime());
		return product;
	}


	@Override
	public List<Map<String, Object>> setProductPack(List<ProductDomain> products) {
		List<Map<String, Object>> productpacks = new ArrayList<>();
		for (ProductDomain product : products) {
			Map<String, Object> productpack = new HashMap<>();
			int productNum = product.getProducNum();
			ProductOptionDomain productOption = productOptionService.OneOptionByProduct(productNum);
			Locale locale = new Locale("ko", "KR"); // 한국 로케일 (한국어, 대한민국)
			NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
			String newPrice = String.valueOf(numberFormat.format(Integer.parseInt(productOption.getPrice())));
//			String newPrice = String.valueOf(productOption.getPrice());
			productOption.setPrice(newPrice);
			productpack.put("product", product);
			productpack.put("productoption", productOption);
			productpacks.add(productpack);
		}
		return productpacks;
	}
}
