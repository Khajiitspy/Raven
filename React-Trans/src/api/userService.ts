import { createApi } from "@reduxjs/toolkit/query/react";
import { createBaseQuery } from "../utils/createBaseQuery";

export interface IUserRegister {
    email: string;
    password: string;
}

export interface IUserItem {
    id: number;
    email: string;
    dateCreated: string;
}

export const userService = createApi({
    reducerPath: "userService",
    baseQuery: createBaseQuery("users"),
    tagTypes: ["Users"],

    endpoints: (builder) => ({

        register: builder.mutation<IUserItem, IUserRegister>({
            query: (body) => ({
                url: "register",
                method: "POST",
                body,
                headers: {
                    "Content-Type": "application/json"
                }
            }),
            invalidatesTags: ["Users"]
        }),
    })
});

export const {
    useRegisterMutation
} = userService;
