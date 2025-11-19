import { useGetCountriesQuery } from "../../api/countryService";
import {APP_ENV} from "../../env";

export default function CountriesPage() {
    const { data, isLoading } = useGetCountriesQuery();

    if (isLoading) return <p className="text-heading">Loading...</p>;

    return (
        <div>
            <h1 className="text-2xl font-bold mb-4 text-heading">Countries</h1>

            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                {data?.map(country => (
                    <div
                        key={country.id}
                        className="bg-neutral-primary border border-default rounded-lg p-4 shadow"
                    >
                        <h3 className="text-heading text-lg font-semibold">{country.name}</h3>
                        <p className="text-body">Code: {country.code}</p>
                        <p className="text-body">Slug: {country.slug}</p>

                        {country.image && (
                            <img
                                src={`${APP_ENV.IMAGE_BASE_URL}large/${country.image}`}
                                alt={country.name}
                                className="w-full h-32 object-cover rounded mt-2"
                            />
                        )}
                    </div>
                ))}
            </div>
        </div>
    );
}
