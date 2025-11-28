export interface ICityItem {
  id: number;
  name: string;
  slug: string;
  image?: string;
  countryId: number;
  countryName: string;
  description: string;
  population: number;
  timezone: string;
  mainAirportCode: string;
  avgMealPrice: number;
  avgHotelPrice: number;
  hasRecrationalWater: boolean;
}
