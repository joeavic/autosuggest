package com.evaluation.fleet.AutoSuggest.suggestion;

import com.evaluation.fleet.AutoSuggest.domain.CountySuggestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @GetMapping(value = "/suggest")
    public ResponseEntity<List<CountySuggestion>> getInvoice(@RequestParam("q") String query) {
        return ResponseEntity.ok(suggestionService.getSuggestions(query));
    }
}
