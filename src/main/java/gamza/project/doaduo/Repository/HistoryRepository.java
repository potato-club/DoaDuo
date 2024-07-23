package gamza.project.doaduo.Repository;

import gamza.project.doaduo.entity.HistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HistoryRepository extends JpaRepository<HistoryEntity, Long> {

  boolean existsById(Long id);
}
