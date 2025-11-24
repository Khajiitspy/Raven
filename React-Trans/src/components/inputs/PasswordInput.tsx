import React from "react";

interface Props {
    label: string;
    name: string;
    value: string;
    required?: boolean;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const PasswordInput: React.FC<Props> = ({
    label,
    name,
    value,
    required = true,
    onChange
}) => {
    return (
        <div>
            <label className="block mb-1 font-medium">{label}</label>
            <input
                type="password"
                name={name}
                value={value}
                required={required}
                onChange={onChange}
                className="w-full px-4 py-2 rounded-lg border focus:ring-2 focus:ring-blue-500 transition"
            />
        </div>
    );
};

export default PasswordInput;
