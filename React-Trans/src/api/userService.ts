import { createApi } from "@reduxjs/toolkit/query/react";
import { createBaseQuery } from "../utils/createBaseQuery";
import { serialize } from "object-to-formdata";

export interface IUserRegister {
    email: string;
    phone: string;
    image?: File | null;
    password: string;
}

export interface IUserItem {
    id: number;
    lastName: string;
    name: string;
    email: string;
    phone: string;
    dateCreated: string;
}

export const userService = createApi({
    reducerPath: "userService",
    baseQuery: createBaseQuery("users"),
    tagTypes: ["Users"],

    endpoints: (builder) => ({

        register: builder.mutation<IUserItem, IUserRegister>({
            query: (body) => {
                const formData = serialize(body, { indices: false });

                return {
                  url: "register",
                  method: "POST",
                  body: formData,
                };
            },
            invalidatesTags: ["Users"]
        }),
    })
});

export const {
    useRegisterMutation
} = userService;
