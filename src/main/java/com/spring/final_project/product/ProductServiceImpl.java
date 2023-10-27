package com.spring.final_project.product;

import com.spring.final_project.host.HostDomain;
import com.spring.final_project.member.MemberController;
import com.spring.final_project.reservation_dates.ReservationDatesDomain;
import com.spring.final_project.reservation_dates.ReservationDatesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.spring.final_project.util.DateService.LocalToDayTime;
import static com.spring.final_project.util.SchaduleService.DatesRetouch;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(MemberController.class);


	private ProductMapper productMapper;
	private ProductOptionService productOptionService;

	private ReservationDatesService reservationDatesService;

	final static int SUCCESS = 1;
	final static int Fail = 0;

	@Autowired
	public ProductServiceImpl(ProductMapper productMapper, ProductOptionService productOptionService, ReservationDatesService reservationDatesService) {
		this.productMapper = productMapper;
		this.productOptionService = productOptionService;
		this.reservationDatesService = reservationDatesService;
	}

	@Override
	@Transactional
	public int insert(ProductDomain product, HostDomain host, String events, String options) {
		int result = productMapper.insert(product);
		int productNum = findProductNum(host.getHostNum());
		List<ReservationDatesDomain> dates = DatesRetouch(events);
		log.info(dates.toString());

		for (ReservationDatesDomain date : dates) {
			date.setProducNum(productNum);
			LocalDateTime closeDate = date.getReservDate().minus(1, ChronoUnit.DAYS).withHour(23).withMinute(59);
			date.setCloseDate(closeDate);
			date.setRegistDate(LocalToDayTime());
			date.setUpdateDate(LocalToDayTime());
			if (1 == reservationDatesService.insert(date)) {
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
		int result = productMapper.update(product);
		int productNum = findProductNum(host.getHostNum());
		List<ReservationDatesDomain> dates = DatesRetouch(events);
		log.info(dates.toString());

		for (ReservationDatesDomain date : dates) {
			date.setProducNum(productNum);
			LocalDateTime closeDate = date.getReservDate().minus(1, ChronoUnit.DAYS).withHour(23).withMinute(59);
			date.setCloseDate(closeDate);
			date.setUpdateDate(LocalToDayTime());

			//업데이트로 바꾸기
			if (1 == reservationDatesService.insert(date)) {
				int reservationDateId = reservationDatesService.getId(productNum);
				productOptionService.listInsert(productNum, reservationDateId, options);
				log.info("등록");
			}
		}
		return result == SUCCESS ? SUCCESS : Fail;
	}

	@Override
	@Transactional
	public int delete(ProductDomain product) {
		return productMapper.delete(product);
	}

	@Override
	@Transactional
	public List<ProductDomain> findByCategory() {
		return productMapper.findByCategory();
	}

	@Override
	@Transactional
	public ProductDomain findByProductNum(int productNum) {
		return productMapper.findByProductNum(productNum);
	}

	@Override
	@Transactional
	public ProductDomain forPayByProductNum(int productNum) {
		return productMapper.forPayByProductNum(productNum);
	}

	@Override
	@Transactional
	public int findProductNum(int hostNum) {
		return productMapper.findProductNum(hostNum);
	}

	@Override
	public String getTitleByProductNum(int productNum) {
		return productMapper.getTitleByProductNum(productNum);
	}

	@Override
	@Transactional
	public List<ProductDomain> findByHostNum(int hostNum) {
		return productMapper.findByHostNum(hostNum);
	}

	@Override
	@Transactional
	public int countByHostNum(int hostNum) {
		return productMapper.countByHostNum(hostNum);
	}

	@Override
	@Transactional
	public int countInThisMonth(Map<String, Object> map) {
		return productMapper.countInThisMonth(map);
	}

	@Override
	@Transactional
	public int viewCountUp(int productNum) {
		return productMapper.viewCountUp(productNum);
	}

	@Override
	@Transactional
	public List<ProductDomain> findPopular() {
		return productMapper.findPopular();
	}

	@Override
	@Transactional
	public List<ProductDomain> findNew() {
		return productMapper.findNew();
	}

	@Override
	@Transactional
	public List<ProductDomain> findForHostInfo(int hostNum) {
		return productMapper.findForHostInfo(hostNum);
	}

	@Override
	@Transactional
	public List<ProductDomain> findPerCategory(String firstCategoryName) {
		return productMapper.findPerCategory(firstCategoryName);
	}

	@Override
	public List<ProductDomain> findByRecentSearch(String search) {
		return productMapper.findByRecentSearch(search);
	}

	@Override
	public ProductDomain productSet(ProductDomain product, int hostNum, String addressDetail, int SecondCategoryNum) {
		String[] addressArray = product.getAddress().split(" ");
		product.setHostNum(hostNum);
		product.setAddressDetail(addressDetail);
		product.setArea(addressArray[0]);
		product.setAreaDetail(addressArray[1]);
		product.setSecCateNum(SecondCategoryNum);
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
			productOption.setPrice(newPrice);
			productpack.put("product", product);
			productpack.put("productoption", productOption);
			productpacks.add(productpack);
		}
		return productpacks;
	}
}
