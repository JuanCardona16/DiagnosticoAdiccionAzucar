import { apiUrl } from "../config/api/ApiConfig"

export const sendResponse = async (response: boolean) => {
  try {
    const AxiosResponse = await apiUrl.post('/response', null, {
      params: { response }
    })
    return AxiosResponse.data
  } catch (error) {
    return error
  }
}