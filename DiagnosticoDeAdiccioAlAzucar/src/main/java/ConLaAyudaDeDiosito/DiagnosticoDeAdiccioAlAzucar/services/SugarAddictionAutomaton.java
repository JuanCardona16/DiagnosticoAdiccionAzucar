package ConLaAyudaDeDiosito.DiagnosticoDeAdiccioAlAzucar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SugarAddictionAutomaton {

    @Autowired
    private final AutomatonState state = new AutomatonState();

    private final List<Question> questions = List.of(
            new Question("¿Sientes ansias de comer dulce todos los días?", 3),
            new Question("¿Consumes bebidas azucaradas frecuentemente?", 2),
            new Question("¿Te cuesta concentrarte si no consumes azúcar?", 3),
            new Question("¿Te irritas fácilmente si no consumes azúcar?", 2),
            new Question("¿Comes dulces incluso cuando no tienes hambre?", 1),
            new Question("¿Has intentado reducir tu consumo de azúcar sin éxito?", 3)
    );

    public Question getNextQuestion() {
        if (state.getCurrentState() < questions.size()) {
            return questions.get(state.getCurrentState());
        }
        return null; // No hay más preguntas
    }

    public String processResponse(boolean response) {
        state.addResponse(response);

        if (response) {
            state.addScore(questions.get(state.getCurrentState()).getWeight());
        }

        state.incrementState();

        if (state.getCurrentState() >= questions.size()) {
            return getDiagnosis();
        }

        return null;
    }

    private String getDiagnosis() {
        int score = state.getScore();

        if (score >= 12) {
            return "Adicción severa al azúcar";
        } else if (score >= 8) {
            return "Riesgo alto de adicción al azúcar";
        } else if (score >= 4) {
            return "Riesgo moderado";
        } else {
            return "No hay señales de adicción al azúcar";
        }
    }

    public void resetState() {
        state.reset(); // Reinicia el estado del autómata
    }

}