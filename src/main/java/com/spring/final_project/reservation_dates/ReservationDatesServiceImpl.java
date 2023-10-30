package com.spring.final_project.reservation_dates;

import com.spring.final_project.product.ProductOptionService;
import com.spring.final_project.reservation.ReservationService;
import com.spring.final_project.util.CalendarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

@Service
public class ReservationDatesServiceImpl implements ReservationDatesService {


	final static int SUCCESS = 1;
	final static int Fail = 0;
	ReservationDatesMapper reservationDatesMapper;
	ProductOptionService productOptionService;
	ReservationService reservationService;

	@Autowired
	public ReservationDatesServiceImpl(ReservationDatesMapper reservationDatesMapper, ProductOptionService productOptionService, ReservationService reservationService) {
		this.reservationDatesMapper = reservationDatesMapper;
		this.productOptionService = productOptionService;
		this.reservationService = reservationService;
	}

	@Override
	@Transactional
	public int insert(ReservationDatesDomain reservationDates) {
		return reservationDatesMapper.insert(reservationDates);
	}

	@Override
	public int update(ReservationDatesDomain reservationDates, int reservationDateId) {
		if (reservationDatesMapper.update(reservationDates) == SUCCESS) {
//			try {
				if (reservationService.findByReservationDateId(reservationDateId) == null) {
					productOptionService.deleteByReservationId(reservationDateId);
				}
//			} catch (Exception e) {
//				TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//				return Fail;
//			}
		}
		return SUCCESS;
	}

	@Override
	@Transactional
	public int delete(int productNum) {
		return reservationDatesMapper.delete(productNum);
	}

	@Override
	@Transactional
	public List<ReservationDatesDomain> findByProductNum(int productNum) {
		return reservationDatesMapper.findByProductNum(productNum);
	}

	@Override
	@Transactional
	public List<CalendarVo> findByCalendarVo(int productNum) {
		return reservationDatesMapper.findByCalendarVo(productNum);
	}

	@Override
	@Transactional
	public ReservationDatesDomain findById(int reservationId) {
		return reservationDatesMapper.findById(reservationId);
	}


	@Override
	@Transactional
	public int getId(int productNum) {
		return reservationDatesMapper.getId(productNum);
	}
}
