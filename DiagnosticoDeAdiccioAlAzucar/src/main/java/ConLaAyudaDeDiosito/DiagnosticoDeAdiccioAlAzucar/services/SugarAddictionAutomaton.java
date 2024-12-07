package ConLaAyudaDeDiosito.DiagnosticoDeAdiccioAlAzucar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

// import java.util.List;

import static com.fasterxml.jackson.databind.type.LogicalType.Map;

@Service
public class SugarAddictionAutomaton {

    @Autowired
    private final AutomatonState state = new AutomatonState();

    /* private final List<Question> questions = List.of(
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
    } */

    // Preguntas asociadas a los estados
     /* private final Map<Integer, String> questions = Map.of(
            0, "¿Sientes ansias de comer dulce todos los días?",
            1, "¿Consumes bebidas azucaradas frecuentemente?",
            2, "¿Te cuesta concentrarte si no consumes azúcar?",
            3, "¿Te irritas fácilmente si no consumes azúcar?",
            4, "¿Comes dulces incluso cuando no tienes hambre?",
            5, "¿Has intentado reducir tu consumo de azúcar sin éxito?"
    ); */

    private final Map<Integer, Question> questions = new HashMap<>();

    {
        questions.put(0, new Question("¿Sientes ansias de comer dulce todos los días?", 3));
        questions.put(1, new Question("¿Consumes bebidas azucaradas frecuentemente?", 2));
        questions.put(2, new Question("¿Te cuesta concentrarte si no consumes azúcar?", 3));
        questions.put(3, new Question("¿Te irritas fácilmente si no consumes azúcar?", 2));
        questions.put(4, new Question("¿Comes dulces incluso cuando no tienes hambre?", 1));
        questions.put(5, new Question("¿Has intentado reducir tu consumo de azúcar sin éxito?", 3));
    }

    private final int finalState = 6; // Estado final (sin más preguntas)
    private int score = 0; // Puntuación acumulada

    public String getNextQuestion() {
        // Verificar que el índice no sea mayor que el tamaño de las preguntas
        if (state.getCurrentState() >= 0 && state.getCurrentState() < questions.size()) {
            Question question = questions.get(state.getCurrentState());
            if (question != null) {
                return question.getText();
            }
        }

        System.out.println(score);

        return null; // No hay más preguntas
    }

    public String processResponse(boolean response) {
        // Asegurarse de que el estado actual no esté fuera de rango
        if (state.getCurrentState() < finalState) {
            state.transition(response);
            Question currentQuestion = questions.get(state.getCurrentState());

            if (response && currentQuestion != null) {
                score += currentQuestion.getWeight(); // Sumar el peso si la respuesta es afirmativa
            }

            if (state.getCurrentState() >= finalState) {
                System.out.println("Puntaje" + score);
                return getDiagnosis();
            }
        }

        return null; // Continúa el flujo
    }

    private String getDiagnosis() {
        // Diagnóstico basado en el estado final alcanzado
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
        state.reset(); // Reinicia el autómata
        score = 0;
    }

}