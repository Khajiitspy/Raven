import { useState } from "react";
import { createCountry } from "../api/countries.api";

export default function CountryForm() {
  const [name, setName] = useState("");
  const [code, setCode] = useState("");
  const [slug, setSlug] = useState("");
  const [image, setImage] = useState(null);
  const [message, setMessage] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const data = new FormData();
    data.append("name", name);
    data.append("code", code);
    data.append("slug", slug);
    if (image) data.append("image", image);

    try {
      const response = await createCountry(data);
      setMessage("Country created: " + response.name);
    } catch (err) {
      setMessage("Error creating country");
    }
  };

  return (
    <form onSubmit={handleSubmit} style={{maxWidth: 400}}>
      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
        required
      />

      <input
        type="text"
        placeholder="Code"
        value={code}
        onChange={(e) => setCode(e.target.value)}
        required
      />

      <input
        type="text"
        placeholder="Slug"
        value={slug}
        onChange={(e) => setSlug(e.target.value)}
        required
      />

      <input
        type="file"
        onChange={(e) => setImage(e.target.files[0])}
      />

      <button type="submit">Create Country</button>

      {message && <p>{message}</p>}
    </form>
  );
}
