import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router";
import { useGetCountriesQuery } from "../../../api/countryService";
import { useCreateCityMutation } from "../../../api/cityService";
import type { ICityCreate } from "../../../types/cities/ICityCreate.ts";
import InputField from "../../../components/inputs/InputField.tsx";
import ImageUploader from "../../../components/uploaders/ImageUploader.tsx";
import HtmlEditor from "../../../components/inputs/HtmlEditor.tsx";
import BaseButton from "../../../components/inputs/BaseButton.tsx";
import type { UploadFile } from "antd";
import {parseServerValidationErrors} from "../../../utils/parseServerValidationErrors.ts";
import { APP_ENV } from "../../../env";
// import CreateCityForm from "../../../components/forms/CreateCityForm.tsx";

const CreateCityPage: React.FC = () => {
    const navigate = useNavigate();
    const { data: countries = [] } = useGetCountriesQuery();
    const [createCity, { isLoading }] = useCreateCityMutation();

    const [form, setForm] = useState<ICityCreate>({
        name: "",
        slug: "",
        countryId: 0,
        description: "",
        population: 0,
        timezone: "",
        mainAirportCode: "",
        avgMealPrice: 0,
        avgHotelPrice: 0,
        hasRecrationalWater: false
    });

    const [description, setDescription] = useState("");
    const [showAdvanced, setShowAdvanced] = useState(false);
    const [fieldErrors, setFieldErrors] = useState<Record<string, string>>({});
    const [formError, setFormError] = useState<string | null>(null);
    const [fileList, setFileList] = useState<UploadFile[]>([]);
    const [imageError, setImageError] = useState(false);

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        setFormError(null);
        setFieldErrors({});

        if (!fileList[0]?.originFileObj) {
            setImageError(true);
            return;
        }

        try {
            await createCity({ ...form, description: description, image: fileList[0].originFileObj }).unwrap();
            navigate("/");
        } catch (err: any) {
            if (err?.data?.errors) {
                const { fieldErrors } = parseServerValidationErrors(err.data.errors);
                setFieldErrors(fieldErrors);
            } else {
                setFormError(err?.data?.message || "Помилка Creating");
            }
        }
    };

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    };

    const handleSelectChange = (e: React.ChangeEvent<HTMLSelectElement>) => {
        const { name, value } = e.target;
        setForm((prev) => ({ ...prev, [name]: value }));
    };

    return (
        <div className="p-5 min-h-screen flex items-center justify-center">
            <div className="max-w-[900px] w-full rounded-2xl overflow-hidden shadow-[0_4px_20px_rgba(0,0,0,0.1)] dark:shadow-gray-800">
                <div className="grid md:grid-cols-2">
                    <div
                      className="card p-10 hidden md:flex flex-col justify-center text-white"
                      style={{
                          backgroundImage: form.countryId
                            ? `url(${APP_ENV.IMAGE_BASE_URL}large/${countries.find(x => x.id === Number(form.countryId))?.image})`
                            : "url('/default-bg.jpg')",
                          backgroundSize: "cover",
                          backgroundPosition: "center",
                          backgroundRepeat: "no-repeat",
                      }}
                    >
                        <div className="card p-10 rounded-2xl">
                            <h2 className="text-3xl font-semibold mb-4">Ready to Create?</h2>
                            <p className="text-lg">Create cities</p>
                        </div>
                    </div>

                    <div className="p-6 md:p-10 flex flex-col overflow-y-auto max-h-[85vh]">
                        <div className="text-center mb-6">
                            <h3 className="text-2xl font-semibold mb-1">City</h3>
                            <p>Enter some info about this city</p>
                        </div>

                        <form onSubmit={handleSubmit} className="space-y-4">

                            <InputField label="Name"
                              name="name"
                              placeholder="City"
                              value={form.name}
                              onChange={handleChange}
                              error={fieldErrors.name} />

                            <InputField label="Slug"
                              name="slug"
                              placeholder="city"
                              value={form.slug}
                              onChange={handleChange}
                              error={fieldErrors.slug} />

                            <div>
                                <label className="block mb-1 font-medium">City</label>
                                <select
                                    name="countryId"
                                    value={form.countryId}
                                    onChange={handleSelectChange}
                                    required
                                    className="w-full border p-2 rounded"
                                >
                                    <option value="">Choose country</option>
                                    {countries.map(c => (
                                        <option key={c.id} value={c.id}>
                                            {c.name}
                                        </option>
                                    ))}
                                </select>
                            </div>

                              <div className="w-full text-center">
                                  <ImageUploader
                                      fileList={fileList}
                                      setFileList={setFileList}
                                      imageError={imageError}
                                      setImageError={setImageError}
                                  />
                                  {imageError && <p className="text-red-500 text-sm mt-1">Image is required</p>}
                              </div>


                            <div>
                                <label className="block mb-1 font-medium">Description</label>
                                <HtmlEditor value={description} onChange={setDescription} />
                            </div>

                            <button
                              type="button"
                              className="underline text-blue-600"
                              onClick={() => setShowAdvanced(!showAdvanced)}
                            >
                              {showAdvanced ? "Hide advanced fields" : "Show advanced fields"}
                            </button>

                            {showAdvanced && (
                              <div className="space-y-4 mt-4 border-t pt-4">

                                  <InputField label="Timezone" name="timezone"
                                    value={form.timezone} onChange={handleChange} />

                                  <InputField label="Airport Code" name="mainAirportCode"
                                    value={form.mainAirportCode} onChange={handleChange} />

                                  <InputField
                                      label="Population"
                                      name="population"
                                      type="number"
                                      placeholder="1000000"
                                      value={form.population.toString()}
                                      onChange={handleChange}
                                  />

                                  <InputField
                                      label="Average Meal Price"
                                      name="avgMealPrice"
                                      type="number"
                                      placeholder="12.5"
                                      value={form.avgMealPrice.toString()}
                                      onChange={handleChange}
                                  />

                                  <InputField
                                      label="Average Hotel Price"
                                      name="avgHotelPrice"
                                      type="number"
                                      placeholder="100"
                                      value={form.avgHotelPrice.toString()}
                                      onChange={handleChange}
                                  />

                                  <label className="flex items-center gap-2">
                                    <input
                                        type="checkbox"
                                        name="hasRecrationalWater"
                                        checked={form.hasRecrationalWater}
                                        onChange={(e) =>
                                            setForm((prev) => ({
                                                ...prev,
                                                hasRecrationalWater: e.target.checked
                                            }))
                                        }
                                    />
                                    Has Recreational Water
                                  </label>

                              </div>
                            )}

                            {formError && <p className="text-red-500 text-sm">{formError}</p>}

                            <BaseButton
                                type="submit"
                                className="w-full rounded-xl border border-blue-300 font-medium py-2"
                            >
                                {isLoading ? "Loading..." : "Create"}
                            </BaseButton>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default CreateCityPage;
