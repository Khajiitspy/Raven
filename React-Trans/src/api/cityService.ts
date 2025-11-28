import { createApi } from "@reduxjs/toolkit/query/react";
import { createBaseQuery } from "../utils/createBaseQuery";
import type { ICityItem } from "../types/cities/ICityItem";
import type { ICityCreate } from "../types/cities/ICityCreate";
import { serialize } from "object-to-formdata";

export const cityService = createApi({
  reducerPath: "cityService",
  baseQuery: createBaseQuery("cities"),
  tagTypes: ["Cities"],

  endpoints: (builder) => ({
  //   getCountries: builder.query<ICityItem[], void>({
  //     query: () => ({
  //       url: "",
  //       method: "GET"
  //     }),
  //     providesTags: ["Cities"]
  //   }),

    createCity: builder.mutation<ICityItem, ICityCreate>({
      query: (body) => {
        const formData = serialize(body, { indices: false });

        return {
          url: "",
          method: "POST",
          body: formData,
        };
      },
      invalidatesTags: ["Cities"]
    })
  })
});

export const {
  useCreateCityMutation
} = cityService;
