import { createApi } from "@reduxjs/toolkit/query/react";
import { createBaseQuery } from "../utils/createBaseQuery";
import type { ICountryItem } from "../types/countries/ICountryItem";
import type { ICountryCreate } from "../types/countries/ICountryCreate";
import { serialize } from "object-to-formdata";

export const countryService = createApi({
  reducerPath: "countryService",
  baseQuery: createBaseQuery("countries"),
  tagTypes: ["Countries"],

  endpoints: (builder) => ({
    getCountries: builder.query<ICountryItem[], void>({
      query: () => ({
        url: "",
        method: "GET"
      }),
      providesTags: ["Countries"]
    }),

    getCountryBySlug: builder.query<ICountryItem, string>({
      query: (slug) => ({
        url: slug,
        method: "GET"
      }),
      providesTags: (result) =>
        result ? [{ type: "Countries", id: result.slug }] : ["Countries"]
    }),

    createCountry: builder.mutation<ICountryItem, ICountryCreate>({
      query: (body) => {
        const formData = serialize(body, { indices: false });

        return {
          url: "",
          method: "POST",
          body: formData,
        };
      },
      invalidatesTags: ["Countries"]
    })
  })
});

export const {
  useGetCountriesQuery,
  useGetCountryBySlugQuery,
  useCreateCountryMutation
} = countryService;
