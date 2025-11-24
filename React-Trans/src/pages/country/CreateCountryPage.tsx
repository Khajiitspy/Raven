import { useState } from "react";
import {useCreateCountryMutation} from "../../api/countryService.ts";

import TextInput from "../../../src/components/inputs/TextInput";
import FileInput from "../../../src/components/inputs/FileInput";

const CreateCountryPage = () => {
    const [createCountry, { isLoading }] = useCreateCountryMutation();

    const [form, setForm] = useState({
        name: "",
        code: "",
        slug: "",
    });

    const [image, setImage] = useState(null);
    const [preview, setPreview] = useState(null);

    const [message, setMessage] = useState(null);
    const [error, setError] = useState(null);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleImage = (e) => {
        const file = e.target.files[0];
        setImage(file);
        if (file) setPreview(URL.createObjectURL(file));
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            setError(null);

            await createCountry({
                ...form,
                image,
            }).unwrap();

            setMessage("Country created successfully!");
            setForm({ name: "", code: "", slug: "" });
            setImage(null);
            setPreview(null);

        } catch (err) {
            setError("Failed to create country.");
        }
    };

    return (
        <div className="p-5 min-h-screen flex items-center justify-center">
            <div className="max-w-[900px] w-full rounded-2xl overflow-hidden shadow-[0_4px_20px_rgba(0,0,0,0.1)] dark:shadow-gray-800">
                <div className="grid md:grid-cols-2">

                    <div className="card p-10 hidden md:flex flex-col justify-center">
                        <h2 className="text-3xl font-semibold mb-4">Let's create new worlds!</h2>
                    </div>

                    <div className="p-6 md:p-10 flex flex-col justify-center">
                        <div className="text-center mb-6">
                            <h3 className="text-2xl font-semibold mb-1">Create Country</h3>
                            <p className="">
                                Fill in the new Country's info 
                            </p>
                        </div>


                        <form onSubmit={handleSubmit} className="space-y-4">

                            <TextInput
                                label="Name"
                                name="name"
                                type="text"
                                value={form.name}
                                required
                                onChange={handleChange}
                            />

                            <TextInput
                                label="Code"
                                name="code"
                                type="text"
                                value={form.code}
                                required
                                onChange={handleChange}
                            />

                            <TextInput
                                label="Slug"
                                name="slug"
                                type="text"
                                value={form.slug}
                                required
                                onChange={handleChange}
                            />


                            <FileInput
                                label="Image"
                                onFileSelect={handleImage}
                                preview={preview}
                            />

                            {error && <p className="text-red-500 text-sm">{error}</p>}
                            {message && <p className="text-green-500 text-sm">{message}</p>}

                            <button
                                type="submit"
                                disabled={isLoading}
                                className="w-full bg-blue-600 text-white py-3 rounded-lg font-semibold transition hover:bg-blue-700 active:scale-95 disabled:opacity-50"
                            >
                                {isLoading ? "Saving..." : "Create Country"}
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CreateCountryPage;
