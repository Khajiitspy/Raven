import { fetchBaseQuery } from "@reduxjs/toolkit/query";
import { APP_ENV } from "../env";

// const PUBLIC_ENDPOINTS = [
//   'login/',
//   'register/',
//   'password-reset-request/',
//   'password-reset-confirm/',
//   'generate/',
// ];

export const createBaseQuery = (apiPrefix: string) => {
  const baseUrl = apiPrefix
    ? `${APP_ENV.API_BASE_URL}/api/${apiPrefix}`
    : `${APP_ENV.API_BASE_URL}/api`;

  return fetchBaseQuery({
    baseUrl,
    prepareHeaders: (headers) => {
      return headers;
    },
  });
};
