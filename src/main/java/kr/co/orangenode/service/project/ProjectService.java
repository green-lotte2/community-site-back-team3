package kr.co.orangenode.service.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import kr.co.orangenode.dto.project.ProjectDTO;
import kr.co.orangenode.entity.project.Collaborator;
import kr.co.orangenode.entity.project.Project;
import kr.co.orangenode.mapper.ProjectMapper;
import kr.co.orangenode.repository.project.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final ProjectMapper projectMapper;
    private final ModelMapper modelMapper;

    /* 프로젝트 생성 */
    @Transactional
    public ResponseEntity<?> createProject(ProjectDTO projectDTO) {
        try {

            Project project = modelMapper.map(projectDTO, Project.class);
            project.setContent("");
            Project savedProject = projectRepository.save(project);
            for(String uid: projectDTO.getUids()){
                if (uid != null) {
                    log.info("1번 :" + uid);
                    Collaborator collaborator = new Collaborator();
                    collaborator.setUid(uid);
                    collaborator.setProNo(savedProject.getProNo());
                    log.info("2번 :" + uid);
                    collaboratorRepository.save(collaborator);
                }
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
    /* 프로젝트 삭제*/
    @Transactional
    public ResponseEntity<?> deleteProject(int proNo) {
        try {
            Optional<Project> findProNo = projectRepository.findById(proNo);
            if(findProNo.isPresent()){
                collaboratorRepository.deleteAllByProNo(proNo);
                projectRepository.deleteById(proNo);
                return ResponseEntity.status(HttpStatus.OK).body("success");
            }
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("FAIL");
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error");
        }
    }
    /* 프로젝트 수정 */
    @Transactional
    public ResponseEntity<?> updateProject(ProjectDTO projectDTO) {
        projectMapper.updateProject(projectDTO);
        return ResponseEntity.status(HttpStatus.OK).body("success");
    }
    /* 특정 프로젝트 보기*/
    public ResponseEntity<?> viewProject(int proNo) {
        Optional<Project> viewNo = projectRepository.findById(proNo);
        if(viewNo.isPresent()){
            Project project = viewNo.get();
            log.info("도꺠비참수" +project);
            return ResponseEntity.status(HttpStatus.OK).body(project);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
        }
    }
    /* 칸반보드 생성 */
    @Transactional
    public ResponseEntity<?> createKanban(ProjectDTO projectDTO) {

        Optional<Project> optProject = projectRepository.findById(projectDTO.getProNo());
        if(optProject.isPresent()){
            Project project = optProject.get();
            project.setContent(projectDTO.getContent());
            projectRepository.save(project);
            log.info("proect체크:" + project);
            return ResponseEntity.status(HttpStatus.OK).body(1);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }
    }
    // 칸반보드 출력
    public ResponseEntity<?> viewKanban(int proNo) {
        Optional<Project> viewNo = projectRepository.findById(proNo);
        if (viewNo.isPresent()) {
            Project project = viewNo.get();
            String content = project.getContent();

            if (content == null || content.trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(Collections.emptyList());
            }

            ObjectMapper objectMapper = new ObjectMapper();
            List<Map<String, Object>> kanbanData = null;

            try {
                kanbanData = objectMapper.readValue(content, new TypeReference<List<Map<String, Object>>>() {});
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("content 파싱 에러");
            }
            return ResponseEntity.status(HttpStatus.OK).body(kanbanData);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("데이터를 찾지 못했습니다.");
        }
    }
}
