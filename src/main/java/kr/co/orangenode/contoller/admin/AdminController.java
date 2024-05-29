package kr.co.orangenode.contoller.admin;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.entity.board.File;
import kr.co.orangenode.service.AdminService;
import kr.co.orangenode.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {


    private final AdminService adminService;

    //글 전체 목록
    @GetMapping("/admin/article")
    public ResponseEntity<?> getAdminArticleList(){

        return adminService.adminArticleList();
    }

    // 글 삭제
    @DeleteMapping("/admin/article/{ano}")
    public void delAdminArticle(@PathVariable int ano){
        adminService.adminArticleDel(ano);

    }






}
