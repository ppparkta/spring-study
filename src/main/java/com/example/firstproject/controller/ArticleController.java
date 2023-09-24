package com.example.firstproject.controller;

import com.example.firstproject.dto.ArticleForm;
import com.example.firstproject.entity.Article;
import com.example.firstproject.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
public class ArticleController {
    @Autowired
    private ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticle(ArticleForm form){
        //System.out.println(form.toString());
        log.info(form.toString());
        //DTO를 엔티티로 변환하기
        Article article = form.toEntity();
        //레포지토리 이용해서 엔티티를 DB에 저장하기
        Article saved = articleRepository.save(article);
        //System.out.println(saved.toString());
        log.info(saved.toString());
        return "redirect:/articles/"+saved.getId();
    }

    //중괄호 안에 이름을 써주면 해당 이름을 변수로 사용할 수 있음
    //@PathVariable은 URL 요청으로 들어온 전달값을 컨트롤러의 매개변수로 가져옴
    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){
        log.info("id = "+id);
        //id조회해서 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        //모델에 데이터 등록
        model.addAttribute("article", article);
        log.info(article.toString());
        //뷰 페이지 반환
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        //db에서 article 가져오기
        List<Article> articleList = articleRepository.findAll();
        //가져온 article 묶음 모델에 등록
        model.addAttribute("articleList", articleList);
        //뷰 페이지 반환
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article article = articleRepository.findById(id).orElse(null);
        model.addAttribute("article", article);
        return "articles/edit";
    }
    @PostMapping("/articles/update")
    public String update(ArticleForm form){
        log.info(form.toString());
        //dto를 엔티티로 변환
        Article article = form.toEntity();
        //엔티티를 db에 저장
        Article equal = articleRepository.findById(article.getId()).orElse(null);
        if (equal != null){
            articleRepository.save(article);
            return "redirect:/articles/"+article.getId();
        }
        return "redirect:/articles";
    }
    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rdirmsg){
        log.info("삭제 요청이 들어왔습니다!");
        Article article = articleRepository.findById(id).orElse(null);
        if (article!=null){
            articleRepository.delete(article);
            rdirmsg.addFlashAttribute("msg", "삭제되었습니다.");
        }
        return "redirect:/articles";
    }
}
