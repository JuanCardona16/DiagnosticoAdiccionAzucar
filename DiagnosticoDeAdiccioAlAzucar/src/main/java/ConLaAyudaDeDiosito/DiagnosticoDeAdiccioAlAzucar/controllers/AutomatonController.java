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
    public Question getNextQuestion() {
        return sugarAddictionAutomaton.getNextQuestion();
    }

    @PostMapping("/response")
    public String processResponse(@RequestParam boolean response) {
        return sugarAddictionAutomaton.processResponse(response);
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetAutomaton() {
        sugarAddictionAutomaton.resetState(); // Método para reiniciar el estado
        return ResponseEntity.ok("Estado reiniciado.");
    }

}
