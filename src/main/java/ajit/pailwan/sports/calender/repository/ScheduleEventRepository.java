package ajit.pailwan.sports.calender.repository;

import ajit.pailwan.sports.calender.entity.ScheduleEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleEventRepository extends JpaRepository<ScheduleEvent, Long> {

    List<ScheduleEvent> findAllByOrderByEventStartDateTimeAsc();
}
