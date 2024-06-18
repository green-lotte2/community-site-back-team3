package kr.co.orangenode.controller.admin;

import kr.co.orangenode.entity.article.Article;
import kr.co.orangenode.entity.user.User;
import kr.co.orangenode.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final AdminService adminService;
    ///////////////////////////////////////
    ////////////////글 관리////////////////
    ///////////////////////////////////////

    //글 전체 목록
    @GetMapping("/admin/article")
    public ResponseEntity<?> getAdminArticleList(){
        return adminService.adminArticleList();
    }

    //글 보기
    @GetMapping("/admin/article/{ano}")
    public ResponseEntity<Article> adminArticleView(@PathVariable int ano) {
        Optional<Article> article = adminService.adminArticleView(ano);
        return article.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // 글 수정
    @PutMapping("/admin/article/{ano}")
    public ResponseEntity<Article> adminArticleUpd(@PathVariable int ano,
                                                   @RequestBody Article updatedArticle) {
        Optional<Article> updatedArticleOpt = adminService.adminArticleUpd(ano, updatedArticle);
        return updatedArticleOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 글 삭제
    @DeleteMapping("/admin/article/{ano}")
    public void delAdminArticle(@PathVariable int ano){
        adminService.adminArticleDel(ano);

    }

    ////////////////////////////////////////
    ////////////////유저관리////////////////
    ////////////////////////////////////////
    
    //유저 전체 목록

    @GetMapping("/admin/member/list")
    public ResponseEntity<List<User>> getAdminUserList(){
        List<User> userList = adminService.adminUserList();
        return ResponseEntity.ok(userList);
    }





}
