package kr.co.orangenode.service;

import kr.co.orangenode.dto.calendar.CalendarDTO;
import kr.co.orangenode.entity.calendar.Calendar;
import kr.co.orangenode.repository.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final ModelMapper modelMapper;

    public ResponseEntity<?> insertCalendar(CalendarDTO calendarDTO) {
        log.info("캘린더 서비스 : " + calendarDTO);
        String isAllDay = calendarDTO.getIsAllDay();
        if(isAllDay.equals("true")){

        }
        Calendar calendar = modelMapper.map(calendarDTO, Calendar.class);
        calendarRepository.save(calendar);

        return ResponseEntity.ok(calendar);
    }


}
