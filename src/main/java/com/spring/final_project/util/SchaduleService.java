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
		System.out.println(data.getId().length() + " setfd");
		if (data.getId().length() <= 3 || !data.getId().substring(0, 3).equals("new")) {
			reservationDate.setReservationId(Integer.parseInt(data.getId()));
		}
		reservationDate.setReservDate(startDate);
		reservationDate.setEndDate(endDate);
		return reservationDate;
	}

}
