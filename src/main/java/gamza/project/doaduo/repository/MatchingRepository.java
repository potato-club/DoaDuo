package gamza.project.doaduo.repository;

import gamza.project.doaduo.entity.MatchingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchingRepository extends JpaRepository<MatchingEntity, Long> {
    List<MatchingEntity> findByAcceptStateFalse();
}
