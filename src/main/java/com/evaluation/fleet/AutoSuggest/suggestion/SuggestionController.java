package com.evaluation.fleet.AutoSuggest.suggestion;

import com.evaluation.fleet.AutoSuggest.domain.AutoSuggestResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SuggestionController {
    @Autowired
    private SuggestionService suggestionService;

    @GetMapping(value = "/suggest")
    public ResponseEntity<List<AutoSuggestResponse>> getInvoice(@RequestParam("q") String query) {
        return ResponseEntity.ok(suggestionService.getSuggestions(query));
    }
}
