package org.example.domain;

import java.time.LocalDate;

public class Job {
    private int id;
    private String TextToTranslate;
    private String TranslatedText;
    private String TranslatedFromLanguage;
    private String TranslatedToLanguage;
    private boolean translationCompleted;
    private int price;
    private LocalDate issuedAt;
    private LocalDate approximatedDeadline;
    private User issuedByUser;
    private User AssignedTo;


}
