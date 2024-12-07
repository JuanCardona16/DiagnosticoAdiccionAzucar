import axios from "axios";

export const apiUrl = axios.create({
  baseURL: "http://localhost:8080/api/v1/automaton",
  // baseURL: "https://diagnosticoadiccionazucar.onrender.com/api/v1/automaton",
});
