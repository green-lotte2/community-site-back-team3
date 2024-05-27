package kr.co.orangenode.contoller.admin;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.service.AdminService;
import kr.co.orangenode.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/admin/article")
    public List<Article> adminArticleList(){

        return adminService.getAdminArticleList();
    }


}
