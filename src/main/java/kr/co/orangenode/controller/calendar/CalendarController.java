package kr.co.orangenode.controller.calendar;

import kr.co.orangenode.dto.calendar.CalendarDTO;
import kr.co.orangenode.service.CalendarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController @Slf4j @RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    // 캘린더 일정 DB 등록
    @PostMapping("/calendar/insert")
    public ResponseEntity<?> insertCalendar(@RequestBody CalendarDTO calendarDTO) {
        log.info("캘린더 컨트롤러 : " + calendarDTO);
        return calendarService.insertCalendar(calendarDTO);
    }

    // 캘린더 일정 DB에서 가져오기
    @PostMapping("/calendar/selects")
    public ResponseEntity<?> selectsSchedules(@RequestBody Map<String, String> requestData) {
        String uid = requestData.get("uid");
        log.info("캘린더 가져오기 컨트롤러 : " + uid);

        return calendarService.selectsSchedules(uid);
    }
    
    // 캘린더 일정 DB 수정
    @PostMapping("/calendar/modify/{id}")
    public ResponseEntity<?> modifyEvent(@PathVariable("id") String id, @RequestBody CalendarDTO calendarDTO){
        log.info("수정 컨트롤러..1"+id);
        log.info("수정 컨트롤러..2"+calendarDTO);
        return calendarService.modifyEvent(id, calendarDTO);
    }
    
    // 캘린더 일정 DB에서 삭제
    @GetMapping("/calendar/delete")
    public ResponseEntity<?> deleteEvent(String id){
        log.info("삭제 컨트롤러" + id);
        return calendarService.deleteEvent(id);
    }
}
