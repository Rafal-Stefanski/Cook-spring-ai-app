package com.example.cookspringaiapp;

import java.util.List;

record Recipe(
        String title,
        int prepTimeMinutes,
        List<String> instructionSteps) {
}
