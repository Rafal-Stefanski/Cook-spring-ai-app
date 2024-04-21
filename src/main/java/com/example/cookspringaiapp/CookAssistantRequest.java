package com.example.cookspringaiapp;

import java.util.List;

record CookAssistantRequest(
        List<Ingredients> ingredients,
        String diet,
        String mealType) {
}

record Ingredients(
        String name,
        double weight) {
}
