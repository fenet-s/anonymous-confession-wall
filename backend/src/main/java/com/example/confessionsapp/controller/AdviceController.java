package com.example.confessionsapp.controller;

import com.example.confessionsapp.model.Advice;
import com.example.confessionsapp.model.User;
import com.example.confessionsapp.repository.AdviceRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/advice")
public class AdviceController {

    private final AdviceRepository repo;

    public AdviceController(AdviceRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public List<Advice> all() {
        return repo.findAll();
    }

    @PostMapping
    public Object create(@RequestBody Map<String, String> body,
                         HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user == null) return "Login required";

        Advice a = new Advice();
        a.setContent(body.get("content"));
        a.setUser(user);

        return repo.save(a);
    }

    @PostMapping("/{id}/like")
    public Object like(@PathVariable Long id) {
        return repo.findById(id)
                .map(a -> {
                    a.setLikes(a.getLikes() + 1);
                    return repo.save(a);
                })
                .orElse("Not found");
    }
}
