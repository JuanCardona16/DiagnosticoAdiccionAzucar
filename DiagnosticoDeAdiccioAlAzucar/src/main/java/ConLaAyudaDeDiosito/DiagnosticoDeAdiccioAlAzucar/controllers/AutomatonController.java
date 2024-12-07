package ConLaAyudaDeDiosito.DiagnosticoDeAdiccioAlAzucar.controllers;

import ConLaAyudaDeDiosito.DiagnosticoDeAdiccioAlAzucar.services.Question;
import ConLaAyudaDeDiosito.DiagnosticoDeAdiccioAlAzucar.services.SugarAddictionAutomaton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/automaton")
public class AutomatonController {

    @Autowired
    private SugarAddictionAutomaton sugarAddictionAutomaton;

    @GetMapping("/questions")
    public String getNextQuestion() {
        String question = sugarAddictionAutomaton.getNextQuestion();
        if (question == null) {
            return "No hay más preguntas, el diagnóstico está completo.";
        }
        return question;
    }

    @PostMapping("/response")
    public String processResponse(@RequestParam boolean response) {
        return sugarAddictionAutomaton.processResponse(response);
    }

    // Endpoint para reiniciar el estado del autómata
    @PostMapping("/reset")
    public String resetAutomaton() {
        sugarAddictionAutomaton.resetState();
        return "El autómata se ha reiniciado.";
    }

}
