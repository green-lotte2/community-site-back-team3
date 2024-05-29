package kr.co.orangenode.contoller.project;

import kr.co.orangenode.dto.project.ProjectDTO;
import kr.co.orangenode.service.project.ProjectService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ProjectController {

    private  final ProjectService projectService;

    @PostMapping("/project/create")
    public ResponseEntity<?> createProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }
}