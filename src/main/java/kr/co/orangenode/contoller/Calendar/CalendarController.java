package kr.co.orangenode.contoller.Calendar;

import kr.co.orangenode.dto.calendar.CalendarDTO;
import kr.co.orangenode.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @Slf4j @RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @PostMapping("/calendar/insert")
    public ResponseEntity<?> insertCalendar(@RequestBody CalendarDTO calendarDTO) {
        log.info("캘린더 컨트롤러 : " + calendarDTO);
        return calendarService.insertCalendar(calendarDTO);
    }

    @PostMapping("/calendar/selects")
    public ResponseEntity<?> selectsSchedules(@RequestBody Map<String, String> requestData) {
        String uid = requestData.get("uid");
        log.info("캘린더 가져오기 컨트롤러 : " + uid);

        return calendarService.selectsSchedules(uid);
    }
}
