package org.example.entity;

import java.time.LocalDate;

public class Job {
    private int id;
    private String textToTranslate;
    private String translatedText;
    private String translatedFromLanguage;
    private String translatedToLanguage;
    private boolean translationCompleted;
    private int price;
    private LocalDate issuedAt;
    private LocalDate approximatedDeadline;
    private User issuedByUser;
    private User assignedTo;
}
