package com.evaluation.fleet.AutoSuggest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CountySuggestion {
    private String fips;
    private String state;
    private String name;
}
