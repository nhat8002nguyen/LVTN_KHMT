import axios from "axios";

export const harusaAxios = axios.create({
  baseURL: "https://refined-baboon-56.hasura.app/api/rest/v1",
  headers: {
    common: {
      "x-hasura-admin-secret":
        "viWASZFfXH6vUouDpheBH9pZDiwlDTwuvBmk6QVEeBumgPq0xCCZ7IJuT563uR3Z",
    },
  },
  timeout: 10000,
});
