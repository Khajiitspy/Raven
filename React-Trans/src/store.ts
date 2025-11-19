import { configureStore } from "@reduxjs/toolkit";
import { countryService } from "./api/countryService";
import { userService } from "./api/userService";

export const store = configureStore({
  reducer: {
    [countryService.reducerPath]: countryService.reducer,
    [userService.reducerPath]: userService.reducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(countryService.middleware, userService.middleware),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
