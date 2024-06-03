package kr.co.orangenode.repository.calendar;

import kr.co.orangenode.entity.calendar.Calendar;

import kr.co.orangenode.repository.custom.calendar.CalendarRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Integer>, CalendarRepositoryCustom {

    public List<Calendar> findByUid(String uid);
    public List<Calendar> findById(String id);
    public void deleteById(String id);
}
