import { apiUrl } from "../config/api/ApiConfig"

export const getQuestions = async () => {
  try {
    const response = await apiUrl.get('/questions')
    console.log(response)
    return response.data
  } catch (error) {
    return error
  }
}