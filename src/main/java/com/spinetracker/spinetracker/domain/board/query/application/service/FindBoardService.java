package com.spinetracker.spinetracker.domain.board.query.application.service;

import com.spinetracker.spinetracker.domain.board.query.application.dto.FindBoardDTO;
import com.spinetracker.spinetracker.domain.board.query.application.dto.FindPostDTO;
import com.spinetracker.spinetracker.domain.board.query.application.dto.FindProductDTO;
import com.spinetracker.spinetracker.domain.board.query.domain.repository.BoardMapper;
import com.spinetracker.spinetracker.domain.member.query.application.dto.FindMemberDTO;
import com.spinetracker.spinetracker.domain.member.query.application.service.FindMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindBoardService {

    private final BoardMapper boardMapper;
    private final FindProductService findProductService;
    private final FindMemberService findMemberService;

    @Autowired
    public FindBoardService(BoardMapper boardMapper, FindProductService findProductService, FindMemberService findMemberService) {
        this.boardMapper = boardMapper;
        this.findProductService = findProductService;
        this.findMemberService = findMemberService;
    }

    public List<FindBoardDTO> findAllPost() {

        List<FindPostDTO> findAllPostList =  boardMapper.findAllPost();

        List<FindBoardDTO> findBoardDTOList = new ArrayList<>();

        for (FindPostDTO findPost : findAllPostList) {
            FindProductDTO findProduct = findProductService.findByProductId(findPost.getProductId());
            FindMemberDTO findMember = findMemberService.findById(findPost.getWriterId());
            findBoardDTOList.add(
              new FindBoardDTO(
                      findPost.getId(),
                      findPost.getWriterId(),
                      findMember.getName(),
                      findPost.getContent(),
                      findProduct.getProductName(),
                      findProduct.getProductUrl(),
                      findProduct.getImageUrl()
              )
            );
        }

        return findBoardDTOList;
    }
}
