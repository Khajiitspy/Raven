export interface ICityCreate {
  name: string;
  slug: string;
  image?: File | null;
  countryId: number;
  description: string;
  population: number;
  timezone: string;
  mainAirportCode: string;
  avgMealPrice: number;
  avgHotelPrice: number;
  hasRecrationalWater: boolean;
}
