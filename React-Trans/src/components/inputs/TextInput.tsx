import React from "react";

interface Props {
    label: string;
    name: string;
    value: string;
    type?: string;
    required?: boolean;
    onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
}

const TextInput: React.FC<Props> = ({
    label,
    name,
    value,
    type = "text",
    required = false,
    onChange
}) => {
    return (
        <div>
            <label className="block mb-1 font-medium">{label}</label>
            <input
                name={name}
                type={type}
                value={value}
                onChange={onChange}
                required={required}
                className="w-full px-4 py-2 rounded-lg border focus:ring-2 focus:ring-blue-500 transition"
            />
        </div>
    );
};

export default TextInput;
