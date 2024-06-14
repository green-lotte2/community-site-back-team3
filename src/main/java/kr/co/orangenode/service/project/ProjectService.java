package kr.co.orangenode.service.project;

import jakarta.transaction.Transactional;
import kr.co.orangenode.dto.project.CollaboratorDTO;
import kr.co.orangenode.dto.project.ProjectDTO;
import kr.co.orangenode.entity.project.Board;
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

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final CollaboratorRepository collaboratorRepository;
    private final ProjectMapper projectMapper;
    private final BoardRepository boardRepository;
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

                // Board 삭제
                List<Board> boards = boardRepository.findAllByProNo(proNo);
                log.info(boards.toString());

                for(Board board : boards) {
                    log.info("here ... 1");
                    boardRepository.deleteById(board.getBoardNo());
                }
                log.info("here ... 4");

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

    /*
    public ResponseEntity<?> selectKanbanList(int proNo) {
        List<Tuple> tuples = projectRepository.selectKanban(proNo);
        log.info("here111 : "+ tuples.toString());
        List<KanListDTO> kanListDTOS = tuples.stream()
                .map(tuple -> {
                    KanListDTO kanListDTO = new KanListDTO();
                    kanListDTO.setProNo(tuple.get(0, Integer.class));
                    kanListDTO.setId(tuple.get(1, Integer.class));
                    kanListDTO.setIssueTitle(tuple.get(2, String.class));
                    kanListDTO.setUid(tuple.get(3, String.class));
                    kanListDTO.setWorkerName(tuple.get(4, String.class));
                    kanListDTO.setWorkerProfile(tuple.get(5, String.class));
                    log.info("here222 : "+kanListDTO.toString());
                    return kanListDTO;
                }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(kanListDTOS);
    }*/
}
