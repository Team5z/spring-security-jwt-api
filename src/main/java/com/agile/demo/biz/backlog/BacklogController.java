package com.agile.demo.biz.backlog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/backlog")
public class BacklogController {

    @Autowired
    private BacklogService backlogService;

    @PostMapping("/backlog") // 백로그 생성하기
    public ResponseEntity<?> createProject(@RequestBody BacklogDto backlogDto) {
        BacklogEntity backlogEntity = backlogService.createBacklog(backlogDto);

        return ResponseEntity.created(URI.create("/backlog/" + backlogEntity.getNb_seq())).build();
    }

    @GetMapping("/backlogs") // 백로그 출력하기 - 전체
    public List<BacklogDto> getAllBacklog() {
        return backlogService.getAllBacklog();
    }

    @GetMapping("/backlogs/{nb_seq}") // 백로그 출력 - 한개 확대시
    public ResponseEntity<BacklogDto> getOneBacklog(@PathVariable long nb_seq) {
        BacklogDto backlogDto = convertToDto(backlogService.getBacklogById(nb_seq));
        return ResponseEntity.ok(backlogDto);
    }

    @PutMapping("/backlog/{nb_seq}")
    public ResponseEntity<?> updateBacklog(@PathVariable long nb_seq, @RequestBody BacklogDto backlogDto) {
        BacklogEntity backlogEntity = backlogService.updateBacklog(nb_seq, backlogDto);
        return ResponseEntity.ok(convertToDto(backlogEntity));
    }

    @DeleteMapping("/backlog/{nb_seq}")
    public void deleteProject(@PathVariable long nb_seq){
        backlogService.deleteBacklog(nb_seq);
    }

    private BacklogDto convertToDto(BacklogEntity backlogEntity) {
        BacklogDto backlogDto = new BacklogDto();
        backlogDto.setNb_seq(backlogEntity.getNb_seq());
        backlogDto.setTitle(backlogEntity.getTitle());
        backlogDto.setProject(backlogEntity.getProject());
        backlogDto.setDescription(backlogEntity.getDescription());
        return backlogDto;
    }
}