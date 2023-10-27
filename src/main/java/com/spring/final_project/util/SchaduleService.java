package com.spring.final_project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.final_project.reservation_dates.ReservationDatesDomain;
import com.spring.final_project.reservation_dates.ReservationDatesVo;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class SchaduleService {

	public static List<ReservationDatesDomain> DatesRetouch(String events) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<ReservationDatesDomain> DatesList = new ArrayList<>();
		ReservationDatesVo[] reservationDates;
		try {
			reservationDates = objectMapper.readValue(events, ReservationDatesVo[].class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		// events 배열은 이제 Event 객체의 배열로 사용할 수 있습니다.
		for (ReservationDatesVo event : reservationDates) {
			ReservationDatesDomain date = setReservationDate(event);
			DatesList.add(date);
		}
		return DatesList;
	}

	private static ReservationDatesDomain setReservationDate(ReservationDatesVo data) {
		ReservationDatesDomain reservationDate = new ReservationDatesDomain();
		Instant instant = Instant.parse(data.getStart());
		LocalDateTime startDate = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
		instant = Instant.parse(data.getEnd());
		LocalDateTime endDate = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
		//		int validPerson = Integer.parseInt(data.getMaxPeople());
		//		int MaxRegisterPerOne = Integer.parseInt(data.getMaxPerson());
		//		reservationDate.setValidPerson(validPerson);
		//		reservationDate.setMaxRegisterPerOne(MaxRegisterPerOne);
		reservationDate.setReservDate(startDate);
		reservationDate.setEndDate(endDate);
		return reservationDate;
	}

}
