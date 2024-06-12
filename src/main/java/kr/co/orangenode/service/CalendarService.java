package kr.co.orangenode.service;

import jakarta.transaction.Transactional;
import kr.co.orangenode.dto.calendar.CalendarDTO;
import kr.co.orangenode.entity.calendar.Calendar;
import kr.co.orangenode.mapper.CalendarMapper;
import kr.co.orangenode.repository.calendar.CalendarRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final ModelMapper modelMapper;
    private final CalendarMapper calendarMapper;

    public ResponseEntity<?> insertCalendar(CalendarDTO calendarDTO) {
        log.info("캘린더 서비스 : " + calendarDTO);

        Calendar calendar = modelMapper.map(calendarDTO, Calendar.class);
        calendarRepository.save(calendar);

        return ResponseEntity.ok().body(calendar);
    }


    public ResponseEntity<?> selectsSchedules(String uid) {

        List<Calendar> calendars = calendarRepository.findByUid(uid);
        log.info("캘린더 가져오기 서비스 ...1"+uid);
        log.info("캘린더 가져오기 서비스 ...2"+calendars);
        log.info("캘린더 가져오기 서비스 ...3"+calendars.get(0).getStart());
        if(calendars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }else{

            return ResponseEntity.ok().body(calendars);
        }

    }

    public ResponseEntity<?> modifyEvent(String id, CalendarDTO calendarDTO) {
        log.info("수정 서비스"+calendarDTO);
        List<Calendar> calendars = calendarRepository.findById(id);
        if(calendars.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }else{
            calendarMapper.updateEvent(id, calendarDTO);
            return ResponseEntity.ok().build();
        }

    }

    @Transactional
    public ResponseEntity<?> deleteEvent(String id) {
        List<Calendar> calendars = calendarRepository.findById(id);
        log.info("삭제서비스..1"+id);
        log.info("삭제서비스..2"+calendars);
        if(calendars.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT FOUND");
        }else{
            calendarRepository.deleteById(id);
            return ResponseEntity.ok().body(calendars);
        }
    }
}
