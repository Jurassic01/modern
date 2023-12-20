package com.example.webapp;

public class Tasks {
    private int Id;
    private String title;
    private String status;
    private String description;
    // Інші необхідні поля

    // Геттер та сеттер для поля name
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Геттер та сеттер для поля price
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Геттер та сеттер для поля description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id=id;
    }


    // Інші геттери та сеттери для додаткових полів, якщо потрібно
}
