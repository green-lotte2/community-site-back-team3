package kr.co.orangenode.contoller.Calendar;

import kr.co.orangenode.dto.calendar.CalendarDTO;
import kr.co.orangenode.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController @Slf4j @RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/calendar/insert")
    public ResponseEntity<?> insertCalendar(@RequestBody CalendarDTO calendarDTO) {
        log.info("캘린더 컨트롤러 : " + calendarDTO);
        return calendarService.insertCalendar(calendarDTO);
    }
}
