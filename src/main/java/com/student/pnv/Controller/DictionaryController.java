package com.student.pnv.Controller;

import com.student.pnv.entity.Dictionary;
import com.student.pnv.entity.TuDien;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController {
    private List<Dictionary> dictionaries = new ArrayList<>(
            Arrays.asList(
                    new Dictionary(1,"hello"),
                    new Dictionary(2,"good bye"),
                    new Dictionary(3, "thank you"),
                    new Dictionary(4, "how are you?"),
                    new Dictionary(5, "no, thanks")
            )
    );

    private List<TuDien> tudien = new ArrayList<>(
            Arrays.asList(
                    new TuDien(1,"xin chào"),
                    new TuDien(2,"tạm biệt"),
                    new TuDien(3, "cảm ơn"),
                    new TuDien(4, "bạn khoẻ không?"),
                    new TuDien(5, "không, cảm ơn")
            )
    );

    @GetMapping("/{word}")
    public ResponseEntity<String> show(@PathVariable String word) {
        word = word.toLowerCase();
        for (Dictionary dictionary : dictionaries) {
            if (dictionary.getWord().equals(word)) {
                int id = dictionary.getId();
                for (TuDien tuDien : tudien) {
                    if (tuDien.getId() == id) {
                        return ResponseEntity.ok(tuDien.getWord());
                    }
                }
            }
        }
        return ResponseEntity.notFound().build();
    }
    }
