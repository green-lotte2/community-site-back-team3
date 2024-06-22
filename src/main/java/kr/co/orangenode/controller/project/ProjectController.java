package kr.co.orangenode.controller.project;

import kr.co.orangenode.dto.project.ProjectDTO;
import kr.co.orangenode.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        log.info("uid 들어옴 ? "+ uid);
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
    /* 칸반보드 불러오기*/
    @GetMapping("/kanban")
    public ResponseEntity<?> viewProjectKanban(@RequestParam int proNo) {
        log.info("proNo:" + proNo);
        return projectService.viewKanban(proNo);
    }
    // 칸반보드 생성 //
    @PostMapping("/kanban/create")
    public ResponseEntity<?> addKanban(@RequestBody ProjectDTO projectDTO) {
        log.info("projectDTO @@ :" + projectDTO);
        return projectService.createKanban(projectDTO);
    }
}