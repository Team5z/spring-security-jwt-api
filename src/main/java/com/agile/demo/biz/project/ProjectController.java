package com.agile.demo.biz.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/project")
    public ResponseEntity<?> createProject(@RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectService.createProject(projectDto);
        return ResponseEntity.created(URI.create("/projects/" + projectEntity.getNp_seq())).build();
    }

    @GetMapping("/projects")
    public List<ProjectDto> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/project/{np_seq}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable long np_seq) {
        ProjectDto projectDto = convertToDto(projectService.getProjectById(np_seq));
        return ResponseEntity.ok(projectDto);
    }
    // 특정 값을의 내용을 수정
    @PutMapping("/project/{np_seq}")
    public ResponseEntity<?> updateProject(@PathVariable long np_seq, @RequestBody ProjectDto projectDto) {
        ProjectEntity projectEntity = projectService.updateProject(np_seq, projectDto);
        return ResponseEntity.ok(convertToDto(projectEntity));
    }

    @DeleteMapping("project/{np_seq}")
    public void deleteProject(@PathVariable long np_seq){
        projectService.deleteProject(np_seq);
    }

    private ProjectDto convertToDto(ProjectEntity projectEntity) {
        ProjectDto projectDto = new ProjectDto();
        projectDto.setNp_seq(projectEntity.getNp_seq());
        projectDto.setProject_title(projectEntity.getProject_title());
        projectDto.setProject_assign(projectEntity.getProject_assign());
        return projectDto;
    }
}
