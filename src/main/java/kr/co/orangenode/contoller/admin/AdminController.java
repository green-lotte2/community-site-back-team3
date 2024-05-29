package kr.co.orangenode.contoller.admin;

import kr.co.orangenode.entity.board.Article;
import kr.co.orangenode.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AdminController {

    @Autowired
    private final AdminService adminService;

    //글 전체 목록
    @GetMapping("/admin/article")
    public ResponseEntity<?> getAdminArticleList(){

        return adminService.adminArticleList();
    }

    // 글 보기
    @GetMapping("/admin/article/{ano}")
    public Optional<Article> adminArticleView(@PathVariable int ano) {
        return adminService.adminArticleView(ano);
    }

    /*글 수정
    @PutMapping("/admin/article/{ano}")
    public ResponseEntity<Article> UpdAdminArticle(){
        return adminService.adminArticleUpdate();
    }
*/
    // 글 삭제
    @DeleteMapping("/admin/article/{ano}")
    public void delAdminArticle(@PathVariable int ano){
        adminService.adminArticleDel(ano);

    }






}
