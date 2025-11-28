import { useState } from "react";
import { useNavigate } from "react-router";
import { useGetCountriesQuery } from "../../api/countryService";
import { useCreateCityMutation } from "../../api/cityService";
import type { ICityCreate } from "../../types/cities/ICityCreate.ts";
import InputField from "../inputs/InputField.tsx";
import ImageUploader from "../uploaders/ImageUploader.tsx";
import TextareaField from "../inputs/TextareaField.tsx";
import BaseButton from "../inputs/BaseButton.tsx";
import type { UploadFile } from "antd";
import {parseServerValidationErrors} from "../../utils/parseServerValidationErrors.ts";
import { APP_ENV } from "../../env";

const CreateCityForm: React.FC = () => {
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

    const [description] = useState("");
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
            await createCity({ ...form, image: fileList[0].originFileObj }).unwrap();
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

            <div className="mt-2">
                {form.countryId && (
                    <img
                        src={`${APP_ENV.IMAGE_BASE_URL}medium/${countries.find(x => x.id == form.countryId)?.image}`}
                        className="w-32 h-20 object-cover"
                    />
                )}
            </div>
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
            <TextareaField name="description" label="Description" placeholder="Describe this city" value={description}/>
        </div>

        {formError && <p className="text-red-500 text-sm">{formError}</p>}

        <BaseButton
            type="submit"
            className="w-full rounded-xl border border-blue-300 font-medium py-2"
        >
            {isLoading ? "Loading..." : "Create"}
        </BaseButton>

      </form>
    );
};

export default CreateCityForm;
