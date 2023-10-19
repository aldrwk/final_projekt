package com.spring.final_project.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.final_project.reservation_dates.reservationDatesDomain;
import com.spring.final_project.reservation_dates.reservationDatesVo;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class schaduleService {

	public static List<reservationDatesDomain> DatesRetouch(String events) {
		ObjectMapper objectMapper = new ObjectMapper();
		List<reservationDatesDomain> DatesList = new ArrayList<>();

		reservationDatesVo[] reservationDates;

		try {
			reservationDates = objectMapper.readValue(events, reservationDatesVo[].class);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}

		// events 배열은 이제 Event 객체의 배열로 사용할 수 있습니다.
		for (reservationDatesVo event : reservationDates) {
			reservationDatesDomain date = setReservationDate(event);
			DatesList.add(date);
		}
		return DatesList;
	}

	private static reservationDatesDomain setReservationDate(reservationDatesVo data) {
		reservationDatesDomain reservationDate = new reservationDatesDomain();
		Instant instant = Instant.parse(data.getStart());
		LocalDateTime startDate = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
		instant = Instant.parse(data.getEnd());
		LocalDateTime endDate = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
		int validPerson = Integer.parseInt(data.getMaxPeople());
		int MaxRegisterPerOne = Integer.parseInt(data.getMaxPerson());
		reservationDate.setReservDate(startDate);
		reservationDate.setEndDate(endDate);
		reservationDate.setValidPerson(validPerson);
		reservationDate.setMaxRegisterPerOne(MaxRegisterPerOne);

		return reservationDate;
	}

}
