import { createApi } from "@reduxjs/toolkit/query/react";
import { createBaseQuery } from "../utils/createBaseQuery";
import { serialize } from "object-to-formdata";

export const fileService = createApi({
  reducerPath: "fileService",
  baseQuery: createBaseQuery("files"),
  tagTypes: ["File"],

  endpoints: (builder) => ({
  //   getCountries: builder.query<ICityItem[], void>({
  //     query: () => ({
  //       url: "",
  //       method: "GET"
  //     }),
  //     providesTags: ["Cities"]
  //   }),

  RichUpload: builder.mutation<{ fileName: string }, { file: File }>({
      query: (body) => {
        const formData = serialize(body, { indices: false });

        return {
          url: "/rich-upload",
          method: "POST",
          body: formData,
        };
      },
      invalidatesTags: ["File"]
    })
  })
});

export const {
  useRichUploadMutation,
} = fileService;
