// import { useState } from "react";

// export default function RegisterPage() {
//   const [email, setEmail] = useState("");
//   const [pass, setPass] = useState("");

//   const handleRegister = (e) => {
//     e.preventDefault();
//     alert(`Registered: ${email}`);
//   };

//   return (
//     <form onSubmit={handleRegister} style={{maxWidth: 400}}>
//       <h1>Registration</h1>

//       <input type="email" placeholder="Email"
//              value={email} onChange={(e)=>setEmail(e.target.value)} />

//       <input type="password" placeholder="Password"
//              value={pass} onChange={(e)=>setPass(e.target.value)} />

//       <button type="submit">Register</button>
//     </form>
//   );
// }

import { useState } from "react";
import { useRegisterMutation } from "../../api/userService";

export default function RegisterPage() {
    const [register, { isLoading }] = useRegisterMutation();

    const [form, setForm] = useState({
        email: "",
        password: "",
        confirmPassword: "",
    });

    const [message, setMessage] = useState(null);
    const [error, setError] = useState(null);

    const handleChange = (e) => {
        setForm({ ...form, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (form.password !== form.confirmPassword) {
            setError("Passwords do not match");
            return;
        }

        try {
            setError(null);
            const res = await register({
                email: form.email,
                password: form.password,
            }).unwrap();

            setMessage("Registered successfully!");
        } catch (err) {
            setError("Registration failed");
        }
    };

    return (
        <div className="max-w-md mx-auto bg-neutral-primary rounded-lg shadow p-6 border border-default">
            <h1 className="text-heading text-2xl font-semibold mb-4">Register</h1>

            <form onSubmit={handleSubmit} className="space-y-4">

                <div>
                    <label className="block text-heading mb-1">Email</label>
                    <input
                        name="email"
                        type="email"
                        value={form.email}
                        onChange={handleChange}
                        className="block w-full p-2.5 rounded-base border border-default bg-neutral-secondary-soft text-body focus:ring-brand focus:border-brand"
                        required
                    />
                </div>

                <div>
                    <label className="block text-heading mb-1">Password</label>
                    <input
                        name="password"
                        type="password"
                        value={form.password}
                        onChange={handleChange}
                        className="block w-full p-2.5 rounded-base border border-default bg-neutral-secondary-soft text-body focus:ring-brand focus:border-brand"
                        required
                    />
                </div>

                <div>
                    <label className="block text-heading mb-1">Confirm Password</label>
                    <input
                        name="confirmPassword"
                        type="password"
                        value={form.confirmPassword}
                        onChange={handleChange}
                        className="block w-full p-2.5 rounded-base border border-default bg-neutral-secondary-soft text-body focus:ring-brand focus:border-brand"
                        required
                    />
                </div>

                {error && (
                    <div className="text-red-500 text-sm">{error}</div>
                )}

                {message && (
                    <div className="text-green-500 text-sm">{message}</div>
                )}

                <button
                    type="submit"
                    disabled={isLoading}
                    className="w-full py-2.5 bg-brand text-white rounded-base font-medium hover:bg-opacity-90 transition disabled:opacity-50"
                >
                    {isLoading ? "Processing..." : "Register"}
                </button>
            </form>
        </div>
    );
}
