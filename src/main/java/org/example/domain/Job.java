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

    public Job(int id, String textToTranslate, String translatedText, String translatedFromLanguage, String translatedToLanguage, boolean translationCompleted, int price, LocalDate issuedAt, LocalDate approximatedDeadline, User issuedByUser, User assignedTo) {
        this.id = id;
        TextToTranslate = textToTranslate;
        TranslatedText = translatedText;
        TranslatedFromLanguage = translatedFromLanguage;
        TranslatedToLanguage = translatedToLanguage;
        this.translationCompleted = translationCompleted;
        this.price = price;
        this.issuedAt = issuedAt;
        this.approximatedDeadline = approximatedDeadline;
        this.issuedByUser = issuedByUser;
        AssignedTo = assignedTo;
    }
}
