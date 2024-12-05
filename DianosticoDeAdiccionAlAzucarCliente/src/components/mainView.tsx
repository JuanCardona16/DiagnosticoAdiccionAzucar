import { useNavigate } from "react-router-dom";
import styles from './mainView.module.css'

export const MainView = () => {
  const navigate = useNavigate();

  return (
    <section className={styles.container}>
      <h1>Bienvenido al Diagnóstico de Adicción al Azúcar</h1>
      <h4>
        Esta herramienta le ayudará a evaluar su relación con el consumo de
        azúcar.
      </h4>
      <div>
        <p>
          A continuación, se le presentará un cuestionario con 5 preguntas
          simples sobre sus hábitos de consumo de azúcar.
        </p>
        <p>
          Sus respuestas nos ayudarán a determinar si podría estar en riesgo de
          desarrollar una adicción al azúcar.
        </p>
        <p>
          <strong>
            Recuerde: Esta herramienta no sustituye el consejo médico
            profesional.
          </strong>
        </p>
      </div>
      <button type="button" onClick={() => navigate("/prueba")}>
        Comenzar Diagnostico
      </button>
    </section>
  );
};
