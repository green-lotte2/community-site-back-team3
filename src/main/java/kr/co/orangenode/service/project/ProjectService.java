package kr.co.orangenode.service.project;

import jakarta.transaction.Transactional;
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
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;

    /* 프로젝트 생성 */
    @Transactional
    public ResponseEntity<?> createProject(ProjectDTO projectDTO) {
        try {
            Project project = modelMapper.map(projectDTO, Project.class);
            Project savedProject = projectRepository.save(project);
            for(String uid: projectDTO.getUids()){

            }
            return ResponseEntity.status(HttpStatus.OK).body(savedProject);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FAIL");
        }

    }
    /* 프로젝트 출력 */
    public ResponseEntity<?> selectProjectList(ProjectDTO projectDTO) {
        try {
            List<Project> projectList = projectRepository.findAllByUid(projectDTO.getUid());
            return ResponseEntity.status(HttpStatus.OK).body(projectList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FAIL");
        }
    }
}
