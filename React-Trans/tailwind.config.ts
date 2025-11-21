const colors = require("tailwindcss/colors");

module.exports = {
  darkMode: "class",
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    extend: {
      colors: {
        theme: {
          light: {
            bg: colors.white,
            text: colors.gray[900],
          },
          dark: {
            bg: colors.gray[900],
            text: colors.gray[100],
          },
          pink: {
            bg: colors.pink[100],
            text: colors.pink[900],
          },
          red: {
            bg: colors.red[100],
            text: colors.red[900],
          },
        }
      }
    }
  },
  plugins: [],
};
