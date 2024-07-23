package gamza.project.doaduo.Service.inter;

import gamza.project.doaduo.dto.HistoryRequestDTO;
import jakarta.servlet.http.HttpServletRequest;

public interface HistoryService {

  void createHistory(HistoryRequestDTO dto, HttpServletRequest request);

}
