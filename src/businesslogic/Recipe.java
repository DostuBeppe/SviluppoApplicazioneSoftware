package businesslogic;

// questa classe è solo uno scheletro
// sono implementate solo le caratteristiche richieste da "Gestire menu"
// E' possibile modificarne senza conseguenze l'implementazione interna, ma se si  vuole
// che il SW continui a funzionare non è il caso di cambiarne l'interfaccia (per cambiare
// si intende modificare quel che già c'è, aggiungere è sempre possibile)
public class Recipe {
    public String getName() {
        return name;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public  enum Type {Preparation, Dish};
    private String name;
    private Type type;
    private int recipeId;

    public Recipe(String name, Type t) {
        this.name = name;
        this.type = t;
    }
    public Recipe(String name) {
        this.name = name;
    }
    public boolean isDish() {
        return this.type.equals(Type.Dish);
    }

    public boolean isPreparation() {
        return this.type.equals(Type.Preparation);
    }

    public String toString() {
        return this.name;
    }

}
