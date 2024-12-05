package ConLaAyudaDeDiosito.DiagnosticoDeAdiccioAlAzucar.services;

import org.springframework.stereotype.Service;

@Service
public class Question {

    // Getters y setters
    private String text;
    private int weight;

    // Constructor
    public Question(String text, int weight) {
        this.text = text;
        this.weight = weight;
    }

    public Question() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
