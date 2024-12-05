import styles from "../styles/App.module.css";
import { BrowserRouter, Outlet, Route, Routes } from "react-router-dom";
import { MainView, Prueba } from "../components";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route
          path="/"
          element={
            <div className={styles.mainContainer}>
              <header className={styles.header}></header>
              <main className={styles.container}>
                <Outlet />
              </main>
              <footer className={styles.footer}>
                Creado con 💚 por Juan David Cardona
              </footer>
            </div>
          }
        >
          <Route index element={<MainView />} />
          <Route path="/prueba" element={<Prueba />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
