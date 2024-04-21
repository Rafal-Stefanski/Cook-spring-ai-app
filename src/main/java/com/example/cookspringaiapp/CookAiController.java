package com.example.cookspringaiapp;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.parser.BeanOutputParser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CookAiController {

    private final OpenAiChatClient openAiChatClient;

    @PostMapping("/recipeSuggestions")
    Recipe suggestRecipe(@RequestBody CookAssistantRequest request) {

        BeanOutputParser<Recipe> parser = new BeanOutputParser<>(Recipe.class);

        PromptTemplate template = getPromptTemplate();
        template.add("skladniki", request.ingredients());
        template.add("dieta", request.diet());
        template.add("posilek", request.mealType());
        template.add("format", parser.getFormat());

        var prompt = template.create();
        var response = openAiChatClient.call(prompt);

        var content = response.getResult().getOutput().getContent();

        return parser.parse(content);
    }

    private static PromptTemplate getPromptTemplate() {
        String promptString = """
                Na podstawie dostępnych składników: {skladniki}
                oraz z unwzględnieniem diety: {dieta}
                i preferowanego typu pisiłku: {posilek}
                daj mi przepis kulinarny, który spełnia te kryteria.
                Nie musisz wykorzystywać wszystkich składników.
                Odpowiedz po Polsku.
                Zwróć to w formacie: {format}
                """;

        return new PromptTemplate(promptString);
    }

}
