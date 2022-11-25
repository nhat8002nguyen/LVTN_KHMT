import axios from "axios";

export const authServerInstance = axios.create({
  baseURL: process.env.AUTHORIZATION_SERVER_BASE_URL,
  headers: {
    post: {
      "Content-Type": "application/json",
    },
  },
  timeout: 3000,
});

export const resourceServerInstance = axios.create({
  baseURL: process.env.RESOURCE_SERVER_BASE_URL,
  headers: {
    post: {
      "Content-Type": "application/json",
    },
  },
  timeout: 3000,
});
