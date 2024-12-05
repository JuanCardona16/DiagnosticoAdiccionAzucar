import { apiUrl } from "../config/api/ApiConfig"

export const resetPrueba = async () => {
  try {
    const AxiosResponse = await apiUrl.post('/reset')
    return AxiosResponse.data
  } catch (error) {
    return error
  }
}