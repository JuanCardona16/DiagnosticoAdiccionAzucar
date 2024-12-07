import { useEffect, useState } from "react";
import { getQuestions } from "../services/getQuestions";
import { resetPrueba, sendResponse } from "../services";
import { useNavigate } from "react-router-dom";
import styles from "./prueba.module.css";

interface Diagnosis {
  diagnosis: string;
  description: string;
  recommendations: string[];
}

export const Prueba = () => {
  const navigate = useNavigate();
  const [question, setQuestion] = useState<string | null>(null);
  const [diagnosis, setDiagnosis] = useState<string | null>(null);
  const [ResultDiagnosis, setResultDiagnosis] = useState<Diagnosis | null>(
    null
  );
  const [isLoading, setIsLoading] = useState<boolean>(false); // Estado de carga

  useEffect(() => {
    const fetchQuestion = async () => {
      setIsLoading(true); // Inicia la carga
      const question = await getQuestions();
      setQuestion(question || null);
      setIsLoading(false); // Termina la carga
    };

    fetchQuestion();
  }, []);

  console.log(question)

  const handleResponse = async (response: boolean) => {
    setIsLoading(true); // Inicia la carga
    const result = await sendResponse(response);
    console.log("Respuestas" + response)

    console.log("Resultado: " + result)

    if (result) {
      setDiagnosis(result); // Diagnóstico final
      generateDiagnostics(result);
      setQuestion(null);
    } else {
      const nextQuestion = await getQuestions();
      setQuestion(nextQuestion || null);
    }
    setIsLoading(false); // Termina la carga
  };

  const generateDiagnostics = (data: string) => {
    if (data === "Adicción severa al azúcar" || data === "Riesgo alto de adicción al azúcar") {
      setResultDiagnosis({
        diagnosis: "Alto riesgo de adicción al azúcar.",
        description:
          "Tus respuestas indican un alto riesgo de adicción al azúcar. Es importante tomar medidas para reducir tu consumo.",
        recommendations: [
          "Consulta a un profesional de la salud para obtener ayuda personalizada.",
          "Reduce gradualmente el consumo de alimentos y bebidas azucaradas.",
          "Busca alternativas saludables para satisfacer tus antojos de dulce.",
          "Mantén un diario de alimentación para ser más consciente de tu consumo de azúcar.",
        ],
      });
    }

    if (data === "Riesgo moderado") {
      setResultDiagnosis({
        diagnosis: "Riesgo moderado de adicción al azúcar.",
        description:
          "Tus respuestas sugieren un riesgo moderado. Es un buen momento para revisar tus hábitos alimenticios.",
        recommendations: [
          "Intenta reducir el consumo de azúcar en tu dieta diaria.",
          "Aumenta el consumo de frutas frescas como alternativa a los dulces procesados.",
          "Lee las etiquetas de los alimentos para ser consciente del azúcar añadido.",
          "Practica técnicas de manejo del estrés para reducir los antojos emocionales.",
          "Te recomendamos consultar a un profesional de la salud para obtener ayuda personalizada.",
        ],
      });
    }

    if (data === "No hay señales de adicción al azúcar") {
      setResultDiagnosis({
        diagnosis: "¡Felicidades! Bajo riesgo de adicción al azúcar.",
        description:
          "Tus hábitos actuales indican un bajo riesgo de adicción al azúcar. ¡Sigue así!",
        recommendations: [
          "Mantén tus buenos hábitos alimenticios.",
          "Continúa eligiendo alimentos bajos en azúcar.",
          "Comparte tus estrategias de alimentación saludable con amigos y familiares.",
          "Mantente informado sobre los beneficios de una dieta baja en azúcar.",
          "Te recomendamos consultar a un profesional de la salud para obtener ayuda personalizada.",
        ],
      });
    }

    return data;
  };

  if (isLoading) {
    return (
      <div>
        <h1>Cargando...</h1>
        <p>Por favor espera mientras se genera el diagnóstico.</p>
      </div>
    );
  }

  if (diagnosis) {
    return (
      <div className={styles.diagnosisContainer}>
        <div>
          <h1>{ResultDiagnosis?.diagnosis}</h1>
          <p>{ResultDiagnosis?.description}</p>
          <div>
            <h3>Aqui tienes algunos consejos y recomendaciones: </h3>
            <div>
              {ResultDiagnosis?.recommendations.map((item) => (
                <p key={item} style={{ marginBottom: ".5rem" }}>
                  <span>📌 </span>
                  <span>{item}</span>
                </p>
              ))}
            </div>
          </div>
        </div>
        <button
          type="button"
          onClick={() => {
            resetPrueba();
            navigate("/");
          }}
        >
          Reintentar diagnostico
        </button>
      </div>
    );
  }

  return (
    <div className={styles.containerForm}>
      <h1>Diagnóstico de Adicción al Azúcar</h1>
      <p>
        Responda las siguientes preguntas para evaluar su relación con el
        azúcar.
      </p>
      <div>
        <h2>{question}</h2>
        <div>
          <button onClick={() => handleResponse(true)}>Sí</button>
          <button onClick={() => handleResponse(false)}>No</button>
        </div>
      </div>
    </div>
  );
};
