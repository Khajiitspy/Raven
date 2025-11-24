import React from "react";

interface Props {
    label: string;
    onFileSelect: (file: File | null) => void;
    preview?: string | null;
}

const FileInput: React.FC<Props> = ({ label, onFileSelect, preview }) => {
    const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const file = e.target.files?.[0] || null;
        onFileSelect(file);
    };

    return (
        <div>
            <label className="block mb-1 font-medium">{label}</label>
            <input
                type="file"
                accept="image/*"
                onChange={handleChange}
                className="block w-full text-body file:mr-4 file:py-2.5 file:px-4 file:rounded-base file:border-0 file:bg-brand hover:file:bg-opacity-90"
            />

            {preview && (
                <img
                    src={preview}
                    alt="Preview"
                    className="mt-3 h-32 w-full object-cover rounded border border-default"
                />
            )}
        </div>
    );
};

export default FileInput;
