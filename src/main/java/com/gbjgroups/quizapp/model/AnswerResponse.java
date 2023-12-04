package com.gbjgroups.quizapp.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswerResponse {
    private Integer id;
    private String response;
}
