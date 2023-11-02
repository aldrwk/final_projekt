package com.spring.final_project.reservation_dates;

import com.spring.final_project.product.ProductOptionService;
import com.spring.final_project.reservation.ReservationService;
import com.spring.final_project.reservation_dates.mapper.*;
import com.spring.final_project.util.CalendarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationDatesServiceImpl implements ReservationDatesService {


	final static int SUCCESS = 1;
	final static int Fail = 0;
	ReservationDatesWriteMapper reservationDatesWriteMapper;
	ReservationDatesReadMapper reservationDatesReadMapper;
	ProductOptionService productOptionService;
	ReservationService reservationService;

	@Autowired
	public ReservationDatesServiceImpl(ReservationDatesWriteMapper reservationDatesWriteMapper, ReservationDatesReadMapper reservationDatesReadMapper, ProductOptionService productOptionService, ReservationService reservationService) {
		this.reservationDatesWriteMapper = reservationDatesWriteMapper;
		this.reservationDatesReadMapper = reservationDatesReadMapper;
		this.productOptionService = productOptionService;
		this.reservationService = reservationService;
	}


	@Override
	@Transactional
	public int insert(ReservationDatesDomain reservationDates) {
		return reservationDatesWriteMapper.insert(reservationDates);
	}

	@Override
	public int update(ReservationDatesDomain reservationDates, int reservationDateId) {
		if (reservationDatesWriteMapper.update(reservationDates) == SUCCESS) {
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
		return reservationDatesWriteMapper.delete(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ReservationDatesDomain> findByProductNum(int productNum) {
		return reservationDatesReadMapper.findByProductNum(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public List<CalendarVo> findByCalendarVo(int productNum) {
		return reservationDatesReadMapper.findByCalendarVo(productNum);
	}

	@Override
	@Transactional(readOnly = true)
	public ReservationDatesDomain findById(int reservationId) {
		return reservationDatesReadMapper.findById(reservationId);
	}


	@Override
	@Transactional(readOnly = true)
	public int getId(int productNum) {
		return reservationDatesReadMapper.getId(productNum);
	}
}
