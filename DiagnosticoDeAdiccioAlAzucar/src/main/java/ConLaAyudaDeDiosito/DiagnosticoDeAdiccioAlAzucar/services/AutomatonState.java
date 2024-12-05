package ConLaAyudaDeDiosito.DiagnosticoDeAdiccioAlAzucar.services;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AutomatonState {
    private int currentState = 0;
    private int score = 0;
    private final List<Boolean> responses = new ArrayList<>();

    public int getCurrentState() {
        return currentState;
    }

    public void incrementState() {
        currentState++;
    }

    public int getScore() {
        return score;
    }

    public void addScore(int weight) {
        score += weight;
    }

    public void addResponse(boolean response) {
        responses.add(response);
    }

    public List<Boolean> getResponses() {
        return responses;
    }

    public void reset() {
        currentState = 0;
        score = 0;
        responses.clear();
    }

}
