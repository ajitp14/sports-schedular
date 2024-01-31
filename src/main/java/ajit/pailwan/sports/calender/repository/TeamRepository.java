package ajit.pailwan.sports.calender.repository;

import ajit.pailwan.sports.calender.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
}
