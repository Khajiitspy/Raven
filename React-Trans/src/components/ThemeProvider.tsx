import { useEffect } from "react";
import { useSelector } from "react-redux";
import { type RootState } from "../store";

export default function ThemeProvider({ children }) {
  const theme = useSelector((state: RootState) => state.theme.theme);

  useEffect(() => {
    const html = document.documentElement;

    html.classList.remove("light", "dark", "pink", "red", "ukraine", "dracula");
    html.classList.add(theme);

  }, [theme]);

  return children;
}
