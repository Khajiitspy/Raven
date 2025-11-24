import { useState, type FormEvent } from "react";
import { useRegisterMutation } from "../../../api/userService";

import TextInput from "../../../components/inputs/TextInput";
import PasswordInput from "../../../components/inputs/PasswordInput";
import FileInput from "../../../components/inputs/FileInput";

const RegisterPage: React.FC = () => {
    const [register, { isLoading }] = useRegisterMutation();

    const [form, setForm] = useState({
        email: "",
        phone: "",
        password: "",
        confirmPassword: "",
    });

    const [image, setImage] = useState<File | null>(null);
    const [preview, setPreview] = useState<string | null>(null);

    const [message, setMessage] = useState<string | null>(null);
    const [error, setError] = useState<string | null>(null);

    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) =>
        setForm({ ...form, [e.target.name]: e.target.value });

    const handleImage = (file: File | null) => {
        setImage(file);
        setPreview(file ? URL.createObjectURL(file) : null);
    };

    const handleSubmit = async (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();

        if (form.password !== form.confirmPassword) {
            setError("Passwords do not match");
            return;
        }

        try {
            setError(null);
            setMessage(null);

            const res = await register({ ...form, image }).unwrap();

            setMessage("Registered successfully!");
            setForm({ email: "", phone: "", password: "", confirmPassword: "" });
            setImage(null);
            setPreview(null);
        } catch (err: any) {
            const msg =
                err?.data?.error ||
                err?.data?.message ||
                err?.error ||
                "Unknown error";

            setError(msg);
            setMessage(null);
        }
    };

    return (
        <div className="p-5 min-h-screen flex items-center justify-center">
            <div className="max-w-[900px] w-full rounded-2xl overflow-hidden shadow-[0_4px_20px_rgba(0,0,0,0.1)] dark:shadow-gray-800">
                <div className="grid md:grid-cols-2">

                    <div className="card p-10 hidden md:flex flex-col justify-center">
                        <h2 className="text-3xl font-semibold mb-4">Ласкаво просимо!</h2>
                        <p className="text-lg">
                            Зареєструйтесь, щоб розпочати.
                        </p>
                    </div>

                    <div className="p-6 md:p-10 flex flex-col justify-center">
                        <div className="text-center mb-6">
                            <h3 className="text-2xl font-semibold mb-1">Реєстрація</h3>
                            <p className="">
                                Введіть свої дані, щоб створити акаунт
                            </p>
                        </div>

                        <form onSubmit={handleSubmit} className="space-y-5">

                            <FileInput
                                label="Image"
                                onFileSelect={handleImage}
                                preview={preview}
                            />

                            <TextInput
                                label="Email"
                                name="email"
                                type="email"
                                value={form.email}
                                required
                                onChange={handleChange}
                            />

                            <TextInput
                                label="Телефон"
                                name="phone"
                                type="tel"
                                value={form.phone}
                                required
                                onChange={handleChange}
                            />

                            <PasswordInput
                                label="Пароль"
                                name="password"
                                value={form.password}
                                onChange={handleChange}
                            />

                            <PasswordInput
                                label="Confirm Пароль"
                                name="confirmPassword"
                                value={form.confirmPassword}
                                onChange={handleChange}
                            />

                            {error && <p className="text-red-500 text-sm">{error}</p>}
                            {message && <p className="text-green-500 text-sm">{message}</p>}

                            <button
                                type="submit"
                                disabled={isLoading}
                                className="w-full bg-blue-600 text-white py-3 rounded-lg font-semibold transition hover:bg-blue-700 active:scale-95 disabled:opacity-50"
                            >
                                {isLoading ? "Зачекайте..." : "Зареєструватися"}
                            </button>
                        </form>

                    </div>
                </div>
            </div>
        </div>
    );
};

export default RegisterPage;
