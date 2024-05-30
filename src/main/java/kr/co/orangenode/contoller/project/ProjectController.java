package kr.co.orangenode.contoller.project;

import kr.co.orangenode.dto.project.ProjectDTO;
import kr.co.orangenode.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private  final ProjectService projectService;

    /* 프로젝트 생성 */
    @PostMapping("/project/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }
    /* 프로젝트 리스트 출력 */
    @GetMapping("/project/list")
    public ResponseEntity<?> selectProjectList(@RequestParam String uid) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setUid(uid);
        return projectService.selectProjectList(projectDTO);
    }
    /* 프로젝트 삭제*/
    @DeleteMapping("/project/delete")
    public ResponseEntity<?> deleteProject(@RequestParam int proNo) {
        return projectService.deleteProject(proNo);
    }
    /* 프로젝트 수정*/
    @PutMapping("/project/update")
    public ResponseEntity<?> updateProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(projectDTO);
    }
    /* 프로젝트 특정글 불러오기*/
    @GetMapping("/project/kanban")
    public ResponseEntity<?> viewProjectKanban(@RequestParam int proNo) {
        return projectService.viewProject(proNo);
    }
    @GetMapping("/project/kanban/list")
    public ResponseEntity<?> kanbanList(@RequestParam int proNo) {
        return projectService.selectKanbanList(proNo);
    }
}