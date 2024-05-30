package kr.co.orangenode.service.project;

import kr.co.orangenode.dto.project.ProjectDTO;
import kr.co.orangenode.entity.project.Project;
import kr.co.orangenode.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KanbanService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    /* 프로젝트 출력 */
    public ResponseEntity<?> selectKanbanList(ProjectDTO projectDTO) {
        try {
            List<Project> projectList = projectRepository.findAllByUid(projectDTO.getUid());
            return ResponseEntity.status(HttpStatus.OK).body(projectList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FAIL");
        }
    }
}
