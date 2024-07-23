package gamza.project.doaduo.Service.impl;

import gamza.project.doaduo.Repository.HistoryRepository;
import gamza.project.doaduo.Service.inter.HistoryService;
import gamza.project.doaduo.dto.HistoryRequestDTO;
import gamza.project.doaduo.entity.HistoryEntity;
import gamza.project.doaduo.error.requestError.BadRequestException;
import gamza.project.doaduo.jwt.JwtTokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public class HistoryServiceImpl implements HistoryService {

  private final HistoryRepository historyRepository;
  private final JwtTokenProvider jwtTokenProvider;

  public HistoryServiceImpl(HistoryRepository historyRepository, JwtTokenProvider jwtTokenProvider) {
    this.historyRepository = historyRepository;
    this.jwtTokenProvider = jwtTokenProvider;
  }

  @Override
  public void createHistory(HistoryRequestDTO dto, HttpServletRequest request) {
    HistoryEntity historyEntity = new HistoryEntity().builder()
        .cause(dto.getCause())
        .content(dto.getContent())
        .writer(dto.getWriter())
        .date(dto.getDate())
        .userRole(request.getHeader("role"))
        .build();

  }
}
