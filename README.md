## Cook AI App
* Java 17
* Spring boot 3.2.5
* springframework.ai

## Interface
* API POST endpoint: http//localhost:8080/recipeSuggestions
* JSON example:
```
{
    "ingredients": [
        {
            "name": "kurczak",
            "weight": 200.0
        },
        {
            "name": "ryż",
            "weight": 200.0
        },
        {
            "name": "pomidory",
            "weight": 150.0
        }
    ],
    "diet": "bezglutenowa",
    "mealType": "obad"
}
```