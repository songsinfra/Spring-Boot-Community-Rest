package com.community.rest.controller;

import com.community.rest.domain.Board;
import com.community.rest.repository.BoardRepository;

import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class BoardRestController {

    private BoardRepository boardRepository;

    public BoardRestController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //url 규칙이 맞아야 리퀘스트가 들어옴
    @GetMapping("/boards/search/addTest")
    public @ResponseBody ResponseEntity<?> basicTest() {
        List<Board> boardList = boardRepository.findAll();

        Resources<Board> resources = new Resources<>(boardList);
        resources.add(linkTo(methodOn(BoardRestController.class).basicTest()).withSelfRel());
        return ResponseEntity.ok(resources);
    }

    @GetMapping("/boards/search/test")
    public @ResponseBody Resources<Board> basicTestReturnBoard() {
        List<Board> storeList = boardRepository.findAll();

        Resources<Board> resources = new Resources<>(storeList);
        resources.add(linkTo(methodOn(BoardRestController.class).basicTestReturnBoard()).withSelfRel());
        return resources;
    }
}
