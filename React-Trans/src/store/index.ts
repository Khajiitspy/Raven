import { configureStore } from "@reduxjs/toolkit";
import { countryService } from "../api/countryService";
import { userService } from "../api/userService";
import themeReducer from "./themeSlice";
import {type TypedUseSelectorHook, useDispatch, useSelector} from "react-redux";

export const store = configureStore({
  reducer: {
    [countryService.reducerPath]: countryService.reducer,
    [userService.reducerPath]: userService.reducer,
    theme: themeReducer,
  },
  middleware: (getDefaultMiddleware) =>
    getDefaultMiddleware().concat(countryService.middleware, userService.middleware),
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;


export const useAppDispatch = () => useDispatch<AppDispatch>();
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
