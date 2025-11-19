import { BrowserRouter, Routes, Route } from "react-router-dom";

import MainLayout from "./layout/Main/MainLayout";
import CountriesPage from "./pages/country/CountriesPage";
import CreateCountryPage from "./pages/country/CreateCountryPage";
import RegisterPage from "./pages/account/RegisterPage";

function App() {
  return (
    <BrowserRouter>
      <MainLayout>
        <Routes>
          <Route path="/" element={<div>Home Page</div>} />
          <Route path="/register" element={<RegisterPage />} />
          <Route path="/countries" element={<CountriesPage />} />
          <Route path="/countries/create" element={<CreateCountryPage />} />
        </Routes>
      </MainLayout>
    </BrowserRouter>
  );
}

export default App;
