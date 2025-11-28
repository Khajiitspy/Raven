import { useEditor, EditorContent } from "@tiptap/react";
import StarterKit from "@tiptap/starter-kit";
import Image from "@tiptap/extension-image";
import { APP_ENV } from "../../env";
import { useRichUploadMutation } from "../../api/fileService";

interface Props {
    value: string;
    onChange: (value: string) => void;
}

const HtmlEditor: React.FC<Props> = ({ value, onChange }) => {

    const editor = useEditor({
        extensions: [
            StarterKit,
            Image,
        ],
        content: value,
        onUpdate({ editor }) {
            onChange(editor.getHTML());
        }
    });

    const [uploadRichFile] = useRichUploadMutation();

    const uploadImage = async (event: any) => {
        const file = event.target.files?.[0];
        if (!file) return;

        const result = await uploadRichFile({ file }).unwrap();

        const url = APP_ENV.IMAGE_BASE_URL + "large/" + result?.fileName;

        editor?.chain().focus().setImage({ src: url }).run();
    };


    return (
        <div className="space-y-2">
            <div className="flex gap-2 border p-2 rounded bg-gray-50">
                <button
                    type="button"
                    onClick={() => editor?.chain().focus().toggleBold().run()}
                    className="border px-2 rounded"
                >
                    Bold
                </button>

                <button
                    type="button"
                    onClick={() => editor?.chain().focus().toggleItalic().run()}
                    className="border px-2 rounded"
                >
                    Italic
                </button>

                <button
                    type="button"
                    onClick={() => editor?.chain().focus().toggleBulletList().run()}
                    className="border px-2 rounded"
                >
                    • List
                </button>

                <label className="card border px-2 rounded cursor-pointer">
                    Upload Image
                    <input
                        type="file"
                        accept="image/*"
                        hidden
                        onChange={uploadImage}
                    />
                </label>
            </div>

            <EditorContent editor={editor} className="border p-3 min-h-[200px] rounded bg-white" />
        </div>
    );
};

export default HtmlEditor;
